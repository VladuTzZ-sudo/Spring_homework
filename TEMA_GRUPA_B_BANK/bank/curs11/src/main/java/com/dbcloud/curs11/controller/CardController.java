package com.dbcloud.curs11.controller;

import com.dbcloud.curs11.dao.card.Card;
import com.dbcloud.curs11.dao.user.User;
import com.dbcloud.curs11.service.AccountService;
import com.dbcloud.curs11.service.CardService;
import com.dbcloud.curs11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class CardController {
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;
    @Autowired
    AccountService accountService;

    @PostMapping("/users/{id_user}/account/{id_account}")
    public void addCardToSpecifiedAccount(
            @PathVariable(name = "id_user") int id_user,
            @PathVariable(name = "id_account") int id_account,
            @RequestBody Card card) {

        User user = userService.getById(id_user);
        if (user.getAccounts().contains(accountService.getAccountById(id_account))) {
            accountService.getAccountById(id_account).getCards().add(card);
            // Exista relatie intre tabele.
            userService.createOrModifyUser(user);
        }
    }

    // Eu la grupaB vin doar luni. Nu am fost atunci cand ati creat acest proiect. Nu am inteles de ce avem nevoie de lista de carduri atat pentru
    // fiecare account, cat si separat pentru utilizator. Am luat in considerare cazul in care aceste carduri, din clasa User vor fi create separat
    // printr-un request si sterse prin metoda de mai jos.
    // Daca ar fi vorba de aceleasi carduri, atunci de ce trebuie sa le salvam de mai multe ori in locuri diferite ?.
    // As fi putut face un for printre accounts si verifica toate listele de carduri. Am ales aceasta metoda. ( constrangere DELETE )
    @PostMapping("/users/cards/{id}")
    void addCardToUser(
            @PathVariable(name = "id") Integer id,
            @RequestBody Card card) {
        userService.addCard(card, id);
    }
}
