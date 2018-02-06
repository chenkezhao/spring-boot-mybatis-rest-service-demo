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

import com.chenkezhao.clientinf.entity.Params;

/**
 * 访问T_FT_PARAMS
 * @author Chenkezhao
 *
 */
@Mapper
public interface ParamsDao {

	/**
	 * 插入
	 * @param params
	 */
	@PreAuthorize("#oauth2.hasScope('write')")
	void insert(Params params);

	/**
	 * 更新
	 * @param params
	 */
	@PreAuthorize("#oauth2.hasScope('write')")
	void update(Params params);

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
	List<Map> queryParamsPageList(Map paramMap);

	/**
	 * 根据id查询
	 * @param id
	 */
	Params getById(int id);

}
