package com.project.user.service;

import com.project.user.entity.User;
import com.project.user.exception.UserNotFoundException;
import com.project.user.requests.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServiceInterface {
    Integer AddUser(UserRequest request);
    Integer UpdateUserById(UserRequest request) throws UserNotFoundException;
    Integer UpdateUserByName(UserRequest request) throws UserNotFoundException;
    String DeleteUser(Integer id);
    List<User> ListUser(Integer page);

}
