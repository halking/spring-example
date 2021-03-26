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
@Table(name = "employees")
@ToString(exclude = "serialVersionUID")
public class Employees implements Serializable {

  private static final long serialVersionUID = 637227708934494734L;
  @Id
  @EqualsAndHashCode.Include
  private Long empNo;
  private LocalDate birthDate;
  private String firstName;
  private String lastName;
  private String gender;
  private LocalDate hireDate;


}
