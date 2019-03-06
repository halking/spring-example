package com.hal.sample.sql;

import java.util.Random;
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


  @Test
  public void generateCustomerSql() {
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i <= 200; i++) {
      builder.append("(");
      builder.append(i).append(",");
      builder.append("full_name_").append(i).append(",");
      builder.append("cdb_num_").append(i).append(",");
      builder.append("mobile_").append(i).append(",");
      if (i % 2 == 0) {
        builder.append("VIP").append(",");
      } else {
        builder.append("NORMAL").append(",");
      }
      Random rndYear=new Random();
      int year=rndYear.nextInt(30)+1970;
      builder.append(year).append(",");

      Random rndMonth=new Random();
      int month=rndMonth.nextInt(12)+1;   //生成[1,12]的整数；月
      Random rndDay=new Random();
      int day=rndDay.nextInt(30)+1;
      builder.append(String.format("%02d", month)).append("/")
          .append(String.format("%02d", day)).append("),");

      builder.append("\n");
    }

    System.out.println(builder.toString());
  }

  @Test
  public void generateAccountSql() {
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i <= 200; i++) {
      builder.append("(");
      builder.append(i).append(",");
      builder.append("'").append("full_name_").append(i).append("'").append(",");
      builder.append("'").append("cdb_num_").append(i).append("'").append(",");
      builder.append("'").append("mobile_").append(i).append("'").append(",");
      if (i % 2 == 0) {
        builder.append("'").append("VIP").append("'").append(",");
      } else {
        builder.append("'").append("NORMAL").append("'").append(",");
      }
      Random rndYear=new Random();
      int year=rndYear.nextInt(30)+1970;
      builder.append("'").append(year).append("'").append(",");

      Random rndMonth=new Random();
      int month=rndMonth.nextInt(12)+1;   //生成[1,12]的整数；月
      Random rndDay=new Random();
      int day=rndDay.nextInt(30)+1;
      builder.append("'").append(String.format("%02d", month)).append("/")
          .append(String.format("%02d", day)).append("'").append("),");

      builder.append("\n");
    }

    System.out.println(builder.toString());
  }

}
