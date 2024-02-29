package com.project.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@Entity
@AllArgsConstructor(staticName = "user_details")
@NoArgsConstructor
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int user_id;
    private String name;
    private String address;
    private String email;
    private int age;

}
