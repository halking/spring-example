package com.hal.sample.sql;

import com.hal.sample.common.Constant;
import com.hal.sample.enums.Segment;
import com.hal.sample.enums.SystemGroupEnum;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/3/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlTest {

  private long startDate;

  private Long endDate;

  @Before
  public void setup() {
    startDate = LocalDate.of(1970, 1, 1).toEpochDay();
    endDate = LocalDate.of(1998, 12, 12).toEpochDay();

  }

  @Test
  public void generateCustomerSql() {
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i <= 200; i++) {
      builder.append("(");
      builder.append(i).append(",");

      if (i < 100) {
        builder.append("'").append("full_name_").append(i).append("'").append(",");
        builder.append("'").append("cdb_num_").append(i).append("'").append(",");
        builder.append("'").append("mobile_").append(i).append("'").append(",");
      } else {
        builder.append("'").append(i).append("_full_name").append("'").append(",");
        builder.append("'").append(i).append("_cdb_num").append("'").append(",");
        builder.append("'").append(i).append("_mobile").append("'").append(",");
      }

      builder.append("'").append(Segment.randomValue().name()).append("'").append(",");

      builder.append("'").append(randomYear()).append("'").append(",");
      builder.append("'").append(randomMonthDay()).append("'").append("),");

      builder.append("\n");
    }

    System.out.println(builder.toString());
  }

  @Test
  public void generateRelSql() {
    StringBuilder builder = new StringBuilder();
    int k = 0;
    for (int j = 1; j <= 4; j++) {
      for (int i = 1; i <= 200; i++) {
        builder.append("(");
        builder.append(++k).append(",");
        builder.append(i).append(",");
        builder.append(j).append(",");

        SystemGroupEnum group = SystemGroupEnum.randomValue();
        builder.append("'").append(group.name()).append("'").append(",");
        builder.append(1).append(",");

        int reference = 0;
        int chat = 0;
        int bound = 0;
        int sales = 0;
        switch (group) {
          case Prospect:
            if (i % 2 == 0) {
              bound = 1;
            }
            if (bound == 0) {
              chat = 1;
            }
            builder.append(reference).append(",").append(chat).append(",")
                .append(bound).append(",").append(sales).append(",");
            break;
          case NHP:
            if (i % 2 == 0) {
              reference = 1;
            }
            if (reference == 0) {
              sales = 1;
            }
            builder.append(reference).append(",").append(chat).append(",")
                .append(bound).append(",").append(sales).append(",");
            break;
          case TOP70:
            reference = 1;
            builder.append(reference).append(",").append(chat).append(",")
                .append(bound).append(",").append(sales).append(",");
            break;
          case Opportunities:
            if (i % 2 == 0) {
              reference = 1;
            }
            if (reference == 0) {
              sales = 1;
            }
            builder.append(reference).append(",").append(chat).append(",")
                .append(bound).append(",").append(sales).append(",");
            break;
          case Other:
            chat = 1;
            builder.append(reference).append(",").append(chat).append(",")
                .append(bound).append(",").append(sales).append(",");
            break;
          default:
            break;
        }
        if (200<k && k<=302) {
          String randomStr = RandomStringUtils.randomAlphanumeric(10);
          builder.append("'").append(randomStr).append("'");
        }else {
          builder.append((String) null);
        }
        builder.append("),");
        builder.append("\n");
      }
    }

    System.out.println(builder.toString());
  }

  public String randomMonthDay() {
    long random = ThreadLocalRandom.current().nextLong(startDate, endDate);
    LocalDate birthDay = LocalDate.ofEpochDay(random);
    MonthDay monthDay = MonthDay.of(birthDay.getMonthValue(), birthDay.getDayOfMonth());
    String monthDayStr = monthDay.format(DateTimeFormatter.ofPattern(Constant.MMdd));

    return monthDayStr;
  }

  public String randomYear() {
    long random = ThreadLocalRandom.current().nextLong(startDate, endDate);
    LocalDate birthDay = LocalDate.ofEpochDay(random);
    return String.valueOf(birthDay.getYear());
  }

  @Test
  public void randomEnum() {
    System.out.println(SystemGroupEnum.randomValue());
  }

}
