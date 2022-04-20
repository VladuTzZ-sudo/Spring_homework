package com.example.test_coverage.Service;

import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.Account.impl.AccountsRepositoryJPA;
import com.example.test_coverage.dao.card.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountsRepositoryJPA accountsRepositoryJPA;

    public boolean createAccount(Account account) {
        if (!accountsRepositoryJPA.existsById(account.getId())) {
            accountsRepositoryJPA.save(account);
            return true;
        }
        return false;
    }

    public Account getAccountById(Integer id) {
        Optional<Account> customer = accountsRepositoryJPA.findById(id);
        return customer.orElse(null);
    }

    public Account getAccountByIbanAndId(Account account) {
        return accountsRepositoryJPA.getAccountByIbanAndId(account.getIban(), account.getId());
    }

    public Account getAccountByContainingCard(Card card) {
        return accountsRepositoryJPA.getAccountByCardsContains(card);
    }

    public boolean saveAccount(Account account) {
        try {
            accountsRepositoryJPA.save(account);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
