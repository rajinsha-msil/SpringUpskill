package com.project.login.service;

import com.project.login.exception.UserNotFoundException;
import com.project.login.requests.LoginRequest;

public interface UserLoginInterface {
    String RegisterUser(LoginRequest request);
    String LoginUser(LoginRequest request) throws UserNotFoundException;
    String ForgotPassword(LoginRequest request);
    String  UnlockAccount(String name) throws UserNotFoundException;
}
