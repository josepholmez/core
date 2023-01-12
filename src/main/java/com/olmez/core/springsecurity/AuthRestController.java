package com.olmez.core.springsecurity;

import java.rmi.UnexpectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.core.springsecurity.securityutiliy.AuthResponse;
import com.olmez.core.springsecurity.securityutiliy.AuthRequest;
import com.olmez.core.springsecurity.securityutiliy.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@RequestBody RegisterRequest request) {
        AuthResponse res = authService.register(request);
        String error = res.getErrorMessage();
        return error == null ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(error);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody AuthRequest request) throws UnexpectedException {
        AuthResponse res = authService.authenticate(request);
        String error = res.getErrorMessage();
        return error == null ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(error);
    }

}