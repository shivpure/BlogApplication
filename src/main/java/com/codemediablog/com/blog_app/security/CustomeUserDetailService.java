package com.codemediablog.com.blog_app.security;

import com.codemediablog.com.blog_app.entities.User;
import com.codemediablog.com.blog_app.exceptions.ResourceNotFoundException;
import com.codemediablog.com.blog_app.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","User name:"+username,0));

        return user;
    }
}
