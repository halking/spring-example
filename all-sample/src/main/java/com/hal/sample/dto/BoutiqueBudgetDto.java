package com.hal.sample.dto;

import com.hal.sample.util.ExcelField;
import java.math.BigDecimal;
import lombok.Data;

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
