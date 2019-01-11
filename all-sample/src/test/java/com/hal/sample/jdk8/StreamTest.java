package com.hal.sample.jdk8;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import com.google.common.collect.Lists;
import com.hal.sample.entity.User;
import com.hal.sample.enums.SourceType;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
public class StreamTest {
  private List<User> users;

  @Before
  public void setup(){
    User user1 = User.builder().name("name1").birthday(LocalDate.now()).gender(1).age(13)
        .sourceType(SourceType.Reference).build();
    User user2 = User.builder().name("name2").birthday(LocalDate.now()).gender(0).age(15)
        .sourceType(SourceType.Reference).build();
    User user3 = User.builder().name("name3").birthday(LocalDate.now()).gender(1).age(24)
        .sourceType(SourceType.Bind).build();
    User user4 = User.builder().name("name4").birthday(LocalDate.now()).gender(0).age(27)
        .sourceType(SourceType.Chat).build();

    User user5 = User.builder().name("name5").birthday(LocalDate.now()).gender(1).age(32)
        .sourceType(SourceType.Sale).build();

    users = Lists.newArrayList(user1,user2,user3,user4,user5);
  }

  @Test
  public void filter(){
    List<User> filters = users.stream().filter(user -> user.getGender().equals(0))
        .collect(Collectors.toList());


    MatcherAssert.assertThat(filters.size(), is(2));

    MatcherAssert.assertThat(filters, Matchers.everyItem(
        hasProperty("gender",is(0))
    ));
  }

  @Test
  public void peek(){
    users.stream().filter(user -> user.getGender().equals(0))
        .peek(user -> System.out.println("Filtered values: "+user.getName()))
        .collect(Collectors.toList());
  }
}
