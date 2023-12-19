package com.eventmanegement.event.controller;

import com.eventmanegement.event.dto.ApiResponse;
import com.eventmanegement.event.dto.UserDto;
import com.eventmanegement.event.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        UserDto user = this.userService.updateUser(userId, userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> createUser(@PathVariable String userId) {
        this.userService.deleteUser(userId);
        ApiResponse response = ApiResponse.builder()
                .message("user deleted successfully !!")
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
