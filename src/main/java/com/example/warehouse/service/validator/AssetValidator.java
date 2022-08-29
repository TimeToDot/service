package com.example.warehouse.service.validator;

import com.example.warehouse.common.validator.DateValidator;
import com.example.warehouse.common.validator.EnumValidator;
import com.example.warehouse.service.model.AssetType;
import com.example.warehouse.service.utils.DateUtil;

import java.text.SimpleDateFormat;

public class AssetValidator {
  private final DateValidator dateValidator;
  private final EnumValidator enumValidator;

  public AssetValidator() {
    this.dateValidator = new DateValidator(new SimpleDateFormat(DateUtil.DATE_FORMAT));
    this.enumValidator = new EnumValidator(AssetType.class);
  }

  public boolean isDataValid(String serialNumber, String assetType, String warrantyExpirationDate) {
    return isSerialNumberValid(serialNumber) &&
           dateValidator.isValid(warrantyExpirationDate) &&
           enumValidator.isValid(assetType);
  }

  public boolean isTypeValid(String assetType) {
    return enumValidator.isValid(assetType);
  }

  public boolean isSerialNumberValid(String serialNumber) {
    return serialNumber != null && serialNumber.length() == 13;
  }
}