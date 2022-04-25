package com.db.marketapi.service;

import com.db.marketapi.model.UserLoginDTO;
import com.db.marketapi.repository.LoginDTORepository;
import com.db.marketapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    final
    LoginDTORepository loginDTORepository;

    public MyUserDetailsService(LoginDTORepository userRepository) {
        this.loginDTORepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginDTORepository.findUserLoginDtoByUsername(username);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return loginDTORepository.findUserLoginDTOByEmail(email);
    }
}
