package com.example.warehouse.service;

import java.util.List;

public interface WarehouseService {
  void addAsset(String serialNumber, String assetType, String warrantyExpirationDate);

  void removeAsset(String serialNumber);

  boolean isAssetInWarehouse(String serialNumber);

  int countAllAssets();

  int countByAssetType(String assetType);

  List<String> findExpiredWarrantyAssetsSerialNumbers();

}
