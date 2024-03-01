package com.project.user.controller;

import com.project.user.entity.User;
import com.project.user.entity.UserRedis;
import com.project.user.exception.UserNotFoundException;
import com.project.user.repository.RedisRepo;
import com.project.user.requests.UserRequest;
import com.project.user.service.UserServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CommonsLog
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private RedisRepo redis;
    @PostMapping("/addUser")
    public Integer AddUser(@RequestBody UserRequest request) {
        log.info("Request Received for Add user:" + request);
        return service.AddUser(request);
    }

    @DeleteMapping("/DeleteUser/{id}")
    public String DeleteUser(@PathVariable Integer id) {
        log.info("Request Received for deletion:" + id);
        return service.DeleteUser(id);
    }

    @PostMapping("/updateUserByName")
    public Integer UpdateUserbyName(@RequestBody UserRequest request) throws UserNotFoundException
    {
        log.info("Request Received for updation:" + request);
        return service.UpdateUserByName(request);
    }

    @PostMapping("/updateUserById")
    public Integer UpdateUserbyId(@RequestBody UserRequest request) throws UserNotFoundException
    {
        log.info("Request Received for updation:" + request);
        return service.UpdateUserById(request);
    }

    @GetMapping("/listUser/{pageNum}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> ListUsers(@PathVariable Integer pageNum)
    {
        log.info("Request Received for Listing" );
        return service.ListUser(pageNum);
    }
    @PostMapping("/saveRedis")
    public UserRedis save(@RequestBody UserRedis user) {
        return redis.save(user);
    }

    @GetMapping("/getRedis")
    public List<UserRedis> listUser(){
        return redis.findAll();
    }

    @GetMapping("/getByIdRedis/{id}")
    public UserRedis getByID(@PathVariable int id) {
        return redis.findUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return redis.delete(id);
    }
}
