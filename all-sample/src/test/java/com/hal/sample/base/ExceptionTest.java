package com.hal.sample.base;

import com.google.common.collect.Lists;
import com.hal.sample.entity.User;
import com.hal.sample.enums.SourceType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Steven.HUANG on 2019/1/11.
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@Slf4j
public class ExceptionTest {

  private List<User> users;

//  @Before
  public void setup(){
    User user1 = User.builder().name("name1").birthday(LocalDate.now()).gender(1).age(18)
        .sourceType(SourceType.Reference)
        .createTime(LocalDateTime.of(2019,1,8,12,30,0)).build();
    User user2 = User.builder().name("name2").birthday(LocalDate.now()).gender(0).age(22)
        .sourceType(SourceType.Bind)
        .createTime(LocalDateTime.of(2019,1,7,12,30,0)).build();
    User user3 = User.builder().name("name3").birthday(LocalDate.now()).gender(1).age(32)
        .sourceType(SourceType.Sale)
        .createTime(LocalDateTime.of(2019,1,6,10,30,0)).build();
    User user4 = User.builder().name("name4").birthday(LocalDate.now()).gender(0).age(19)
        .sourceType(SourceType.Sale)
        .createTime(LocalDateTime.of(2019,1,8,13,30,0)).build();
    User user5 = User.builder().name("name5").birthday(LocalDate.now()).gender(1).age(30)
        .sourceType(SourceType.Chat)
        .createTime(LocalDateTime.of(2018,1,8,12,30,0)).build();
    User user6 = User.builder().name("name6").birthday(LocalDate.now()).gender(0).age(20)
        .sourceType(SourceType.Chat).build();

    users = Lists.newArrayList(user1,user2,user3,user4,user5,user6);
  }

//  @Test
  public void forException(){
    for (User user : users) {
      try {
        System.out.println(user.getName());
        throw new RuntimeException();
      } catch (Exception e){
        log.error(e.getMessage(),e);
      }
    }
  }
  
}
