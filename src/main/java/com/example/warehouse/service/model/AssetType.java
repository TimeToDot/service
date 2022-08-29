package com.example.warehouse.service.model;

public enum AssetType {
  MOBILE_PHONE("MOBILE_PHONE"),
  TABLET("TABLET"),
  COMPUTER("COMPUTER"),
  WATCH("WATCH"),
  TV("TV"),
  SERVER("SERVER");


  private final String message;

  AssetType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
