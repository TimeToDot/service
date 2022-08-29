package com.example.warehouse.service;

import com.example.warehouse.WarehouseConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
  classes = WarehouseConfiguration.class
)
class WarehouseServiceTest {

  @Autowired
  private WarehouseService service;

  @Test
  void assetsShouldNotBeAddedWhenSerialNumberIsNotValid(){
    //when
    service.addAsset("CXXWEW8WXCXCV", "MOBILE_PHONE", "13/12/2016");
    service.addAsset("CXXWEW8WXCXCVcdef", "MOBILE_PHONE", "13/12/2016");
    service.addAsset(null, "MOBILE_PHONE", "13/12/2016");
    //then
    Assertions.assertEquals(1, service.countAllAssets(), "Number of assets is not correct");
    //after
    service.removeAsset("CXXWEW8WXCXCV");
  }

  @Test
  void assetsShouldNotBeAddedWhenAssetTypeIsNotValid(){
    //when
    service.addAsset("CXXWEW8WXCXCV", "OTHER_PHONE", "13/12/2016");
    service.addAsset("CSDD22DSS4SWF", null, "22/12/2030");
    service.addAsset("SDSCS5W32D222", "COMPUTER", "21/12/2030");
    //then
    Assertions.assertEquals(1, service.countAllAssets(), "Number of assets is not correct");
    //after
    service.removeAsset("SDSCS5W32D222");
  }

  @Test
  void assetsShouldNotBeAddedWhenDateIsNotValid(){
    //when
    service.addAsset("CXXWEW8WXCXCV", "MOBILE_PHONE", "2016-09-08");
    service.addAsset("CSDD22DSS4SWF", "TABLET", null);
    service.addAsset("DSS23532323SD", "COMPUTER", "february");
    service.addAsset("SDSCS5W32D222", "COMPUTER", "21/12/2030");
    //then
    Assertions.assertEquals(1, service.countAllAssets(), "Number of assets is not correct");
    //after
    service.removeAsset("SDSCS5W32D222");
  }

  @Test
  void assetsShouldBeUpdatedWhenSerialNumberAlreadyExist(){
    //given
    service.addAsset("SDSCS5W32D222", "COMPUTER", "21/12/2030");
    //when
    service.addAsset("SDSCS5W32D222", "TABLET", "21/12/2030");
    //then
    Assertions.assertEquals(0, service.countByAssetType("COMPUTER"), "Number of assets is not correct");
    Assertions.assertEquals(1, service.countByAssetType("TABLET"), "Number of assets is not correct");
    //after
    service.removeAsset("SDSCS5W32D222");
  }

}