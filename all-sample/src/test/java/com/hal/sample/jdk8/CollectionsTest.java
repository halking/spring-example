package com.hal.sample.jdk8;

import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;

import com.google.common.collect.Lists;
import com.hal.sample.entity.Role;
import com.hal.sample.entity.User;
import com.hal.sample.enums.SourceType;
import com.hal.sample.util.JsonUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2019/1/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionsTest {

  private List<User> users;

  @Before
  public void setup() {
    Role role1 = Role.builder().name("Admin").build();
    Role role2 = Role.builder().name("Bm").build();
    Role role3 = Role.builder().name("Waiter").build();

    User user1 = User.builder().name("name1").birthday(LocalDate.now()).gender(1).age(18)
        .sourceType(SourceType.Reference).role(role1)
        .createTime(LocalDateTime.of(2019, 1, 8, 12, 30, 0)).build();
    User user2 = User.builder().name("name2").birthday(LocalDate.now()).gender(0).age(22)
        .sourceType(SourceType.Bind).role(role2)
        .createTime(LocalDateTime.of(2019, 1, 7, 12, 30, 0)).build();
    User user3 = User.builder().name("name3").birthday(LocalDate.now()).gender(1).age(32)
        .sourceType(SourceType.Sale)
        .createTime(LocalDateTime.of(2019, 1, 6, 10, 30, 0)).build();
    User user4 = User.builder().name("name4").birthday(LocalDate.now()).gender(0).age(19)
        .sourceType(SourceType.Sale).role(role3)
        .createTime(LocalDateTime.of(2019, 1, 8, 13, 30, 0)).build();
    User user5 = User.builder().name("name5").birthday(LocalDate.now()).gender(1).age(30)
        .sourceType(SourceType.Chat).role(role2)
        .createTime(LocalDateTime.of(2018, 1, 8, 12, 30, 0)).build();
    User user6 = User.builder().name("name6").birthday(LocalDate.now()).gender(0).age(20)
        .sourceType(SourceType.Chat).build();

    users = Lists.newArrayList(user1, user2, user3, user4, user5, user6);
  }

  @Test
  public void sort() throws Exception {
    Collections.sort(users, Comparator.comparing(User::getCreateTime, nullsLast(reverseOrder())));
    System.out.println(JsonUtil.objectToJson(users));

    Collections.sort(users, Comparator.comparing(user ->
            Optional.ofNullable(user.getRole()).map(role -> role.getName()).orElse(""),
        nullsLast(reverseOrder())));
    System.out.println(JsonUtil.objectToJson(users));
  }

  @Test
  public void add() {
    User user = User.builder().name("name6").birthday(LocalDate.now()).gender(0).age(20)
        .sourceType(SourceType.Chat).build();
    List<User> users = Lists.newArrayList(user);
    user.setId(1L);

    for (User user1 : users) {
      System.out.println(user1);
    }
  }

}
