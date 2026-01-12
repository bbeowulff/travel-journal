package com.example.traveljournal.Service;

import com.example.traveljournal.Dto.CreateUserRequest;
import com.example.traveljournal.Dto.UpdateUserRequest;
import com.example.traveljournal.Dto.UserResponse;
import com.example.traveljournal.Entity.User;
import com.example.traveljournal.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found: " + id
                ));
        return toResponse(u);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest req) {
        if (userRepository.existsByEmailIgnoreCase(req.email())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Email already exists: " + req.email()
            );
        }

        User u = new User();
        u.setName(req.name());
        u.setSurname(req.surname());
        u.setEmail(req.email().toLowerCase());
        u.setPassword(req.password());

        User saved = userRepository.save(u);
        return toResponse(saved);
    }

    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest req) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found: " + id
                ));

        u.setName(req.name());
        u.setSurname(req.surname());

        return toResponse(u);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found: " + id
            );
        }
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(
                u.getId(),
                u.getName(),
                u.getSurname(),
                u.getEmail()
        );
    }
}
