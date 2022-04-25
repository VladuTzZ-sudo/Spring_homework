package com.db.marketapi.repository;

import com.db.marketapi.model.UserLoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDTORepository extends JpaRepository<UserLoginDTO, String> {
    UserLoginDTO findUserLoginDtoByUsername(String username);
    UserLoginDTO findUserLoginDTOByUserID(String userID);
    UserLoginDTO findUserLoginDTOByEmail(String email);
}
