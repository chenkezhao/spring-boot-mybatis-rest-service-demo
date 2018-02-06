/*
 * Powered By [Chenkezhao]
 * Web Site: http://www.chenkezhao.com/
 * Since 2014 - 2018
 */
package com.chenkezhao.clientinf.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chenkezhao.clientinf.dao.CategoryDao;
import com.chenkezhao.clientinf.entity.Category;
import com.chenkezhao.framework.mybatis.PageInfo;

/**
 * T_FT_CATEGORY
 * @author Chenkezhao
 *
 */
@Component
@Transactional(readOnly = true)
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;

	public CategoryDao getCategoryDao(){
		return this.categoryDao;
	}

	/**
	 * 查询分页列表（列表管理）
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public List<Map> queryForManage(Map searchParams, PageInfo page) {
		searchParams.put("page", page);// 分页插件条件
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
	public void delete(int id) {
		categoryDao.delete(id);
	}

	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	public Category getById(int id) {
		Category category = categoryDao.getById(id);
		return category;
	}

}
