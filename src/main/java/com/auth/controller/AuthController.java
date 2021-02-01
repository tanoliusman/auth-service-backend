package com.auth.controller;

import javax.validation.Valid;

import com.auth.dto.JwtResponse;
import com.auth.dto.LoginRequest;
import com.auth.dto.SignupRequestDto;
import com.auth.exceptions.AuthServiceException;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws AuthServiceException {

        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> registerUser(@Valid @RequestBody SignupRequestDto signUpRequestDto) throws AuthServiceException {
        return ResponseEntity.ok(userService.registerUser(signUpRequestDto));
    }
}