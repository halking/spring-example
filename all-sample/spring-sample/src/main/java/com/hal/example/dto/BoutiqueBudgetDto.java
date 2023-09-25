package com.hal.example.dto;

import com.hal.sample.util.ExcelField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Steven HUANG
 * @Date: 2019/4/28
 */
@Data
public class BoutiqueBudgetDto {

  @ExcelField(title = "boutiqueCode")
  private String boutiqueCode;

  @ExcelField(title = "budget")
  private BigDecimal budget;

  @ExcelField(title = "remaining")
  private BigDecimal remaining;
}
