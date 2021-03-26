package com.hal.labs.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "salaries")
@ToString(exclude = "serialVersionUID")
public class Salaries implements Serializable {

  private static final long serialVersionUID = -6280182291698097906L;
  @Id
  @EqualsAndHashCode.Include
  private Long empNo;
  private Long salary;
  private LocalDate fromDate;
  private LocalDate toDate;


}
