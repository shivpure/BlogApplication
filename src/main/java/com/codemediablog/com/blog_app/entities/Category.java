package com.codemediablog.com.blog_app.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Categories")
@Data
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name="title",nullable=false,length=100)
    private String categoryTitle;
    @Column(name="description")
    private String categoryDescription;
     @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> post=new ArrayList<>();
}