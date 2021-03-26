package com.hal.labs.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "dept_emp")
@ToString(exclude = "serialVersionUID")
@IdClass(DeptEmpPk.class)
public class DeptEmp implements Serializable {

  private static final long serialVersionUID = -5348605796608852489L;
  @Id
  @EqualsAndHashCode.Include
  private Long empNo;
  @Id
  @EqualsAndHashCode.Include
  private String deptNo;
  private LocalDate fromDate;
  private LocalDate toDate;

}
