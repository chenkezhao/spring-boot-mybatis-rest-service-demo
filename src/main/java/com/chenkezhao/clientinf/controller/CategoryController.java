/*
 * Powered By [Chenkezhao]
 * Web Site: http://www.chenkezhao.com/
 * Since 2014 - 2018
 */
package com.chenkezhao.clientinf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chenkezhao.framework.JsonResponse;
import com.chenkezhao.framework.Servlets;
import com.chenkezhao.framework.mybatis.PageInfo;
import com.chenkezhao.framework.mybatis.PageResponse;

import com.chenkezhao.clientinf.entity.Category;
import com.chenkezhao.clientinf.service.CategoryService;

/**
 * T_FT_CATEGORY
 * @author Chenkezhao
 *
 */
@RestController
@RequestMapping(value = "/clientinf/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 获取查询管理列表
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryForManage")
	@ResponseBody
	public PageResponse queryForManage(int page, int rows,HttpServletRequest request) {
		Map searchParams = Servlets.getParametersStartingWith(request,"search_");
		PageInfo pageInfo = new PageInfo(page, rows);
		List<Map> list = categoryService.queryForManage(searchParams, pageInfo);
		PageResponse pageResponse = new PageResponse(list, pageInfo);
		return pageResponse;
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse delete(int id, HttpServletRequest request,
														 HttpServletResponse response) {
		JsonResponse jsonResponse = new JsonResponse(true, null, null);
		try {
			categoryService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage(e.getMessage());
		}
		return jsonResponse;
	}

	/**
	 * 保存
	 * @param category
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse save(Category category, HttpServletRequest request,
			                             HttpServletResponse response) {
		JsonResponse jsonResponse = new JsonResponse(true, null, null);
		try {

			if (StringUtils.isEmpty(category.getId()) ) {
				category = categoryService.insert(category);
			} else {
				Category oldCategory = categoryService.getById(category.getId());
				oldCategory.setId(category.getId());
				oldCategory.setParentid(category.getParentid());
				oldCategory.setName(category.getName());
				oldCategory.setCode(category.getCode());
				oldCategory.setRemark(category.getRemark());
				category = categoryService.update(oldCategory);
			}
			jsonResponse.setData(category);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage(e.getMessage());
		}
		return jsonResponse;
	}

	/**
	 * 查询对象实体
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "queryById")
	@ResponseBody
	public Category queryById(int id) {
			return categoryService.getById(id);
	}

}
