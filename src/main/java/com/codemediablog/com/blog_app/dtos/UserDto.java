package com.codemediablog.com.blog_app.dtos;


import com.codemediablog.com.blog_app.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {


    private int id;
    @NotEmpty
    @Size(min = 4, message="User must be minimum 4 char !!")
    private String name;
    @Email (message="Email address not valid !!")
    private String email;
    @NotEmpty
    @Size(min = 4, max=20, message="Password must be minimum 4 and max of 20 char !! ")
    private String password;
    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
