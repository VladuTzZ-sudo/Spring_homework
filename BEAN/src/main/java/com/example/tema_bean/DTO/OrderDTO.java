package com.example.tema_bean.DTO;

import com.example.tema_bean.model.Customer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    @NotNull
    private Double price;

    @ManyToOne
    Customer customer;
}
