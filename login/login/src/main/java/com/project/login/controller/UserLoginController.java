package com.project.login.controller;

import com.project.login.exception.UserNotFoundException;
import com.project.login.requests.LoginRequest;
import com.project.login.service.UserLoginServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CommonsLog
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserLoginServiceImpl service;

    @PostMapping("/register")
    public String RegisterUser(@RequestBody LoginRequest request) {
        log.info("Registration request received for User:" + request.getName());
        return service.RegisterUser(request);
    }

    @PostMapping("/login")
    public String LoginUser(@RequestBody LoginRequest request) throws UserNotFoundException {
        log.info("Login request received for User:" + request.getName());
        return service.LoginUser(request);
    }

    @PostMapping("/forgotPassword")
    public String ForgotPassword(@RequestBody LoginRequest request) {
        log.info("ForgotPassword request received for User:" + request.getName());
        return service.ForgotPassword(request);
    }

    @GetMapping("/unlock/{name}")
    public String Unlock(@PathVariable String name) throws UserNotFoundException {
        log.info("Unlock request received for User:" + name);
        return service.UnlockAccount(name);
    }

}
