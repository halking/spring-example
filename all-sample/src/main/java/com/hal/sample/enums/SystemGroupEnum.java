package com.hal.sample.enums;

public enum SystemGroupEnum {
  TOP70,
  NHP,
  Opportunities,
  Prospect,
  Other;

  public static SystemGroupEnum from(String value) {
    for (SystemGroupEnum type : values()) {
      if (type.name().equalsIgnoreCase(value)) {
        return type;
      }
    }
    return null;
  }

  public static SystemGroupEnum randomValue() {
    return values()[(int) (Math.random() * values().length)];
  }
}
