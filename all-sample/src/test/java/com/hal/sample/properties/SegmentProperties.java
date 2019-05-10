package com.hal.sample.properties;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Steven.HUANG on 2019/3/27.
 */
@Component
@ConfigurationProperties(prefix = "segment")
@Data
public class SegmentProperties {

  private Map<String,SegmentInfo> details = new HashMap<>();

  @Data
  public static class SegmentInfo {

    private String description;

    private BigDecimal budget;

  }
}
