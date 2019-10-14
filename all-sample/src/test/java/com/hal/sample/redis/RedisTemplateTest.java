package com.hal.sample.redis;

import static com.google.common.collect.Lists.newArrayList;

import com.hal.sample.entity.Role;
import com.hal.sample.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by Steven.HUANG on 2019/4/8.
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class RedisTemplateTest {

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  private User user;

  private List<Role> roles;

  //  @Before
  public void setUp() {
    Role role1 = Role.builder().name("ROLE_A").build();
    Role role2 = Role.builder().name("ROLE_B").build();
    roles = newArrayList(role1, role2);
    user = User.builder().name("test").age(16).roles(roles).build();
  }

  //  @Test
  public void stringType() {
    redisTemplate.opsForValue().set("user", user);
    redisTemplate.opsForValue().set("roles", roles);
    User keyUser = (User) redisTemplate.opsForValue().get("user");
    List<Role> keyRoles = (List<Role>) redisTemplate.opsForValue().get("roles");
    System.out.println(keyUser);
    System.out.println(keyRoles);

  }

  //  @Test
  public void isOverWriter() {
    User user = new User();
    redisTemplate.opsForValue().set("test", "test1");
    redisTemplate.opsForValue().set("test", "test2");
    System.out.println(redisTemplate.opsForValue().get("test"));
    System.out.println(redisTemplate.opsForValue().get("dkt:video:category:Basketball"));
  }


}
