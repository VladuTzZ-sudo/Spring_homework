package com.example.tema_bean.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String city;
    @NotNull
    private String country;

    @OneToMany(cascade = CascadeType.ALL)
    List<Order> orderList;
}
