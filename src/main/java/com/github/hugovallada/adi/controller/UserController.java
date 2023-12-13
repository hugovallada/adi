package com.github.hugovallada.adi.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hugovallada.adi.controller.dto.CreateUserDto;
import com.github.hugovallada.adi.controller.dto.UpdateUserDto;
import com.github.hugovallada.adi.entity.User;
import com.github.hugovallada.adi.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        final var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") UUID userId) {
        final var user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        final var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    ResponseEntity<Void> updateUserById(@PathVariable UUID userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updatedUser(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

}
