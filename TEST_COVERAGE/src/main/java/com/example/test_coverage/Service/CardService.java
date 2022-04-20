package com.example.test_coverage.Service;

import com.example.test_coverage.dao.card.Card;
import com.example.test_coverage.dao.card.impl.CardsRepositoryJPA;
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

    public boolean deleteCards(Card card) {
        try {
            cardsRepositoryJPA.deleteById(card.getId());
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public Card modifyCard(Card card) {
        return cardsRepositoryJPA.save(card);
    }

    public boolean deleteAllCards() {
        try {
            cardsRepositoryJPA.deleteAll();
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }
}
