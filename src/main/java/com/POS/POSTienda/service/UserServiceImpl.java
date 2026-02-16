package com.POS.POSTienda.service;

import com.POS.POSTienda.dto.UserResponseDTO;
import com.POS.POSTienda.model.User;
import com.POS.POSTienda.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        return mapToDTO(user);
    }


    private UserResponseDTO mapToDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setRole(user.getRol());
        return dto;
    }

}
