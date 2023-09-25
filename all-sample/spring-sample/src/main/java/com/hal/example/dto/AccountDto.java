package com.hal.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

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

  public static void main(String[] args) {
      System.out.println(org.springframework.util.DigestUtils.md5DigestAsHex("13967167593".getBytes()));
      System.out.println(org.springframework.util.DigestUtils.md5DigestAsHex("13967167593".getBytes(StandardCharsets.UTF_8)));
  }

}
