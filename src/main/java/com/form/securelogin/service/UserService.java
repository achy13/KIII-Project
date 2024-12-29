package com.form.securelogin.service;

import com.form.securelogin.model.User;
import com.form.securelogin.model.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
    void deleteUserByEmail(String email);
    void updateUserRole(Long userId, String role);
}
