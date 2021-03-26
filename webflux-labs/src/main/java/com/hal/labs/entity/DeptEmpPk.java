package com.hal.labs.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: Steven HUANG
 * @Date: 2021/3/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DeptEmpPk implements Serializable {

  private Long empNo;
  private String deptNo;

}
