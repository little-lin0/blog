package xyz.kit00.blog.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
//@NoArgsConstructor
//@AllArgsConstructor

@Builder
public class Article {
    private Integer id;

    private String title;
    private String sub_title;

    private Integer authorId;
    private String author_name;

    private String date;

    private Integer view;

    private Integer good;

    private Integer collect;

    private Integer state;

    private String cover;

    private String label;

    private String content;



}