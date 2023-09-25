package com.hal.example.dto;

import com.hal.sample.util.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Steven HUANG
 * @Date: 2021/1/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MobileDto {

  @ExcelField(title = "card_no")
  private String cardCode;

  @ExcelField(title = "mobile")
  private String mobile;

  private String mobileMd5;
}
