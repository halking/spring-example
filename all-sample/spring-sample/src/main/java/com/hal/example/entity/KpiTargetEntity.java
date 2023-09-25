package com.hal.example.entity;

import com.hal.sample.util.ExcelField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class KpiTargetEntity {

  @ExcelField(title = "boutiqueCode")
  private String boutiqueCode;

  @ExcelField(title = "prospectCollectPerMonth")
  private Integer prospectCollectPerMonth;

  @ExcelField(title = "prospectCommunicateRate")
  private Integer prospectCommunicateRate;

  @ExcelField(title = "prospectConvertRate")
  private Integer prospectConvertRate;

  @ExcelField(title = "opportunityPurchaseRate")
  private Integer opportunityPurchaseRate;

  @ExcelField(title = "opportunityCommunicateRate")
  private Integer opportunityCommunicateRate;

  @ExcelField(title = "opportunityPurchaseAmount")
  private BigDecimal opportunityPurchaseAmount;

  @ExcelField(title = "top70PurchaseRate")
  private Integer top70PurchaseRate;

  @ExcelField(title = "top70CommunicateRate")
  private Integer top70CommunicateRate;

  @ExcelField(title = "top70PurchaseAmount")
  private BigDecimal top70PurchaseAmount;
}
