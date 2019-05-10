package com.hal.sample.dto;

import com.hal.sample.util.ExcelField;
import lombok.Data;

/**
 * @Author: Steven HUANG
 * @Date: 2019/4/28
 */
@Data
public class CustomerSegmentDto {

  @ExcelField(title = "cdbNumber")
  private String cdbNumber;

  @ExcelField(title = "segment")
  private String segment;
}
