package com.hal.sample.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

  private Long id;

  private String ldapName;

  private String displayName;

  private String boutiqueId;

  private String role;

  private String boutiqueName;

  private String avatar;

  @JsonProperty(value = "staff_id")
  private String staffId;

  private String mpUserId;

}