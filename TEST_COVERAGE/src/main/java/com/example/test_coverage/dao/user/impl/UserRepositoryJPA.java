package com.example.test_coverage.dao.user.impl;

import com.example.test_coverage.dao.card.Card;
import com.example.test_coverage.dao.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, Integer> {
    public List<User> findByNameAndRole(String name, String role);
    public List<User> findByName(String name);
    public List<User> findByRole(String role);
    public List<User> findUserByCardsContaining(Card card);
}
