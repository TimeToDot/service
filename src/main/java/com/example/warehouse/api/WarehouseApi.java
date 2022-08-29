package com.example.warehouse.api;

import com.example.warehouse.service.WarehouseService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class WarehouseApi {

    private final WarehouseService warehouseService;

    public WarehouseApi(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void addAsset(String serialNumber, String assetType, String warrantyExpirationDate) {
        warehouseService.addAsset(serialNumber, assetType, warrantyExpirationDate);
    }

    public void removeAsset(String serialNumber) {
        warehouseService.removeAsset(serialNumber);
    }

    public boolean isAssetInWarehouse(String serialNumber) {
        return warehouseService.isAssetInWarehouse(serialNumber);
    }

    public int countAllAssets() {
        return warehouseService.countAllAssets();
    }

    public int countByAssetType(String assetType) {
        return warehouseService.countByAssetType(assetType);
    }
    
    public List<String> findExpiredWarrantyAssetsSerialNumbers() {
        return warehouseService.findExpiredWarrantyAssetsSerialNumbers();
    }    
}
