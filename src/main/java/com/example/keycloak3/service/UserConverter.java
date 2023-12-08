package com.example.keycloak3.service;

import com.example.keycloak3.dto.UserDTO;
import com.example.keycloak3.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {
    public UserDTO toDTO(User model) {
        UserDTO dto = new UserDTO();
        if(model.getId() != null) {
            dto.setId(model.getId());
        }
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setPassword("***");
        dto.setRole(model.getRole());
        return dto;
    }
}
