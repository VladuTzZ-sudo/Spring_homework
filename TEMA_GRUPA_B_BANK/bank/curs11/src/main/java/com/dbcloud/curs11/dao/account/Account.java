package com.dbcloud.curs11.dao.account;

import com.dbcloud.curs11.dao.card.Card;
import com.dbcloud.curs11.dao.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "IBAN", nullable = false, unique = true)
    private String iban;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Card> cards;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}
