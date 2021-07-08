package xyz.kit00.blog.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import xyz.kit00.blog.bean.Comment;

import java.util.Map;

@Service
public interface CommentService {
     JSONObject getCommentList(Integer pageNum, Integer pageSize);
     JSONObject addComment(Map<String, String> record);
}
