package xyz.kit00.blog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import xyz.kit00.blog.bean.Comment;
@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Map<String, String> record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}