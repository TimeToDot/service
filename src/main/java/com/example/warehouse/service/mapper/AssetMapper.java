package com.example.warehouse.service.mapper;

import com.example.warehouse.service.model.AssetType;
import com.example.warehouse.service.model.WarehouseAsset;
import com.example.warehouse.service.utils.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AssetMapper {
  private final DateFormat formatter = new SimpleDateFormat(DateUtil.DATE_FORMAT);

  public WarehouseAsset mapWarehouseAsset(String assetType, String warrantyExpirationDate) throws ParseException {
    return new WarehouseAsset(AssetType.valueOf(assetType), formatter.parse(warrantyExpirationDate));
  }
}