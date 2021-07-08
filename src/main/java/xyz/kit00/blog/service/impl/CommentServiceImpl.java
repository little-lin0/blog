package xyz.kit00.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kit00.blog.bean.Article;
import xyz.kit00.blog.bean.Comment;
import xyz.kit00.blog.dao.CommentMapper;
import xyz.kit00.blog.service.CommentService;

import java.util.Map;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public JSONObject getCommentList(Integer pageNum, Integer pageSize) {
        JSONObject result=new JSONObject();
        try {
            PageHelper.startPage(pageNum,pageSize);
            PageInfo<Comment> info=new PageInfo<>(commentMapper.selectAll());
            result.put("data",info.getList());
            result.put("count",info.getTotal());
            result.put("msg","查询成功");
            result.put("code","0");
        }catch (NumberFormatException e) {
            result.put("msg","查询异常");
            result.put("code","500");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject addComment(Map<String,String> record) {
        JSONObject result=new JSONObject();
        try {
            commentMapper.insert(record);
            result.put("msg","插入成功");
            result.put("code","0");
        } catch (Exception e) {
            result.put("msg","插入失败");
            result.put("code","500");
            e.printStackTrace();
        }

        return result;
    }
}
