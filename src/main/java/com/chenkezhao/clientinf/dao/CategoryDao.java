package com.chenkezhao.clientinf.dao;

import com.chenkezhao.clientinf.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryDao {
    /**
     * 插入
     * @param category
     */
    void insert(Category category);

    /**
     * 更新
     * @param category
     */
    void update(Category category);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    void deleteByWorkPkid(String wkpkid);

    /**
     * 查询分页列表（列表管理）
     * @param paramMap
     */
    List<Map> queryCategoryPageList(Map paramMap);

    /**
     * 根据id查询
     * @param id
     */
    Category getById(@Param(value = "id")String id);
}
