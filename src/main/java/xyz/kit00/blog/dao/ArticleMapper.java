package xyz.kit00.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.kit00.blog.bean.Article;
@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAll();

    int updateByPrimaryKey(Map<String, String> map);

    @Select("select count(*) from article")
    Integer getTotal();

    List<Article> searchByCondition(Map<String, String> condition);
}