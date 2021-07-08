package xyz.kit00.blog.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private Integer id;
    private String username;
    private String head_img;
    private Integer follow;
    private Integer article;
}
