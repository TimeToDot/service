package com.example.warehouse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class WarehouseApiTest {

    private WarehouseApi api;

    @Before
    public void setUp() {
        api = new WarehouseApi();
        executeWarehouseOperations(api);
    }
    
    private void executeWarehouseOperations(WarehouseApi api){
        api.addAsset("CXXWEW8WXCXCV", "MOBILE_PHONE", "13/12/2016");
        api.addAsset("CSDD22DSS4SWF", "TABLET", "22/12/2020");
        api.addAsset("SDSCS5W32D222", "COMPUTER", "21/12/2020");
        api.addAsset("DSS23532323SD", "COMPUTER", "20/12/2020");
        api.addAsset("CXXWEW8WXCXCV", "MOBILE_PHONE", "13/12/2016");
        api.addAsset("CSDD23DSSDBBF", "TABLET", "14/10/2019");
        api.addAsset("DSS23231323SD", "COMPUTER", "22/11/2021");
        api.addAsset("CXXWE2EWXCXCV", "MOBILE_PHONE", "03/03/2019");
        api.addAsset("VSADW5WEEEWWW", "WATCH", "10/02/2017");
        api.addAsset("VSADAAA005WWW", "TV", "22/12/2021");
        api.addAsset("CXXWE23C52XCV", "TV", "03/01/2024");
        api.addAsset("WDFGSDDC52XCV", "TV", "03/01/2024");
        api.addAsset("VSA43A56EEEWR", "WATCH", "22/12/2021");
        api.addAsset("OGSDXWE23C523", "TV", "01/01/2020");
        api.addAsset("VSA43A56EEEWR", "WATCH", "22/12/2021");

        api.removeAsset("CXXWEW8WXCXCV");
        api.removeAsset("WDFGSDDC52XCV");
        api.removeAsset("OGSDXWE23C523");
        api.removeAsset("USDSD33SD3343");       
    }

    @Test
    public void shouldReturnTotalNumberOfAssetsInWarehouse() {
        //when
        int totalAssets = api.countAllAssets();

        //then
        assertEquals("Total number of assets is not correct", 10, totalAssets);
    }

    @Test
    public void shouldReturnTotalNumberOfComputersInWarehouse() {
        //when
        int totalComputers = api.countByAssetType("COMPUTER");

        //then
        assertEquals("Total number of computers is not correct", 3, totalComputers);
    }

    @Test
    public void shouldReturnTotalNumberOfWatchesInWarehouse() {
        //when
        int totalWatches = api.countByAssetType("WATCH");

        //then
        assertEquals("Total number of watches is not correct", 2, totalWatches);
    }

    @Test
    public void shouldReturnTotalNumberOfTabletsInWarehouse() {
        //when
        int totalTablets = api.countByAssetType("TABLET");

        //then
        assertEquals("Total number of tablets is not correct", 2, totalTablets);
    }

    @Test
    public void shouldReturnTotalNumberOfMobilePhonesInWarehouse() {
        //when
        int totalMobilePhones = api.countByAssetType("MOBILE_PHONE");

        //then
        assertEquals("Total number of mobile phones is not correct", 1, totalMobilePhones);
    }

    @Test
    public void shouldReturnTotalNumberOfServersInWarehouse() {
        //when
        int totalServers = api.countByAssetType("SERVER");

        //then
        assertEquals("Total number of servers is not correct", 0, totalServers);
    }

    @Test
    public void shouldReturnTotalNumberOfTvsInWarehouse() {
        //when
        int totalTvs = api.countByAssetType("TV");

        //then
        assertEquals("Total number of tvs is not correct", 2, totalTvs);
    }

    @Test
    public void shouldFindAssetInWarehouse() {
        //when
        boolean assetInWarehouse = api.isAssetInWarehouse("VSA43A56EEEWR");

        //then
        assertTrue("Asset should be found in warehouse", assetInWarehouse);
    }

    @Test
    public void shouldFindExpiredWarrantyAssetsSerialNumbers() {
        //when
        List<String> expiredWarrantyAssetsSerialNumbers = api.findExpiredWarrantyAssetsSerialNumbers();

        //then
        assertEquals("Number of assets with expired warranty is not correct", 1, expiredWarrantyAssetsSerialNumbers.size());
        assertEquals("Expired warranty asset serial number is not correct", "VSADW5WEEEWWW", expiredWarrantyAssetsSerialNumbers.get(0));
    } 
     
}
