package com.example.cursul1_advanced;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Integer> {

}
