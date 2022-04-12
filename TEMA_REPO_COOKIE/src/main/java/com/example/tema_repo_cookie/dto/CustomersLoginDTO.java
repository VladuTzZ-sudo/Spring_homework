package com.example.tema_repo_cookie.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomersLoginDTO {
    @NotNull
    String username;
    @NotNull
    String password;
}
