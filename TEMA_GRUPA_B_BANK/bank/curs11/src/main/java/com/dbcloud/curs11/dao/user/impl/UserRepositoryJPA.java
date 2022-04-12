package com.dbcloud.curs11.dao.user.impl;

import com.dbcloud.curs11.dao.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, Integer> {
    public List<User> findByNameAndRole(String name, String role);
    public List<User> findByName(String name);
    public List<User> findByRole(String role);
}
