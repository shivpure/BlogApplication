package com.codemediablog.com.blog_app.controllers;


import com.codemediablog.com.blog_app.dtos.JwtAuthRequest;
import com.codemediablog.com.blog_app.dtos.UserDto;
import com.codemediablog.com.blog_app.exceptions.ApiException;
import com.codemediablog.com.blog_app.security.JwtAuthResponse;
import com.codemediablog.com.blog_app.security.JwtTokenHelper;
import com.codemediablog.com.blog_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper tokenHelper;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) {

        this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
        UserDetails userDetails=userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String token=tokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
         jwtAuthResponse.setToken(token);
         return new ResponseEntity(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(token);
        }catch(BadCredentialsException e) {
            System.out.println("Invalid details");
            throw new ApiException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUSer(@RequestBody UserDto userDto) {
           UserDto registerUser=userService.registerNewUSer(userDto);
        return new ResponseEntity<>(registerUser,HttpStatus.CREATED);
    }
}
