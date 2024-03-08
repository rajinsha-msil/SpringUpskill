package com.project.login.service;

import com.project.login.entity.UserLogin;
import com.project.login.exception.UserNotFoundException;
import com.project.login.requests.LoginRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface UserLoginInterface {
    String RegisterUser(LoginRequest request);
    String LoginUser(LoginRequest request) throws UserNotFoundException;
    String ForgotPassword(LoginRequest request);
    String  UnlockAccount(String name) throws UserNotFoundException;
    UserLogin GetUserDetails(String name) throws UserNotFoundException;

    ByteArrayInputStream DownloadUserData() throws IOException;
}
