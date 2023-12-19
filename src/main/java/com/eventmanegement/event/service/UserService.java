package com.eventmanegement.event.service;

import com.eventmanegement.event.dto.UserDto;

import java.util.List;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(String userId,UserDto userDto);

    //get all
    List<UserDto> getAllUsers();

    //delete
    void deleteUser(String userId);
}
