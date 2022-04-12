package com.dbcloud.curs11.service;

import com.dbcloud.curs11.dao.account.Account;
import com.dbcloud.curs11.dao.account.impl.AccountsRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountsRepositoryJPA accountsRepositoryJPA;

    public void createAccount(Account account) {
        if (!accountsRepositoryJPA.existsById(account.getId())) {
            accountsRepositoryJPA.save(account);
        }
    }

    public Account getAccountById(Integer id) {
        Optional<Account> customer = accountsRepositoryJPA.findById(id);
        return customer.orElse(null);
    }
}
