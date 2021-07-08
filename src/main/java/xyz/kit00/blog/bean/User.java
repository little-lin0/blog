package xyz.kit00.blog.bean;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
public class User {
    private Integer id;

    private String username;

    private String email;

    private String password;

    private Integer status;

    private String head_img;
}