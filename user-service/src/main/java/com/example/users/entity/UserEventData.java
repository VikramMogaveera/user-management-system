package com.example.users.entity;

import com.example.common.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEventData {

    private String action;
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Set<Role> roles;
    private LocalDateTime createdTime;
}
