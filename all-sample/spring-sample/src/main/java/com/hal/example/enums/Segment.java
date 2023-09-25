package com.hal.example.enums;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by Steven.HUANG on 2018/12/5.
 */
public enum Segment {

  ULTIMATE("ULTIMATE", "超级VIP", BigDecimal.valueOf(20000.00)),
  TOPVIP("TOP-VIP", "顶级VIP", BigDecimal.valueOf(20000.00)),
  HJVIP("HJ-VIP", "高端VIP", BigDecimal.valueOf(20000.00)),
  VIP("VIP", "普通VIP", BigDecimal.valueOf(50000.00)),
  INI("INI", "高端常客", BigDecimal.valueOf(20000.00)),
  REG("REG", "普通常客", BigDecimal.valueOf(20000.00)),
  RIS("RIS", "高端稀客", BigDecimal.valueOf(20000.00)),
  ACC("ACC", "普通稀客", BigDecimal.valueOf(20000.00)),
  SLEEPING("SLEEPING", "沉睡顾客", BigDecimal.valueOf(20000.00)),
  SVI("SVI", "SVI沉睡顾客", BigDecimal.valueOf(20000.00)),
  SLI("SLI", "SLI沉睡顾客", BigDecimal.valueOf(50000.00)),
  SLR("SLR", "SLR沉睡顾客", BigDecimal.valueOf(50000.00)),
  SLS("SLS", "SLS沉睡顾客", BigDecimal.valueOf(50000.00)),
  SLA("SLA", "SLA沉睡顾客", BigDecimal.valueOf(50000.00)),
  NHP("NHP(", "潜力顾客", BigDecimal.valueOf(50000.00)),
  NOV("NOV", "", BigDecimal.valueOf(50000.00)),
  Other("Other", "其他", BigDecimal.valueOf(50000.00));

  private String code;

  private String desc;

  private BigDecimal budget;

  Segment(String code, String desc, BigDecimal budget) {
    this.code = code;
    this.desc = desc;
    this.budget = budget;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
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

  public static String getCodeByDesc(String desc) {
    if (isBlank(desc)) {
      return null;
    }
    for (Segment segment : Segment.values()) {
      if (StringUtils.equalsIgnoreCase(segment.getDesc(), desc)) {
        return segment.getCode();
      }
    }

    return null;
  }

  public static Segment randomValue() {
    return values()[(int) (Math.random() * values().length)];
  }

}
