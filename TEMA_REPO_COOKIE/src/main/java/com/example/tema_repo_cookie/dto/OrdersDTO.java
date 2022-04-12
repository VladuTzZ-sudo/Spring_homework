package com.example.tema_repo_cookie.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class OrdersDTO {
    Integer id;
    Integer customerId;
    @NotNull
    String orderDate;
    String shippedDate;
}
