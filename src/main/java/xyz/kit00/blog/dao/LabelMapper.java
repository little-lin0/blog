package xyz.kit00.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xyz.kit00.blog.bean.Label;
@Repository
public interface LabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(String record);

    Label selectByPrimaryKey(Integer id);

    List<Label> selectAll();

    int updateByPrimaryKey(String record);
    @Select("select * from label where name=#{name}")
    Label selectByName(String s);

    int updateByName(Map<String, String> map);
}