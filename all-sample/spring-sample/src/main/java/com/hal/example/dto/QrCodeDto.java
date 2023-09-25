package com.hal.example.dto;

import com.hal.sample.util.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Steven.HUANG on 2019/1/31.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QrCodeDto {

  @ExcelField(title = "source")
  private String source;

  @ExcelField(title = "store")
  private String storeNumber;

  @ExcelField(title = "channel")
  private String channel;

  @ExcelField(title = "ticket")
  private String ticket;

  @ExcelField(title = "dataUrl")
  private String dataUrl;

  @ExcelField(title = "scene")
  private String scene;

}
