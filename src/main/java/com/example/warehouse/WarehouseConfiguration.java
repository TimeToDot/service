package com.example.warehouse;

import com.example.warehouse.service.DefaultWarehouseService;
import com.example.warehouse.service.WarehouseService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarehouseConfiguration {

  @Bean
  @ConditionalOnMissingBean(WarehouseService.class)
  public WarehouseService warehouseService() {
    return new DefaultWarehouseService();
  }
}