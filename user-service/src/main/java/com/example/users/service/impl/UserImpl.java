package com.example.users.service.impl;

import com.example.common.entities.Role;
import com.example.common.entities.User;
import com.example.users.dto.UserDto;
import com.example.users.exception.UserAlreadyExistsException;
import com.example.users.exception.UserNotFoundException;
import com.example.users.mapper.UserMapper;
import com.example.users.repository.RoleRepository;
import com.example.users.repository.UserRepository;
import com.example.users.service.UserEventPublisher;
import com.example.users.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserEventPublisher userEventPublisher;
    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        Set<Role> roles = userDto.getRoles().stream()
                .map(role -> roleRepository.findByRoleName(role.getRoleName())
                        .orElseGet(() -> roleRepository.save(role)))
                .collect(Collectors.toSet());

        userDto.setRoles(roles);
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        userEventPublisher.publishUserCreatedEvent(savedUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id not found"));
        return UserMapper.mapToUserDto(user);
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::mapToUserDto).toList();
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User id not found"));

        Set<Role> roles = userDto.getRoles().stream()
                .map(role -> roleRepository.findByRoleName(role.getRoleName())
                        .orElseGet(() -> roleRepository.save(role)))
                .collect(Collectors.toSet());

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        userEventPublisher.publishUserUpdatedEventByAdmin(savedUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Transactional
    @Override
    public UserDto updateByUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User id not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User savedUser = userRepository.save(user);
        userEventPublisher.publishUserUpdatedEventByUser(savedUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id not found"));
        userRepository.deleteById(id);
        userEventPublisher.publishUserDeletedEvent(user);
    }
}
