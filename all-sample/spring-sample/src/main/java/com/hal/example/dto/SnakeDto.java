package com.hal.example.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @Author: Steven HUANG
 * @Date: 2019/6/10
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SnakeDto {

  private Long planId;

  private String planName;

  private String startTime;

  private String formula;
}
