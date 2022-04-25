package com.db.marketapi.repository;

import com.db.marketapi.model.UserLoginDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserLoginDTORepository extends MongoRepository<UserLoginDTO, String> {
    UserLoginDTO findUserLoginDtoByUsername(String username);
    UserLoginDTO findUserLoginDTOByUserID(String userID);
    UserLoginDTO findUserLoginDTOByEmail(String email);
}
