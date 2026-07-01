package com.codemediablog.com.blog_app.services;

import com.codemediablog.com.blog_app.config.AppConstant;
import com.codemediablog.com.blog_app.dtos.UserDto;
import com.codemediablog.com.blog_app.entities.Role;
import com.codemediablog.com.blog_app.entities.User;
import com.codemediablog.com.blog_app.exceptions.ResourceNotFoundException;
import com.codemediablog.com.blog_app.repositories.RoleRepo;
import com.codemediablog.com.blog_app.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUSer(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role=roleRepo.findById(Integer.valueOf(AppConstant.NORMAL_USER)).get();
        user.getRoles().add(role);
        User newUser=userRepo.save(user);
        return modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userdto) {
        User user = dtoToUser(userdto);
        User saveUser=userRepo.save(user);
        return userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userdto, Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setName(userdto.getName());
        user.setAbout(userdto.getAbout());
        User updateUser=userRepo.save(user);
        return userToDto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=new User();
        User getUserById=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return userToDto(getUserById);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users=userRepo.findAll();
        List<UserDto> userDto=users.stream().map(this::userToDto).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUserById(Integer userId) {
        User deletByUser=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepo.delete(deletByUser);

    }

    private  User dtoToUser(UserDto userdto) {
       User user = modelMapper.map(userdto, User.class);
       return  user;
    }

    private  UserDto userToDto(User user) {
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
