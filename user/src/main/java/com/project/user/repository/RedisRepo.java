package com.project.user.repository;

import com.project.user.entity.User;
import com.project.user.entity.UserRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisRepo {

    public static final String HASH_KEY = "User";
    @Autowired
    private RedisTemplate template;

    public UserRedis save(UserRedis user){
        template.opsForHash().put("User",user.getUser_id(),user);
        return user;
    }

    public List<UserRedis> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public UserRedis findUserById(int id) {
        return (UserRedis)template.opsForHash().get(HASH_KEY,id);
    }

    public String delete(int id) {
        template.opsForHash().delete(HASH_KEY,id);
        return "Product Deleted";
    }
}
