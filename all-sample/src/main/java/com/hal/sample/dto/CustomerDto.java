package com.hal.sample.dto;

import com.hal.sample.util.ExcelField;
import lombok.Data;

/**
 * @Author: Steven HUANG
 * @Date: 2019/5/6
 */
@Data
public class CustomerDto {

  @ExcelField(title = "cdbNumber")
  private String cdbNumber;

  @ExcelField(title = "staffId")
  private String staffId;
}
