package com.project.login.repository;

import com.project.login.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,Integer> {
    UserLogin findByName(String name);
    @Query(value = "Select * from login_data where name = ?1",  nativeQuery = true)
    public UserLogin getUserByName(@Param("name") String name);

}
