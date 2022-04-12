package com.dbcloud.curs11.service;

import com.dbcloud.curs11.dao.card.Card;
import com.dbcloud.curs11.dao.card.impl.CardsRepositoryJPA;
import com.dbcloud.curs11.dao.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardsRepositoryJPA cardsRepositoryJPA;

    public boolean createCard(Card card) {
        if (!cardsRepositoryJPA.existsById(card.getId())) {
            cardsRepositoryJPA.save(card);
            return true;
        }
        return false;
    }

    public Card getCardById(Integer id) {
        Optional<Card> card = cardsRepositoryJPA.findById(id);
        return card.orElse(null);
    }

    public void deleteCards(Integer id) {
        cardsRepositoryJPA.deleteById(id);
    }

    public void modifyCard(Card card) {
        cardsRepositoryJPA.save(card);
    }
}
