package com.codemediablog.com.blog_app.dtos;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private final String  username;
    private final String password;
}
