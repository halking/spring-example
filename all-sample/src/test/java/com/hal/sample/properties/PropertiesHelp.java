package com.hal.sample.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Steven.HUANG on 2019/3/28.
 */
@Component
public class PropertiesHelp {

  private static SegmentProperties properties;

  @Autowired
  public PropertiesHelp(SegmentProperties properties){
    PropertiesHelp.properties = properties;
  }

  public static SegmentProperties getProperties() {
    return properties;
  }
}
