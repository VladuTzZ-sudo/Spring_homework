package com.dbcloud.curs11.controller;

import com.dbcloud.curs11.dao.account.Account;
import com.dbcloud.curs11.service.AccountService;
import com.dbcloud.curs11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;

    @PostMapping("/users/{id}")
    public void addAccount(
            @PathVariable(name = "id") int id,
            @RequestBody Account account) {
        userService.getById(id).getAccounts().add(account);
        accountService.createAccount(account);
    }
}
