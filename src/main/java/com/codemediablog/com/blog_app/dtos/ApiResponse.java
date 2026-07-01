package com.codemediablog.com.blog_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String  message;
    private Boolean success;
    private LocalDateTime timestamp;

}
