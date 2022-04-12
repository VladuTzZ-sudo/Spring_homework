package com.example.tema_repo_cookie.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class CustomersDTO {
    @Id
    @NotNull
    @GeneratedValue
    Integer id;
    @NotNull
    String username;
    @NotNull
    String city;
    @NotNull
    String country;
    @NotNull
    String password;
}
