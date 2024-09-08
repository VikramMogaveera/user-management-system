package com.example.users.controller;

import com.example.users.dto.UserDto;
import com.example.users.exception.UserAlreadyExistsException;
import com.example.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User Created"),
            @ApiResponse(responseCode = "409", description = "Username already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.createUser(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Get User By Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get All User")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Update User By Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @RequestBody UserDto userDto) {
        UserDto user = userService.updateUser(id, userDto);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Update own information")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/user")
    public ResponseEntity<UserDto> updateByUser(@PathVariable Long id,
                                                @RequestBody UserDto userDto) {

        UserDto user = userService.updateByUser(id, userDto);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Delete a User")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with " + id + " has been deleted");
    }
}
