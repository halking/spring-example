package com.hal.sample.enums;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by Steven.HUANG on 2018/12/5.
 */
public enum Segment {

  Normal("Normal", BigDecimal.valueOf(20000.00)),
  VIP("VIP", BigDecimal.valueOf(50000.00)),
  Initiate("Initiate", BigDecimal.valueOf(50000.00)),
  Loyal("Loyal", BigDecimal.valueOf(50000.00)),
  RisingStar("RisingStar", BigDecimal.valueOf(50000.00)),
  Access("Access", BigDecimal.valueOf(50000.00)),
  NHP("NHP", BigDecimal.valueOf(50000.00)),
  Other("Other", BigDecimal.valueOf(50000.00));

  private String code;

  private BigDecimal budget;

  Segment(String code, BigDecimal budget) {
    this.code = code;
    this.budget = budget;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public static Optional<Segment> getSegmentBudget(String type) {
    if (isBlank(type)) {
      return Optional.empty();
    }
    for (Segment segment : Segment.values()) {
      if (segment.name().equalsIgnoreCase(type)) {
        return Optional.ofNullable(segment);
      }
    }

    return Optional.empty();
  }

  public static Segment randomValue() {
    return values()[(int) (Math.random() * values().length)];
  }

}
