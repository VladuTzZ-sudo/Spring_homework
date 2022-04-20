package com.example.test_coverage.dao.card.impl;

import com.example.test_coverage.dao.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

import java.sql.Date;

public interface CardsRepositoryJPA extends JpaRepository<Card, Integer> {
    void removeCardById(Integer id);
}
