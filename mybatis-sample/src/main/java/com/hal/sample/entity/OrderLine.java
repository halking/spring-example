package com.hal.sample.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class OrderLine extends BaseEntity {

  private static final long serialVersionUID = 9087989888612068684L;

  private Long orderId;

  private String name;

  private String specValue;

  private String imageUrl;

  private String serialNumber;

  private Integer quantity;

  private BigDecimal marketPrice;

  private BigDecimal actualPrice;

  private String status;

  private BigDecimal discountAmount;

  private JsonNode specValueJson;
}
