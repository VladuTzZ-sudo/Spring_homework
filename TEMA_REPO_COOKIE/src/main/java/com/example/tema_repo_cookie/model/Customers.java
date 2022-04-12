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
@Table(name = "customers")
public class Customers {
    @Id
    @NotNull
    @GeneratedValue
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private String password;
    @OneToMany
    public List<Orders> orders;
}
