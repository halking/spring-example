package com.hal.sample.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class Customer extends BaseEntity {

  private static final long serialVersionUID = -6961887567546002876L;

  private String name;

  private String mpOpenId;

  private String mpUnionId;

  private String nickname;

  private String avatarUrl;

  private Integer gender;

  private String email;

  private String mobile;

  private LocalDateTime lastLoginTime;

}
