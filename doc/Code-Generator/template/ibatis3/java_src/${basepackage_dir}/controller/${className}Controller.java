<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller;

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

import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;

/**
 * ${table.sqlName}
 * @author Chenkezhao
 *
 */
@RestController
@RequestMapping(value = "/${namespace}/${classNameLower}")
public class ${className}Controller {

	@Autowired
	private ${className}Service ${classNameLower}Service;

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
		List<Map> list = ${classNameLower}Service.queryForManage(searchParams, pageInfo);
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
			${classNameLower}Service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage(e.getMessage());
		}
		return jsonResponse;
	}

	/**
	 * 保存
	 * @param ${classNameLower}
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse save(${className} ${classNameLower}, HttpServletRequest request,
			                             HttpServletResponse response) {
		JsonResponse jsonResponse = new JsonResponse(true, null, null);
		try {

			if (StringUtils.isEmpty(${classNameLower}.get${table.pkColumn.columnName}()) ) {
				${classNameLower} = ${classNameLower}Service.insert(${classNameLower});
			} else {
				${className} old${className} = ${classNameLower}Service.getBy${table.pkColumn.columnName}(${classNameLower}.get${table.pkColumn.columnName}());
				<#list table.columns as column>
				old${className}.set${column.columnName}(${classNameLower}.get${column.columnName}());
				</#list>
				${classNameLower} = ${classNameLower}Service.update(old${className});
			}
			jsonResponse.setData(${classNameLower});
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
	public ${className} queryById(int id) {
			return ${classNameLower}Service.getById(id);
	}

}
