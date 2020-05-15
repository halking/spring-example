package com.hal.sample.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseEntity extends IdEntity {

  private static final long serialVersionUID = 7363788322890057032L;

  protected LocalDateTime createTime;

  protected LocalDateTime updateTime;
}
