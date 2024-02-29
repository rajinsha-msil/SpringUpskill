package com.project.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
public class UserRedis implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int user_id;
    private String name;
    private String address;
    private String email;
    private int age;

}
