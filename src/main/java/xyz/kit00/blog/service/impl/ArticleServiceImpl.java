package xyz.kit00.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import xyz.kit00.blog.bean.Article;
import xyz.kit00.blog.bean.Label;
import xyz.kit00.blog.bean.Search_key;
import xyz.kit00.blog.dao.*;
import xyz.kit00.blog.service.ArticleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableTransactionManagement
@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Search_keyMapper search_keyMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private LabelMapper labelMapper;
//    @Autowired
//    private JSONObject result;

    @Override
    public Integer getTotal() {
        return articleMapper.getTotal();
    }

    @Transactional
    @Override
    public JSONObject update(Map<String, String> map) {
        JSONObject result = new JSONObject();
        try {
            articleMapper.updateByPrimaryKey(map);
            result.put("msg", "插入成功");
            result.put("code", "0");
        } catch (Exception e) {
            result.put("msg", "插入失败");
            result.put("code", "500");
            e.printStackTrace();
        }

        return result;
    }

    @Transactional
    @Override
    public JSONObject deleteArticle(Integer id) {
        JSONObject result = new JSONObject();
        try {
            articleMapper.deleteByPrimaryKey(id);
            result.put("msg", "修改成功");
            result.put("code", "0");
        } catch (Exception e) {
            result.put("msg", "修改失败");
            result.put("code", "500");
            e.printStackTrace();
        }

        return result;
    }

    @Transactional
    @Override
    public JSONObject collectService(Map<String, String> map) {
        JSONObject result = new JSONObject();
        try {
            if (map.get("select") != null) {
                int count = collectMapper.selectCount(map);
                result.put("count", count);
            } else if (map.get("delete") != null) {
                collectMapper.deleteByPrimaryKey(map);
                map.put("id", map.get("article_id"));
                map.put("collect", "1");
                map.put("delete", "1");
                articleMapper.updateByPrimaryKey(map);
            } else if (map.get("add") != null) {
                collectMapper.insert(map);
                map.put("id", map.get("article_id"));
                map.put("collect", "1");
                map.put("add", "1");
                articleMapper.updateByPrimaryKey(map);
            }

            result.put("msg", "处理成功");
            result.put("code", "0");
        } catch (Exception e) {
            result.put("msg", "处理失败");
            result.put("code", "500");
            e.printStackTrace();
        }


        return result;
    }

    @Transactional
    @Override
    public JSONObject followService(Map<String, String> map) {
        JSONObject result = new JSONObject();
        try {
            if (map.get("delete") != null)
                followMapper.deleteByPrimaryKey(map);
            else
                followMapper.insert(map);
            result.put("msg", "修改成功");
            result.put("code", "0");
        } catch (Exception e) {
            result.put("msg", "修改失败");
            result.put("code", "500");
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public JSONObject search_keyService() {
        JSONObject result=new JSONObject();
        try {
            List<Search_key> keyList=search_keyMapper.selectAll();
            result.put("data",keyList);
            result.put("msg", "查找成功");
            result.put("code", "0");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "查找异常");
            result.put("code", "500");
        }
        return result;
    }

    @Transactional
    @Override
    public JSONObject searchByCondition(Map<String, String> condition) {
        JSONObject result = new JSONObject();
        try {



            List<Article> articleList = articleMapper.searchByCondition(condition);

            if (condition.get("select") != null&&articleList.size()>0) {

                if (search_keyMapper.selectByName(condition.get("key")) == null)
                    search_keyMapper.insert(condition.get("key"));
                else
                    search_keyMapper.updateByName(condition.get("key"));
            }


            result.put("data", articleList);
            result.put("count", articleList.size());

            if (condition.get("collect") != null)
                result.put("class", "collect");
            else if (condition.get("article") != null)
                result.put("class", "article");

            result.put("msg", "查找成功");
            result.put("code", "0");
        } catch (Exception e) {
            result.put("msg", "查找异常");
            result.put("code", "500");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject searchAll(Integer pageNum, Integer pageSize) {
        JSONObject result = new JSONObject();
        try {
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<Article> info = new PageInfo<>(articleMapper.selectAll());
            result.put("data", info.getList());
            result.put("count", info.getTotal());
            result.put("msg", "查询成功");
            result.put("code", "0");
        } catch (NumberFormatException e) {
            result.put("msg", "查询异常");
            result.put("code", "500");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject selectByPrimaryKey(Integer id) {
        JSONObject result = new JSONObject();
        try {
            Article article = articleMapper.selectByPrimaryKey(id);
            result.put("article", article);
            result.put("msg", "查询成功");
            result.put("code", "0");
        } catch (Exception e) {
            result.put("msg", "查询异常");
            result.put("code", "500");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject addArticle(Article article) {
        JSONObject result = new JSONObject();
        try {
            articleMapper.insert(article);
            String label = article.getLabel();
            label = label.replace("\r\n", "");
            label = label.replace("<ul><li>", "");
            String[] labelList = label.split("</li><li>|</li></ul>");
            for (String s : labelList) {
                if (labelMapper.selectByName(s) == null)
                    labelMapper.insert(s);
                else {
                    Map<String, String> labelMap = new HashMap<>();
                    labelMap.put("name", s);
                    labelMap.put("count", "1");
                    labelMapper.updateByName(labelMap);
                }

            }
            result.put("msg", "插入成功");
            result.put("code", "0");
        } catch (Exception e) {
            result.put("msg", "插入失败");
            result.put("code", "500");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public JSONObject labelService(String update) {
        JSONObject result = new JSONObject();
        try {
            if (update != null) {
                Map<String, String> labelMap = new HashMap<>();
                labelMap.put("name", update);
                labelMap.put("hot", "1");
                labelMapper.updateByName(labelMap);
            } else {
                List<Label> labelList = labelMapper.selectAll();
                result.put("data", labelList);
            }
            result.put("msg", "操作成功");
            result.put("code", "0");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "操作失败");
            result.put("code", "500");
        }
        return result;
    }
}
