package com.hal.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Steven HUANG
 * @Date: 2019/7/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BindingMigration {

  private String boutiqueName;

  private String avatar;

  private String name;

  private String qrCode;

  private String ldapAccount;

  private String staffCode;

  private String cdbId;

  private String openId;

  private String unionId;

  private String status;

  private String boutiqueId;
}
