package com.hal.sample.jdk8;

import com.google.common.collect.Lists;
import com.hal.sample.entity.User;
import com.hal.sample.enums.SourceType;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2018/12/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectorsTest {

  private List<User> users;

  @Before
  public void setup(){
    User user1 = User.builder().name("name1").birthday(LocalDate.now()).gender(1).age(18)
        .sourceType(SourceType.Reference).build();
    User user2 = User.builder().name("name2").birthday(LocalDate.now()).gender(0).age(22)
        .sourceType(SourceType.Bind).build();
    User user3 = User.builder().name("name3").birthday(LocalDate.now()).gender(1).age(32)
        .sourceType(SourceType.Sale).build();
    User user4 = User.builder().name("name4").birthday(LocalDate.now()).gender(0).age(19)
        .sourceType(SourceType.Sale).build();
    User user5 = User.builder().name("name5").birthday(LocalDate.now()).gender(1).age(30)
        .sourceType(SourceType.Chat).build();
    User user6 = User.builder().name("name6").birthday(LocalDate.now()).gender(0).age(20)
        .sourceType(SourceType.Chat).build();

    users = Lists.newArrayList(user1,user2,user3,user4,user5,user6);
  }

  @Test
  public void groupingBy(){
    Map<SourceType,List<User>> map = users.stream().collect(Collectors.groupingBy(
        user-> user.getSourceType(),Collectors.toList()));
    System.out.println(map);
  }

  @Test
  public void partitioning(){

  }

  @Test
  public void mapMerger(){

  }

  @Test
  public void reducing(){

  }

  @Test
  public void mapping(){

  }

  @Test
  public void summarizing(){

  }

  @Test
  public void averaging(){

  }

}
