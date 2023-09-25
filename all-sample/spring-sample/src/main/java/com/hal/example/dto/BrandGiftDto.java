package com.hal.example.dto;

import com.hal.sample.util.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Steven.HUANG on 2019/1/31.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandGiftDto {

  private Long id;

  private Integer outboundStock;

  @ExcelField(title = "inboundStock")
  private Integer inboundStock;

  private Integer leftStockOfLastMonth;

  private Integer leftStockOfCurrentMonth;

  private String boutiqueName;

  @ExcelField(title = "name")
  private String name;

  @ExcelField(title = "code")
  private String code;

  @ExcelField(title = "price")
  private BigDecimal price;

  private Integer stocks;

  private Boolean enable;

  @ExcelField(title = "imageUrl")
  private String imageUrl;

  private String uuid;

  @ExcelField(title = "boutiqueCode")
  private String boutiqueCode;

  @ExcelField(title = "type")
  private String type;
}
