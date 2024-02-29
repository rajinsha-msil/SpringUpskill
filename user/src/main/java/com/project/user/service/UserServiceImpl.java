package com.project.user.service;

import com.project.user.entity.User;
import com.project.user.exception.UserNotFoundException;
import com.project.user.repository.UserInfoRepository;
import com.project.user.requests.UserRequest;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserServiceInterface{
    @Autowired
    private UserInfoRepository repository;
    @Override
    public Integer AddUser(UserRequest request) {
        User user = User.user_details(0,request.getName(),request.getAddress(),request.getEmail(),request.getAge());
        return repository.save(user).getUser_id();
    }

    @Override
    public Integer UpdateUserById(UserRequest request) throws UserNotFoundException{
        User user_details = repository.findById(request.getId()).orElse(null);
        if (user_details!= null) {
            user_details.setName(request.getName());
            user_details.setAddress(request.getAddress());
            user_details.setEmail(request.getEmail());
            user_details.setAge(request.getAge());

            return repository.save(user_details).getUser_id();
        }else{
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public Integer UpdateUserByName(UserRequest request) throws UserNotFoundException{
        User user_details = repository.findByName(request.getName());
        if (user_details != null) {
            user_details.setName(request.getName());
            user_details.setAddress(request.getAddress());
            user_details.setEmail(request.getEmail());
            user_details.setAge(request.getAge());
            return repository.save(user_details).getUser_id();
        }else{
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public String DeleteUser(Integer id) {
        repository.deleteById(id);
        return "Deleted Book" + id;
    }

    @Override
    public List<User> ListUser(Integer pageNum) {
        Pageable page = PageRequest.of(pageNum,3 , Sort.by("name"));
        return repository.findAll(page).getContent();
    }
}
