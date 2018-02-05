/*
 * Powered By [Chenkezhao]
 * Web Site: http://www.Chenkezhao.com/
 * Since 2014 - 2018
 */
package com.chenkezhao.clientinf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	void insert(Params params);

	/**
	 * 更新
	 * @param params
	 */
	void update(Params params);

	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);

	/**
	 * 查询分页列表（列表管理）
	 * @param paramMap
	 */
	List<Map> queryParamsPageList(Map paramMap);

	/**
	 * 根据id查询
	 * @param id
	 */
	Params getById(String id);

}
