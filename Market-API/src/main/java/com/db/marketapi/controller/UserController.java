package com.db.marketapi.controller;

import com.db.marketapi.model.*;
import com.db.marketapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
  private final UserService userService;

  @Autowired
  LoginService loginService;

  @PostMapping("create")
  public Users createUser(@RequestBody Users user) {
    return userService.createUser(user);
  }
  
  /* AICI ESTE PE METODA DE JWT FARA SPRING SECURITY. */
  @PostMapping("update")
  public Users updateUser(@RequestBody Users user,
                          @RequestHeader String token) {
    //List<String> token = headers.get("authorization");

    if (!token.isEmpty()){
      String payload = loginService.checkJWT(token);
      if (!Objects.equals(payload, "")){
        System.out.println("S-a modificat !");
        return userService.updateUser(user);
      }
    }
    return user;
  }

  @GetMapping("get-users-sorted-numOrders")
  public List<Users> getAllUsersSortedByNumOfOrders() {
    return userService.getAllUsersSortedByNumOfOrders();
  }

  @DeleteMapping("delete/{id}")
  public void deleteUserById(@PathVariable Long id) {
    userService.deleteUserById(id);
  }

  @GetMapping("/{id}")
  public Optional<Users> getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @GetMapping("/all")
  public List<Users> getAllUsers() {
    return userService.getAllUsers();
  }
}
