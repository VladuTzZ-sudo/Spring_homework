package com.dbcloud.curs11.dao.card.impl;

import com.dbcloud.curs11.dao.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

import java.sql.Date;

public interface CardsRepositoryJPA extends JpaRepository<Card, Integer> {
}
