package com.dbcloud.curs11.service;

import com.dbcloud.curs11.dao.card.Card;
import com.dbcloud.curs11.dao.user.User;
import com.dbcloud.curs11.dao.user.impl.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepositoryJPA userRepositoryJPA;

    public List<User> getAll(String role, String name) {
        if (name != null && role != null) {
            return this.userRepositoryJPA.findByNameAndRole(name, role);
        }
        if (role != null) {
            return this.userRepositoryJPA.findByRole(role);
        }
        if (name != null) {
            return this.userRepositoryJPA.findByName(name);
        }
        return this.userRepositoryJPA.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public User getById(int id) {
        return this.userRepositoryJPA.getById(id);
    }

    public void createOrModifyUser(User user) {
        this.userRepositoryJPA.save(user);
    }

    public void addCard(Card card, Integer id) {
        User user = userRepositoryJPA.getById(id);
        user.getCards().add(card);
        this.userRepositoryJPA.save(user);
    }
}
