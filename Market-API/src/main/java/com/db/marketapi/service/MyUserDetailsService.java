package com.db.marketapi.service;

import com.db.marketapi.repository.UserLoginDTORepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    final UserLoginDTORepository userLoginDTORepository;

    public MyUserDetailsService(UserLoginDTORepository userLoginDTORepository) {
        this.userLoginDTORepository = userLoginDTORepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userLoginDTORepository.findUserLoginDtoByUsername(username);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userLoginDTORepository.findUserLoginDTOByEmail(email);
    }
}
