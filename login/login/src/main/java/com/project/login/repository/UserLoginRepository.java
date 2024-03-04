package com.project.login.repository;

import com.project.login.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,Integer> {
    UserLogin findByName(String name);
}
