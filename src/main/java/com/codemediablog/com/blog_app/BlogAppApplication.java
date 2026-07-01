package com.codemediablog.com.blog_app;

import com.codemediablog.com.blog_app.config.AppConstant;
import com.codemediablog.com.blog_app.entities.Role;
import com.codemediablog.com.blog_app.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {


    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private RoleRepo roleRepo;
    public static void main(String[] args) {

        SpringApplication.run(BlogAppApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("ganesh123"));
        Role role=new Role();
        role.setId(Integer.parseInt(AppConstant.ADMIN_USER));
        role.setName("ROLE_ADMIN");

        Role role1=new Role();
        role1.setId(Integer.parseInt(AppConstant.NORMAL_USER));
        role1.setName("NORMAL_USER");
        role1.setName("RULE_NORMAL");

        List<Role> allRole=List.of(role,role1);
        roleRepo.saveAll(allRole).forEach(System.out::println);
    }

}