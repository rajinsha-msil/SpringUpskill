package com.project.login.service;

import com.project.login.entity.UserLogin;
import com.project.login.exception.UserNotFoundException;
import com.project.login.repository.UserLoginRepository;
import com.project.login.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginInterface{
    @Autowired
    private UserLoginRepository repository;
    @Autowired
    private EmailSenderService email_service;
    @Override
    public String RegisterUser(LoginRequest request) {
        UserLogin details = UserLogin.user_details(0, request.getName(), request.getPassword(), request.getEmail(), 0,true);
        System.out.println(request.getName());
        repository.save(details);
        return "User Registered Suucessfully";
    }

    @Override
    public String LoginUser(LoginRequest request) throws UserNotFoundException{
        UserLogin details = repository.findByName(request.getName());
        if(details!= null) {
            if (details.getPassword().equals(request.getPassword())) {
                details.setNo_of_attempts(0);
                repository.save(details);
                return "User LoggedIn successfully";
            } else if (!details.isActive()) {
                return "UserAccount Locked";
            } else {
                details.setNo_of_attempts(details.getNo_of_attempts() + 1);
                if (details.getNo_of_attempts() > 2) {
                    details.setActive(false);
                }
                repository.save(details);
                return "Invalid password";
            }
        }else {
            throw new UserNotFoundException("Invalid user name");
        }
    }

    @Override
    public String ForgotPassword(LoginRequest request) {
        sendMail(request.getEmail() , request.getName());
        return "Password reset mail sent to registered mail";
    }

    public void sendMail(String toEmail ,String name) {
        email_service.sendEmail(toEmail , "ForgotPasswordLink", "Reset your password + http://localhost:8080/user/unlock/" + name);
    }
    @Override
    public String UnlockAccount(String name) throws UserNotFoundException{
        UserLogin details = repository.findByName(name);
        if(details!= null) {
            details.setActive(true);
            details.setNo_of_attempts(0);
            repository.save(details);
        }else {
            throw new UserNotFoundException("Invalid user name");
        }
       return "User Account Unlocked successfully";
    }
}
