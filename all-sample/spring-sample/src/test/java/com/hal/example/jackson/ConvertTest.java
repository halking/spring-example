package com.hal.example.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hal.sample.dto.AccountDto;
import com.hal.sample.dto.SnakeDto;
import com.hal.sample.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.SortedMap;

/**
 * @Author: Steven HUANG
 * @Date: 2019/6/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConvertTest {

  @Test
  public void snakeToCamel() throws Exception {
    String json = "{\"plan_id\": 1,\"plan_name\": \"default purchase plan\",\"start_time\": \"2018-01-01T00:00:00+08:00\",\"formula\": \"orderamount*1\"}";
    SnakeDto snakeDto = JsonUtil.jsonToObject(json, SnakeDto.class);
    System.out.println(snakeDto);
  }

  @Test
  public void beanToMap() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    AccountDto account = AccountDto.builder().ldapName("test").role("role_test").boutiqueName("name").staffId("staff")
        .build();
    Map<String, Object> map = objectMapper.readValue(objectMapper.writeValueAsString(account), SortedMap.class);

    System.out.println("map :" + map);
  }
}
