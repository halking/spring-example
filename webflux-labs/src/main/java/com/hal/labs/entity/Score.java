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
@Table(name = "score")
@ToString(exclude = "serialVersionUID")
public class Score implements Serializable {

  private static final long serialVersionUID = -6865032565885482147L;
  @Id
  @EqualsAndHashCode.Include
  private Long id;
  private Double score;

}
