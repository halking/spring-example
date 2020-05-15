package com.hal.sample.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class Order extends BaseEntity {

  private static final long serialVersionUID = -96067843549090265L;

  private Long customerId;

  private String orderNumber;

  private String refundNumber;

  private LocalDateTime transactionDate;

  private String expressNumber;

  private String expressCompany;

  private String returnExpressNumber;

  private BigDecimal totalAmount;

  private BigDecimal discountAmount;

  private BigDecimal amountPayable;

  private String status;

  private LocalDateTime expireTime;

}
