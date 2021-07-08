package xyz.kit00.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.kit00.blog.bean.Comment;
import xyz.kit00.blog.content.Sample;
import xyz.kit00.blog.service.CommentService;

import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/commentList")
    public JSONObject getCommentList(@RequestParam("pageNum") Integer pageNum
                                   , @RequestParam("pageSize") Integer pageSize){
      return   commentService.getCommentList(pageNum,pageSize);
    }
    @RequestMapping(value = "/add",method = RequestMethod.PUT)
    public JSONObject addComment(@RequestParam Map<String,String> record){
//        审核
       if(Sample.checkText(record.values().toString()))
        return commentService.addComment(record);
       else
       {
           JSONObject result=new JSONObject();
           result.put("msg","提交内容违规");
           result.put("code","500");
           return result;
       }
    }
}
