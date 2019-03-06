package com.hal.sample.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Steven.HUANG on 2019/1/22.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

  private Long id;

  private String name;

  private String parentId;

  private Boolean enabled;
}
