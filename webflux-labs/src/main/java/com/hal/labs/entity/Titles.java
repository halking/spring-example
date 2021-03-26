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
@Table(name = "titles")
@ToString(exclude = "serialVersionUID")
public class Titles implements Serializable {

  private static final long serialVersionUID = -5408493377983797232L;
  @Id
  @EqualsAndHashCode.Include
  private Long empNo;
  private String title;
  private LocalDate fromDate;
  private LocalDate toDate;
}
