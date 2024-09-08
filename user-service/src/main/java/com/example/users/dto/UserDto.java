package com.example.users.dto;

import com.example.common.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private Set<Role> roles;
}
