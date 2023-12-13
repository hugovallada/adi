package com.github.hugovallada.adi.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.hugovallada.adi.controller.dto.CreateUserDto;
import com.github.hugovallada.adi.controller.dto.UpdateUserDto;
import com.github.hugovallada.adi.entity.User;
import com.github.hugovallada.adi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UUID createUser(CreateUserDto createUserDto) {
        final var user = new User(createUserDto.username(), createUserDto.email(), createUserDto.password());
        final var updatedUser = userRepository.save(user);
        return updatedUser.getUserId();
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updatedUser(UUID userId, UpdateUserDto updateUserDto) {
        final var user = userRepository.findById(userId);
        if (user.isPresent()) {
            final var userToUpdate = user.get();
            if (Objects.nonNull(updateUserDto.username())) {
                userToUpdate.setUsername(updateUserDto.username());
            }
            if (Objects.nonNull(updateUserDto.password())) {
                userToUpdate.setPassword(updateUserDto.password());
            }
            userRepository.save(userToUpdate);
        }
    }

}
