package com.example.users.service;

import com.example.users.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDto);

    UserDto updateByUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
