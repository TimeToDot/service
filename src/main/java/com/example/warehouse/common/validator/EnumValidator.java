package com.example.warehouse.common.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator {
  private final List<String> acceptedValues;

  public EnumValidator(Class<? extends Enum<?>> anEnum) {
    acceptedValues = Stream.of(anEnum.getEnumConstants())
      .map(Enum::name)
      .collect(Collectors.toList());
  }

  public boolean isValid(String value) {
    if (value == null){
      return false;
    }
    return acceptedValues.contains(value);
  }
}