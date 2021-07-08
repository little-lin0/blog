package xyz.kit00.blog.dao;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.kit00.blog.bean.Article;
import xyz.kit00.blog.bean.Search_key;
import xyz.kit00.blog.bean.User;
import xyz.kit00.blog.bean.UserInfo;

@Repository
public interface UserMapper {
    int insert(Map<String, String> record);
    User selectByPrimaryKey(Integer id);
    List<User> selectByCondition(Map<String,String> map);
    List<User> selectAll();
    List<UserInfo> selectUserInfo(Map<String,String> map);
}