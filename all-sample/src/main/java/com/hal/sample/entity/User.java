package com.hal.sample.entity;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Steven.HUANG on 2018/12/13.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = -5208730969316363525L;

  private Long id;

  private String name;

  private Integer age;

  private String address;

  private String mobile;

  private LocalDate birthday;

  private Integer gender;

  private String email;

}
