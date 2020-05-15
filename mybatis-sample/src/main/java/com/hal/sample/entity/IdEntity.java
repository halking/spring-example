package com.hal.sample.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

@Data
public class IdEntity implements Serializable {

  private static final long serialVersionUID = -4133139590477768134L;

  @TableId
  protected Long id;
}
