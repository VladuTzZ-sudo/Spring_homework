package com.example.test_coverage.dao.card;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "number", nullable = false, unique = true)
    private Long number;

    @Column(name = "CVV", nullable = false, unique = true)
    private int cvv;

    @Column(name = "expiration_date", nullable = false, unique = true)
    private Date expirationDate;

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
