package com.db.marketapi.service;

import com.db.marketapi.model.Users;
import com.db.marketapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;

  public Users createUser(Users user) {
    return userRepository.save(user);
  }

  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }

  public List<Users> getAllUsersSortedByNumOfOrders() {
    return userRepository.findByOrderByNumOfOrdersAsc();
  }

  public Users updateUser(Users user) {
    return userRepository.save(user);
  }

  public List<Users> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<Users> getUserById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return new User("daniel", "bolontoc", new ArrayList<>());
  }
}
