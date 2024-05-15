package com.springeshop.service;

import com.springeshop.data.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
}
