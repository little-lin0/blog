package xyz.kit00.blog.common;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public class Message {
    public static <T> JSONObject messageList(JSONObject result, PageInfo<T> info){
        result.put("data",info.getList());
        result.put("count",info.getTotal());
        return result;
    }
    public static JSONObject success(JSONObject result){
        result.put("msg","查询成功");
        result.put("code","0");
        return result;
    }
    public static JSONObject fail(JSONObject result){
        result.put("msg","插入失败");
        result.put("code","500");
        return result;
    }

}
