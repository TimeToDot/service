package com.example.warehouse;

import java.util.ArrayList;
import java.util.List;

public final class WarehouseApi {

    public void addAsset(String serialNumber, String assetType, String warrantyExpirationDate) {

    }

    public void removeAsset(String serialNumber) {

    }

    public boolean isAssetInWarehouse(String serialNumber) {
        return false;
    }

    public int countAllAssets() {
        return -1;
    }

    public int countByAssetType(String assetType) {
        return -1;
    }
    
    public List<String> findExpiredWarrantyAssetsSerialNumbers() {
        return new ArrayList<>();
    }    
}
