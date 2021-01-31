package com.auth.service;


import com.auth.AuthServiceApplication;
import com.auth.dto.JwtResponse;
import com.auth.dto.LoginRequest;
import com.auth.dto.SignupRequestDto;
import com.auth.exceptions.AuthServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
@Transactional
public class UserAuthenticationTest {

    @Autowired
    private UserService userService;
    private SignupRequestDto signupRequestDto;
    @Before
    public void init() throws AuthServiceException {
        signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("newUser");
        signupRequestDto.setEmail("newUser@gmail.com");
        signupRequestDto.setPassword("asdf123");
    }

    @Test
    public void registerUserTest() throws AuthServiceException {
       JwtResponse response = userService.registerUser(signupRequestDto);
        Assert.assertNotEquals(response,null);
    }


    @Test(expected = AuthServiceException.class)
    public void registerUserWithExceptionAlreadyExists() throws AuthServiceException {
        userService.registerUser(signupRequestDto);
        userService.registerUser(signupRequestDto);
    }

    @Test
    public void loginTest() throws AuthServiceException {
        userService.registerUser(signupRequestDto);
        JwtResponse response =  userService.authenticateUser(new LoginRequest("newUser","asdf123"));
        Assert.assertNotEquals(response,null);
    }

}
