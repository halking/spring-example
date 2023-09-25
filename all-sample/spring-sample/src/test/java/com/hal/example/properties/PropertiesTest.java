package com.hal.example.properties;

import com.hal.sample.properties.SegmentProperties.SegmentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Created by Steven.HUANG on 2019/3/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

  private static String apply(Entry<String, SegmentInfo> o) {
    return o.getValue().getDescription();
  }

  @Test
  public void mapPro() {
    System.out.println(PropertiesHelp.getProperties().getDetails());
    System.out.println(PropertiesHelp.getProperties().getDetails().get("VIP"));
    System.out.println(PropertiesHelp.getProperties().getDetails().get("VIP").getBudget());
    System.out.println(PropertiesHelp.getProperties().getDetails().get("VIP").getDescription());
    System.out.println(PropertiesHelp.getProperties().getDetails().get("NORMAL"));
    System.out.println(PropertiesHelp.getProperties().getDetails().get("NORMAL").getBudget());
    System.out.println(PropertiesHelp.getProperties().getDetails().get("NORMAL").getDescription());

    Map<String, SegmentInfo> details = PropertiesHelp.getProperties().getDetails();

    Map<String, String> result = details.entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, PropertiesTest::apply, (ov, nv) -> ov));

    System.out.println(result);
  }
}
