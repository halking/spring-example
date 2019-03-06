package com.hal.sample.entity;

import com.hal.sample.enums.SourceType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

  private SourceType sourceType;

  private LocalDateTime createTime;

  private Role role;

}
