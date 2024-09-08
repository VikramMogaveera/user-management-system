package com.example.users.mapper;

import com.example.common.entities.User;
import com.example.users.dto.UserDto;

public class UserMapper {

    //converting user to UserDto
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getRoles()
        );
    }

    //converting userDto to user
    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getCreatedAt(),
                userDto.getRoles()
        );
    }
}
