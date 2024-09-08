package com.example.users.service;

import com.example.common.entities.Role;
import com.example.common.entities.User;
import com.example.users.dto.UserDto;
import com.example.users.exception.UserNotFoundException;
import com.example.users.repository.RoleRepository;
import com.example.users.repository.UserRepository;
import com.example.users.service.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserEventPublisher userEventPublisher;

    @InjectMocks
    private UserImpl userService;

    private UserDto userDto;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        role = new Role();
        role.setId(1L);
        role.setRoleName("USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setFirstName("jhon");
        userDto.setLastName("bob");
        userDto.setUsername("testuser");
        userDto.setEmail("jb@gmail.com");
        userDto.setPassword("testpassword");
        userDto.setRoles(roles);

        user = new User();
        user.setId(1L);
        user.setFirstName("john");
        user.setLastName("bob");
        user.setUsername("testuser");
        user.setEmail("jb@gmail.com");
        user.setPassword("password");
        user.setRoles(roles);
    }

    @Test
    void testCreateUserSuccessful() {
        when(userRepository.findByUsername(userDto.getUsername())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName("USER")).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto savedUserDto = userService.createUser(userDto);

        assertNotNull(savedUserDto);
        assertEquals("testuser", savedUserDto.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
        verify(userEventPublisher, times(1)).publishUserCreatedEvent(any(User.class));
    }

    @Test
    void testGetUserById_Success() {
        Long userId = 1L;

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setId(userId);
        user.setFirstName("john");
        user.setLastName("bob");
        user.setUsername("testuser");
        user.setEmail("jb@gmail.com");
        user.setPassword("password");
        user.setRoles(roles);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDto userDto1 = userService.getUserById(userId);

        assertNotNull(userDto1);
        assertEquals(userId, userDto1.getId());
        assertEquals("john", userDto1.getFirstName());
        assertEquals("bob", userDto1.getLastName());
        assertEquals("testuser", userDto1.getUsername());
        assertEquals("jb@gmail.com", userDto1.getEmail());
        assertEquals("password", userDto1.getPassword());
        assertEquals(roles, userDto1.getRoles());

        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserById_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(userId);
        });

        verify(userRepository).findById(userId);
    }
}
