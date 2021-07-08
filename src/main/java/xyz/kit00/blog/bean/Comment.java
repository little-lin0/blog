package xyz.kit00.blog.bean;

import lombok.Data;

@Data
public class Comment {
    private Integer id;

    private String username;

    private String email;

    private String subject;

    private String message;


    }
