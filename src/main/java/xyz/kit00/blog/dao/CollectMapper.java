package xyz.kit00.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.kit00.blog.bean.Collect;
@Repository
public interface CollectMapper {
    int deleteByPrimaryKey(Map<String, String> map);

    int insert(Map<String, String> record);

    List<Collect> selectAll();

    @Select("select count(*) from collect where article_id=#{article_id} and collect_id=#{collect_id}")
    @ResultType(Integer.class)
    int selectCount(Map<String, String> map);
}