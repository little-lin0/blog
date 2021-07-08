package xyz.kit00.blog.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import xyz.kit00.blog.bean.Article;
import xyz.kit00.blog.bean.User;

import java.util.Map;
@Service
public interface UserService {
    JSONObject searchUserForList(Map<String,String> map);
    User searchUserForOne(Map<String,String> map);
    JSONObject searchAll(Integer pageNum,Integer pageSize);

    JSONObject insertUser(Map<String, String> user);

    User selectByPrimaryKey(Integer id);

    JSONObject searchByCondition(Map<String, String> map);

    JSONObject searchUserInfo(Map<String, String> map);
}
