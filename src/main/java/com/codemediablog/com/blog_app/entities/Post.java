package com.codemediablog.com.blog_app.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name="post_title",nullable = false, length = 150)
    private String postTitle;
    @Column(length = 1000)
    private String content;
    @Column
    private Date addedDate;
    @Column
    private String image;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToOne
    private User user;
}
