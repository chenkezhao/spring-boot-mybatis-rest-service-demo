<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ${basepackage}.entity.${className};

/**
 * 访问${table.sqlName}
 * @author Chenkezhao
 *
 */
@Mapper
public interface ${className}Dao {

	/**
	 * 插入
	 * @param ${classNameLower}
	 */
	void insert(${className} ${classNameLower});

	/**
	 * 更新
	 * @param ${classNameLower}
	 */
	void update(${className} ${classNameLower});

	/**
	 * 删除
	 * @param ${table.pkColumn.columnNameLower}
	 */
	void delete(String ${table.pkColumn.columnNameLower});

	/**
	 * 查询分页列表（列表管理）
	 * @param paramMap
	 */
	List<Map> query${className}PageList(Map paramMap);

	/**
	 * 根据id查询
	 * @param ${table.pkColumn.columnNameLower}
	 */
	${className} getById(String ${table.pkColumn.columnNameLower});

}
