package com.hal.sample.jdk8;

import com.google.common.collect.Lists;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComparatorTest {

  @Test
  public void dateSort() {
    List<LocalDate> dateList = Lists.newArrayList(LocalDate.of(2019, 4, 1),
        LocalDate.of(2019, 3, 29), LocalDate.of(2019, 3, 27),
        LocalDate.of(2019, 3, 27));

    System.out.println(dateList.stream().distinct().sorted(Comparator.naturalOrder()).collect(
        Collectors.toList()));
  }
}