package com.codemediablog.com.blog_app.controllers;

import com.codemediablog.com.blog_app.dtos.ApiResponse;
import com.codemediablog.com.blog_app.dtos.UserDto;
import com.codemediablog.com.blog_app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto){
        UserDto createdUser = userService.createUser(userdto);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid){
        UserDto userDto=userService.getUserById(uid);
        return new ResponseEntity<>(userDto,HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> getAllUser=userService.getAllUser();
        return new ResponseEntity<>(getAllUser,HttpStatus.OK);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto, @PathVariable("userId") Integer uid){
        UserDto updateUser=userService.updateUser(userdto,uid);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") Integer uid){
        userService.deleteUserById(uid);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true, LocalDateTime.now()),HttpStatus.OK);
  }


}
