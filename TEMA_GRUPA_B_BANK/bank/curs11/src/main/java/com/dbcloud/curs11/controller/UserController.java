package com.dbcloud.curs11.controller;

import com.dbcloud.curs11.dao.account.Account;
import com.dbcloud.curs11.dao.card.Card;
import com.dbcloud.curs11.dao.user.User;
import com.dbcloud.curs11.service.AccountService;
import com.dbcloud.curs11.service.CardService;
import com.dbcloud.curs11.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAll(@RequestParam(name = "role", required = false) String role,
                             @RequestParam(name = "name", required = false) String name) {
        return userService.getAll(role, name);
    }

    @PostMapping("/users")
    void createCustomer(@RequestBody User user) {
        userService.createOrModifyUser(user);
    }

    @DeleteMapping("/users/cards/{id_user}")
    public void deleteAllExpiredCardsForTheUser(
            @PathVariable(name = "id_user") int id_user) {
        Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());

        User user = userService.getById(id_user);
        user.getCards().removeIf(card -> card.getExpirationDate().before(sqlDate));

        userService.createOrModifyUser(user);
    }
}
