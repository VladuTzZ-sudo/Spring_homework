package com.example.test_coverage.unit;

import com.example.test_coverage.Service.AccountService;
import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.Account.impl.AccountsRepositoryJPA;
import com.example.test_coverage.dao.card.Card;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountsRepositoryJPA accountsRepositoryJPA;

    @InjectMocks
    AccountService accountService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testing createAccount Method")
    public void createAccount() {
        Account account = new Account();
        account.setIban("IBAN_TEST6");
        account.setId(10026);

        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));
        Set<Card> set = new HashSet<>();
        set.add(card);
        account.setCards(set);

        Mockito.when(accountsRepositoryJPA.save(account)).thenReturn(account);
        Mockito.when(accountsRepositoryJPA.existsById(account.getId())).thenReturn(false);

        assertTrue(accountService.createAccount(account));

        Mockito.when(accountsRepositoryJPA.existsById(account.getId())).thenReturn(true);
        assertFalse(accountService.createAccount(account));
    }

    @Test
    @DisplayName("Testing getAccountById Method")
    public void getAccountById() {
        Account account = new Account();
        account.setIban("IBAN_TEST6");
        account.setId(10026);

        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));
        Set<Card> set = new HashSet<>();
        set.add(card);
        account.setCards(set);

        Mockito.when(accountsRepositoryJPA.findById(account.getId())).thenReturn(Optional.of(account));
        assertEquals(accountService.getAccountById(account.getId()), account);
    }

    @Test
    @DisplayName("Testing getAccountByIbanAndId Method")
    public void getAccountByIbanAndId() {
        Account account = new Account();
        account.setIban("IBAN_TEST6");
        account.setId(10026);

        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));
        Set<Card> set = new HashSet<>();
        set.add(card);
        account.setCards(set);

        Mockito.when(accountsRepositoryJPA.getAccountByIbanAndId(account.getIban(), account.getId())).thenReturn(account);
        assertEquals(accountService.getAccountByIbanAndId(account), account);
    }

    @Test
    @DisplayName("Testing getAccountByContainingCard Method")
    public void getAccountByContainingCard() {
        Account account = new Account();
        account.setIban("IBAN_TEST6");
        account.setId(10026);

        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));
        Set<Card> set = new HashSet<>();
        set.add(card);
        account.setCards(set);

        Mockito.when(accountsRepositoryJPA.getAccountByCardsContains(card)).thenReturn(account);
        assertEquals(accountService.getAccountByContainingCard(card), account);
    }

    @Test
    @DisplayName("Testing saveAccount Method")
    public void saveAccount() {
        Account account = new Account();
        account.setIban("IBAN_TEST6");
        account.setId(10026);

        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));
        Set<Card> set = new HashSet<>();
        set.add(card);
        account.setCards(set);

        Mockito.when(accountsRepositoryJPA.save(account)).thenThrow();
        assertFalse(accountService.saveAccount(account));
    }
}
