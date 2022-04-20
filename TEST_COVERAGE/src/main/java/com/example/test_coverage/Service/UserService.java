package com.example.test_coverage.Service;

import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.card.Card;
import com.example.test_coverage.dao.user.User;
import com.example.test_coverage.dao.user.impl.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class UserService {
    @Autowired
    UserRepositoryJPA userRepositoryJPA;
    @Autowired
    AccountService accountService;
    @Autowired
    CardService cardService;

    public List<User> getAll(String role, String name) {
        if (name != null && role != null) {
            return this.userRepositoryJPA.findByNameAndRole(name, role);
        }
        if (role != null) {
            return this.userRepositoryJPA.findByRole(role);
        }
        if (name != null) {
            return this.userRepositoryJPA.findByName(name);
        }
        return this.userRepositoryJPA.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }

    public User getById(int id) {
        return this.userRepositoryJPA.getById(id);
    }

    public User createOrModifyUser(User user) {
        return this.userRepositoryJPA.save(user);
    }

    public User addCard(Card card, Integer id) {
        User user = userRepositoryJPA.getById(id);
        user.getCards().add(card);
        return this.userRepositoryJPA.save(user);
    }

    public User deleteUserCards(Integer id_user, Date... dates) {
        Optional<Date> date = Arrays.stream(dates).findFirst();

        ConcurrentLinkedQueue<Card> cardsForRemove = new ConcurrentLinkedQueue<>();
        User user = userRepositoryJPA.getById(id_user);
        date.ifPresent(value ->
                user.getCards().removeIf(card -> {
                    if (card.getExpirationDate().before(value)) {
                        cardsForRemove.add(card);

                        Account account = accountService.getAccountByContainingCard(card);
                        account.getCards().remove(card);
                        accountService.saveAccount(account);
                        return true;
                    }
                    return false;
                }));

        if (date.isEmpty()) {
            cardsForRemove.addAll(user.getCards());
            for (Account account : user.getAccounts()) {
                for (Card cardForDelete : user.getCards()) {
                    account.getCards().remove(cardForDelete);
                }
                accountService.saveAccount(account);
            }
            user.getCards().clear();
        }

        for (Card card : cardsForRemove) {
            cardService.deleteCards(card);
        }

        return userRepositoryJPA.save(user);
    }

    public boolean addCardToAccountOfUser(Integer id_account, Integer id_user, Card card) {
        User user = getById(id_user);
        if (user.getAccounts().contains(accountService.getAccountById(id_account))) {
            accountService.getAccountById(id_account).getCards().add(card);
            addCard(card, id_user);
            createOrModifyUser(user);
            return true;
        }

        return false;
    }
}
