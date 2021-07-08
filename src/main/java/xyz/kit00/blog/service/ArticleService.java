package xyz.kit00.blog.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import xyz.kit00.blog.bean.Article;

import java.util.Map;

@Service
public interface ArticleService {
 JSONObject addArticle(Article article);
 JSONObject searchAll(Integer pageNum,Integer pageSize);

    JSONObject selectByPrimaryKey(Integer id);

    Integer getTotal();

    JSONObject searchByCondition(Map<String, String> condition);

    JSONObject update(Map<String, String> map);

    JSONObject deleteArticle(Integer id);

    JSONObject collectService(Map<String, String> map);

    JSONObject followService(Map<String, String> map);

    JSONObject labelService(String update);

    JSONObject search_keyService();
}
