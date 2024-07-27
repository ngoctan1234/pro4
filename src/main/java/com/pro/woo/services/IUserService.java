package com.pro.woo.services;

import com.pro.woo.dtos.UserDTO;
import com.pro.woo.exceptions.DataNotFoundException;
import com.pro.woo.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;
    String login(String phoneNumber, String password) ;
}
