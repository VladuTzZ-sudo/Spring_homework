package com.db.marketapi.repository;

import com.db.marketapi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByOrderByNumOfOrdersAsc();
    Optional<Users> findUserByUsernameAndPassword(String username, String password);
    Users getUserByUsername(String username);
}
