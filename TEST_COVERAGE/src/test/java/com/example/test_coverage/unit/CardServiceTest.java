package com.example.test_coverage.unit;

import com.example.test_coverage.Service.AccountService;
import com.example.test_coverage.Service.CardService;
import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.Account.impl.AccountsRepositoryJPA;
import com.example.test_coverage.dao.card.Card;
import com.example.test_coverage.dao.card.impl.CardsRepositoryJPA;
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
public class CardServiceTest {
    @Mock
    CardsRepositoryJPA cardsRepositoryJPA;

    @InjectMocks
    CardService cardService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testing createCard Method")
    public void createAccount() {
        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));

        Mockito.when(cardsRepositoryJPA.existsById(card.getId())).thenReturn(true);
        Mockito.when(cardsRepositoryJPA.save(card)).thenReturn(card);

        assertFalse(cardService.createCard(card));

        Mockito.when(cardsRepositoryJPA.existsById(card.getId())).thenReturn(false);
        assertTrue(cardService.createCard(card));
    }

    @Test
    @DisplayName("Card data")
    public void test_data() {
        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        assertEquals(299226, card.getCvv());
        assertEquals(2922262L, card.getNumber());
    }

    @Test
    @DisplayName("Testing getCardById Method")
    public void getCardById() {
        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));

        Mockito.when(cardsRepositoryJPA.findById(card.getId())).thenReturn(Optional.of(card));
        assertEquals(card, cardService.getCardById(card.getId()));
    }

    @Test
    @DisplayName("Testing deleteCards Method")
    public void deleteCards() {
        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));

        assertTrue(cardService.deleteCards(card));
    }

    @Test
    @DisplayName("Testing modifyCard Method")
    public void modifyCard() {
        Card card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));

        Mockito.when(cardsRepositoryJPA.save(card)).thenReturn(card);
        assertEquals(card, cardService.modifyCard(card));
    }

    @Test
    @DisplayName("Testing deleteAllCards Method")
    public void deleteAllCards() {
        assertTrue(cardService.deleteAllCards());
    }
}

