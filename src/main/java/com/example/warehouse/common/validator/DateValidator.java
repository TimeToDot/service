package com.example.warehouse.common.validator;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;

@Slf4j
public class DateValidator {

  private final DateFormat formatter;

  public DateValidator(DateFormat formatter) {
    this.formatter = formatter;
  }

  public boolean isValid(String dateStr) {
    if (dateStr == null){
      return false;
    }

    try {
      formatter.parse(dateStr);
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
      return false;
    }
    return true;
  }

  public DateFormat getFormatter() {
    return formatter;
  }
}