package com.db.marketapi.controller;

import com.db.marketapi.model.UserLoginDTO;
import com.db.marketapi.model.payloads.AuthenticationRequest;
import com.db.marketapi.model.payloads.AuthenticationResponse;
import com.db.marketapi.repository.UserLoginDTORepository;
import com.db.marketapi.service.MyUserDetailsService;
import com.db.marketapi.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LoginController {
    //@Autowired
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

    //private final UserService userService;

    private final JwtUtil jwtUtil;

    private final UserLoginDTORepository userLoginDTORepository;

    private final MyUserDetailsService userDetailsService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public LoginController(JwtUtil jwtUtil, UserLoginDTORepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, MyUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userLoginDTORepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()) //TODO ENCRYPT THE PASSWORD HERE
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestBody AuthenticationRequest authenticationRequest) {
        if (userLoginDTORepository.findUserLoginDTOByEmail(authenticationRequest.getEmail()) != null)
            return "The user already exists.";

        UserLoginDTO user = new UserLoginDTO();
        user.setUserID(UUID.randomUUID().toString());
        user.setEmail(authenticationRequest.getEmail());
        user.setUsername(authenticationRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userLoginDTORepository.save(user);
        return "The user was created.";
    }

    @GetMapping(value = "/bps/data/user/{JWT}")
    public UserLoginDTO getUserFromJWTPV(@PathVariable String JWT) {
        return getUserFromJWT(JWT);
    }

    @GetMapping(value = "/data/user")
    public UserLoginDTO getUserFromJWTQP(@RequestParam String JWT) {
        return getUserFromJWT(JWT);
    }

    public UserLoginDTO getUserFromJWT(String JWT) {
        String email = jwtUtil.extractEmail(JWT);

        //noinspection DuplicatedCode
        if (email != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByEmail(email);
            if (jwtUtil.validateToken(JWT, userDetails)) {
                return userLoginDTORepository.findUserLoginDTOByEmail(email);
            }
        }
        return null;
    }
}
