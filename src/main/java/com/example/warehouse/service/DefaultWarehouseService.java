package com.example.warehouse.service;

import com.example.warehouse.service.mapper.AssetMapper;
import com.example.warehouse.service.model.AssetType;
import com.example.warehouse.service.model.WarehouseAsset;
import com.example.warehouse.service.utils.DateUtil;
import com.example.warehouse.service.utils.LoggerUtil;
import com.example.warehouse.service.validator.AssetValidator;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Slf4j
public class DefaultWarehouseService implements WarehouseService {

  private final Map<String, WarehouseAsset> warehouseAssetMap;

  private final AssetValidator assetValidator;

  private final AssetMapper assetMapper;

  private final DateFormat dateFormatter;

  public DefaultWarehouseService() {
    this.warehouseAssetMap = new ConcurrentHashMap<>();
    this.assetValidator = new AssetValidator();
    this.assetMapper = new AssetMapper();
    this.dateFormatter = new SimpleDateFormat(DateUtil.DATE_FORMAT);
  }

  @Override
  public void addAsset(String serialNumber, String assetType, String warrantyExpirationDate) {
    if (assetValidator.isDataValid(serialNumber, assetType, warrantyExpirationDate)) {
      WarehouseAsset warehouseAsset = null;

      try {
        warehouseAsset = assetMapper.mapWarehouseAsset(assetType, warrantyExpirationDate);
      } catch (ParseException e) {
        log.error(e.getMessage(), e);
      }
      warehouseAssetMap.put(serialNumber, warehouseAsset);

      if (warehouseAssetMap.containsKey(serialNumber)) {
        log.info(LoggerUtil.SERIAL_NUMBER_EXIST);
      }
      log.info(LoggerUtil.ASSET_WAS_CREATED);
    }
    log.info(LoggerUtil.ARGUMENTS_ARE_NOT_VALID);
  }

  @Override
  public void removeAsset(String serialNumber) {
    if (assetValidator.isSerialNumberValid(serialNumber)) {
      warehouseAssetMap.remove(serialNumber);

      log.info(LoggerUtil.ASSET_WAS_REMOVED);
    }
    log.info(LoggerUtil.ARGUMENT_IS_NOT_VALID);
  }

  @Override
  public boolean isAssetInWarehouse(String serialNumber) {
    if (assetValidator.isSerialNumberValid(serialNumber)) {
      return warehouseAssetMap.containsKey(serialNumber);
    }
    log.info(LoggerUtil.ARGUMENT_IS_NOT_VALID);
    return false;
  }

  @Override
  public int countAllAssets() {
    return warehouseAssetMap.size();
  }

  @Override
  public int countByAssetType(String assetType) {
    if (assetValidator.isTypeValid(assetType)) {
      return (int) warehouseAssetMap.entrySet().stream()
        .filter(entry -> entry.getValue().getAssetType().equals(AssetType.valueOf(assetType)))
        .count();
    }
    log.info(LoggerUtil.ARGUMENT_IS_NOT_VALID);
    return 0;
  }

  @Override
  public List<String> findExpiredWarrantyAssetsSerialNumbers() {
    try {
      Date currentDate = dateFormatter.parse(dateFormatter.format(new Date()));

      return Collections.unmodifiableMap(warehouseAssetMap)
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue().getWarrantyExpirationDate().compareTo(currentDate) < 0)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return new ArrayList<>();
  }
}