package com.dbcloud.curs11.dao.account.impl;

import com.dbcloud.curs11.dao.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepositoryJPA extends JpaRepository<Account, Integer> {
}
