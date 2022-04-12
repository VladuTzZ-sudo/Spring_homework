package com.example.tema_repo_cookie.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ProductsDTO {
    Integer id;
    Integer orderId;
    @NotNull
    int price;
}
