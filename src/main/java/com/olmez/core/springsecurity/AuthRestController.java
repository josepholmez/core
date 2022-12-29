package com.olmez.core.springsecurity;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.core.model.User;
import com.olmez.core.repositories.UserRepository;
import com.olmez.core.springsecurity.config.SecurityConfig;
import com.olmez.core.springsecurity.config.UserDetailsImpl;
import com.olmez.core.springsecurity.securityutiliy.JwtUtility;
import com.olmez.core.springsecurity.securityutiliy.PasswordUtility;
import com.olmez.core.springsecurity.securityutiliy.SigninRequest;
import com.olmez.core.springsecurity.securityutiliy.SignupRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;

    // AUTH - Sign Up
    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@RequestBody SignupRequest signupRequest) {

        User user = userRepository.findByUsername(signupRequest.getUsername());
        if (user != null) {
            String errorMsg = String.format("Error: %s is already in use!", signupRequest.getUsername());
            return ResponseEntity.badRequest().body(errorMsg);
        }
        user = userRepository.findUserByEmail(signupRequest.getEmail());
        if (user != null) {
            String errorMsg = String.format("Error: %s is already in use!", signupRequest.getEmail());
            return ResponseEntity.badRequest().body(errorMsg);
        }
        // Create a new user's account
        user = new User(signupRequest.getUsername(), signupRequest.getFirstName(), signupRequest.getLastName(),
                signupRequest.getEmail());
        user.setPasswordHash(PasswordUtility.hashPassword(signupRequest.getPassword()));
        userRepository.save(user);
        return generateResponse(null, user.getUsername(), user.getUserType().getRole());
    }

    // AUTH - Sign In
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody SigninRequest signinRequest) throws UnexpectedException {

        UserDetailsImpl userDetailsImpl = grantAuthentication(signinRequest);
        User curUser = userDetailsImpl.getUser();
        if (curUser == null) {
            return ResponseEntity.status(400).body(SecurityConfig.EXCEPTION_MESSAGE + signinRequest.getUsername());
        }
        String jwt = createJWTForUser(userDetailsImpl);
        log.info("Granted jwt:{} to user:{}", jwt, curUser);
        return generateResponse(jwt, curUser.getUsername(), curUser.getUserType().getRole());
    }

    private UserDetailsImpl grantAuthentication(SigninRequest signinRequest) throws UnexpectedException {
        String username = signinRequest.getUsername();
        String password = signinRequest.getPassword();
        var aToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authResult = authManager.authenticate(aToken);
        return getPrincipalFromAuthentication(authResult);
    }

    private UserDetailsImpl getPrincipalFromAuthentication(Authentication authResult) throws UnexpectedException {
        Object principal = authResult.getPrincipal();
        if (!(principal instanceof UserDetailsImpl)) {
            throw new UnexpectedException(
                    String.format("Unexpected authentication principal:%s", principal.getClass().getName()));
        }
        return (UserDetailsImpl) principal;
    }

    private String createJWTForUser(UserDetails userDetails) {
        return JwtUtility.generateToken(userDetails);
    }

    // Custom response for ui (Angular)
    private ResponseEntity<Object> generateResponse(String resToken, String resUsername,
            Optional<String> resRole) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", resToken);
        map.put("username", resUsername);
        map.put("role", resRole.isPresent() ? resRole.get() : null);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}