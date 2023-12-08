package com.example.keycloak3.service;

import com.example.keycloak3.dto.UserDTO;
import com.example.keycloak3.enums.Role;
import com.example.keycloak3.model.User;
import com.example.keycloak3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO register(UserDTO userDTO) {
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(Role.user)
                .build();
        user = userRepository.save(user);
        userDTO = userConverter.toDTO(user);
        return userDTO;
    }
}
