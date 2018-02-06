/*
 * Powered By [Chenkezhao]
 * Web Site: http://www.chenkezhao.com/
 * Since 2014 - 2018
 */
package com.chenkezhao.clientinf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;

import com.chenkezhao.clientinf.entity.Category;

/**
 * 访问T_FT_CATEGORY
 * @author Chenkezhao
 *
 */
@Mapper
public interface CategoryDao {

	/**
	 * 插入
	 * @param category
	 */
	@PreAuthorize("#oauth2.hasScope('write')")
	void insert(Category category);

	/**
	 * 更新
	 * @param category
	 */
	@PreAuthorize("#oauth2.hasScope('write')")
	void update(Category category);

	/**
	 * 删除
	 * @param id
	 */
	@PreAuthorize("#oauth2.hasScope('write')")
	void delete(int id);

	/**
	 * 查询分页列表（列表管理）
	 * @param paramMap
	 */
	List<Map> queryCategoryPageList(Map paramMap);

	/**
	 * 根据id查询
	 * @param id
	 */
	Category getById(int id);

}
