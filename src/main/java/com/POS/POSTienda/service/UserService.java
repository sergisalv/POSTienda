package com.POS.POSTienda.service;

import com.POS.POSTienda.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO getUserById(Long id);
}
