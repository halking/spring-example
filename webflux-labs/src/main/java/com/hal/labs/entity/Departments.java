package com.hal.labs.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "departments")
@ToString(exclude = "serialVersionUID")
public class Departments implements Serializable {

  private static final long serialVersionUID = -7095257294010507588L;
  @Id
  @EqualsAndHashCode.Include
  private String deptNo;
  private String deptName;

}
