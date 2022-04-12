package com.example.tema_repo_cookie.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Orders {
    @Id
    @NotNull
    @GeneratedValue
    private Integer id;
    @NotNull
    private String orderDate;
    private String shippedDate;
    @OneToMany
    public List<Products> productsList;
    @ManyToOne
    Customers customers;
}
