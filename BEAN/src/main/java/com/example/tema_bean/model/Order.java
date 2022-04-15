package com.example.tema_bean.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    Customer customer;

    @OneToOne
    Payment payment;
}