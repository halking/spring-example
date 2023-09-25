package com.hal.example.enums;

/**
 * Created by Steven.HUANG on 2018/12/12.
 */
public enum AdjustmentType {

  Add("Add", "新增TOP70"),
  Delete("Delete", "删减TOP70");

  private String type;

  private String description;

  AdjustmentType(String type, String description) {
    this.type = type;
    this.description = description;
  }
}
