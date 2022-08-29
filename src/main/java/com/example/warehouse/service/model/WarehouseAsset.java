package com.example.warehouse.service.model;

import java.util.Date;

public class WarehouseAsset {
  private final AssetType assetType;
  private final Date warrantyExpirationDate;

  public WarehouseAsset(AssetType assetType, Date warrantyExpirationDate) {
    this.assetType = assetType;
    this.warrantyExpirationDate = warrantyExpirationDate;
  }

  public AssetType getAssetType() {
    return assetType;
  }

  public Date getWarrantyExpirationDate() {
    return warrantyExpirationDate;
  }
}