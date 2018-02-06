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

import com.chenkezhao.clientinf.dao.ParamsDao;
import com.chenkezhao.clientinf.entity.Params;
import com.chenkezhao.framework.mybatis.PageInfo;

/**
 * T_FT_PARAMS
 * @author Chenkezhao
 *
 */
@Component
@Transactional(readOnly = true)
public class ParamsService {
	
	@Autowired
	ParamsDao paramsDao;

	public ParamsDao getParamsDao(){
		return this.paramsDao;
	}

	/**
	 * 查询分页列表（列表管理）
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public List<Map> queryForManage(Map searchParams, PageInfo page) {
		searchParams.put("page", page);// 分页插件条件
		List<Map> list = paramsDao.queryParamsPageList(searchParams);
		return list;
	}

	/**
	 * 插入
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = false)
	public Params insert(Params params) {
		paramsDao.insert(params);
		return params;
	}

	/**
	 * 更新
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = false)
	public Params update(Params params) {
		paramsDao.update(params);
		return params;
	}
	/**
	 * 删除
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(int id) {
		paramsDao.delete(id);
	}

	/**
	 * 根据主键获取
	 * @param id
	 * @return
	 */
	public Params getById(int id) {
		Params params = paramsDao.getById(id);
		return params;
	}

}
