package xyz.kit00.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.kit00.blog.bean.Follow;
@Repository
public interface FollowMapper {
    int deleteByPrimaryKey(Map<String,String> map);

    int insert(Map<String,String> map);

    List<Follow> selectAll();
}