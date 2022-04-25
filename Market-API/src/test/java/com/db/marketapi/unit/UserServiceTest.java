package com.db.marketapi.unit;

import com.db.marketapi.model.Users;
import com.db.marketapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {
  @Autowired UserService userService;

  @Test
  public void whenCreateUser_ThenReturnDiffersNull() {
    Users user = new Users();

    Assertions.assertNotNull(userService.createUser(user));
  }
}
