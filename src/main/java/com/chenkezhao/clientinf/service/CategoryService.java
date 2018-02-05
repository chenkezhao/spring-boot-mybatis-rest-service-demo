package com.chenkezhao.clientinf.service;

import com.chenkezhao.clientinf.dao.CategoryDao;
import com.chenkezhao.clientinf.entity.Category;
import com.chenkezhao.framework.mybatis.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
@Transactional(readOnly = true)
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 查询分页列表（列表管理）
     * @param searchParams
     * @param page
     * @return
     */
    public List<Map> queryForManage(Map searchParams, PageInfo page) {
        searchParams.put("page", page);//分页插件条件
        List<Map> list = categoryDao.queryCategoryPageList(searchParams);
        return list;
    }

    /**
     * 插入
     * @param category
     * @return
     */
    @Transactional(readOnly = false)
    public Category insert(Category category) {
        categoryDao.insert(category);
        return category;
    }

    /**
     * 更新
     * @param category
     * @return
     */
    @Transactional(readOnly = false)
    public Category update(Category category) {
        categoryDao.update(category);
        return category;
    }
    /**
     * 删除
     * @param id
     */
    @Transactional(readOnly = false)
    public void delete(long id) {
        categoryDao.delete(id);
    }

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    public Category getById(long id) {
        Category category = categoryDao.getById(id+"");
        return category;
    }
}
