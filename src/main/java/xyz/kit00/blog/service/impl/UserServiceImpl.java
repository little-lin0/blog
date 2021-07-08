package xyz.kit00.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kit00.blog.bean.User;
import xyz.kit00.blog.bean.UserInfo;
import xyz.kit00.blog.dao.ArticleMapper;
import xyz.kit00.blog.dao.FollowMapper;
import xyz.kit00.blog.dao.Search_keyMapper;
import xyz.kit00.blog.dao.UserMapper;
import xyz.kit00.blog.service.UserService;

import java.util.Map;
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private Search_keyMapper search_keyMapper;
    @Override
    public JSONObject searchUserForList(Map<String, String> map) {
        JSONObject result=new JSONObject();
        try {
            PageHelper.startPage(Integer.parseInt(map.get("pageNum")),Integer.parseInt(map.get("pageSize")));
            PageInfo<User> info=new PageInfo<>(userMapper.selectByCondition(map));
            result.put("data",info.getList());
            result.put("count",info.getTotal());
            result.put("msg","查询成功");
            result.put("code","0");
        } catch (NumberFormatException e) {
            result.put("msg","查询异常");
            result.put("code","500");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User searchUserForOne(Map<String, String> map) {
          User user=userMapper.selectByCondition(map).get(0);
          return user;
    }

    @Override
    public JSONObject searchAll(Integer pageNum, Integer pageSize) {
        JSONObject result=new JSONObject();
        try {
            PageHelper.startPage(pageNum,pageSize);
            PageInfo<User> info=new PageInfo<>(userMapper.selectAll());
            result.put("data",info.getList());
            result.put("count",info.getTotal());
            result.put("msg","查询成功");
            result.put("code","0");
        } catch (NumberFormatException e) {
            result.put("msg","查询异常");
            result.put("code","500");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject searchUserInfo(Map<String, String> map) {
        JSONObject result=new JSONObject();
        try {
            PageHelper.startPage(Integer.parseInt(map.get("pageNum")),Integer.parseInt(map.get("pageSize")));
            PageInfo<UserInfo> info=new PageInfo<>(userMapper.selectUserInfo(map));
            result.put("data",info.getList());
            result.put("count",info.getTotal());
            if (map.get("select") != null&&info.getTotal()>0) {

                if (search_keyMapper.selectByName(map.get("key")) == null)
                    search_keyMapper.insert(map.get("key"));
                else
                    search_keyMapper.updateByName(map.get("key"));
            }


            result.put("msg","查询成功");
            result.put("code","0");
        } catch (NumberFormatException e) {
            result.put("msg","查询异常");
            result.put("code","500");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject insertUser(Map<String, String> user) {
        JSONObject result=new JSONObject();
        try {
            userMapper.insert(user);
            result.put("msg","插入成功");
            result.put("code","0");
        } catch (Exception e) {
            result.put("msg","插入失败");
            result.put("code","500");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public JSONObject searchByCondition(Map<String, String> map) {
        JSONObject result=new JSONObject();
        return result;
    }
}
