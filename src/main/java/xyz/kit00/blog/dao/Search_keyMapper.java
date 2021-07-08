package xyz.kit00.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.kit00.blog.bean.Search_key;
@Repository
public interface Search_keyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(String record);

    Search_key selectByPrimaryKey(Integer id);

    List<Search_key> selectAll();

    int updateByPrimaryKey(Search_key record);
    @Select(("select * from search_key where name=#{name}"))
    Search_key selectByName(String key);

    void updateByName(String key);
}