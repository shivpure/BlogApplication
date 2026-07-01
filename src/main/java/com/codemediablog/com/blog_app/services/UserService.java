package com.codemediablog.com.blog_app.services;

import com.codemediablog.com.blog_app.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUSer(UserDto userDto);
    UserDto createUser(UserDto userdto);
    UserDto updateUser(UserDto userdto,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUserById(Integer userId);
}
