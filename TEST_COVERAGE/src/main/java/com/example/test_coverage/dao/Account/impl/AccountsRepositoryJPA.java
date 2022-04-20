package com.example.test_coverage.dao.Account.impl;

import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepositoryJPA extends JpaRepository<Account, Integer> {
    Account getAccountByCardsContains(Card card);
    Account getAccountByIbanAndId(String iban, Integer id);
}
