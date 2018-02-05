package com.chenkezhao.framework.mybatis;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;


@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PagePlugin implements Interceptor {
	private static String dialect = "";
	private static String pageSqlId = "";

	@SuppressWarnings("unchecked")
	public Object intercept(Invocation ivk) throws Throwable {

		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
					.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
					.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper
					.getValueByFieldName(delegate, "mappedStatement");


			if (mappedStatement.getId().matches(pageSqlId)) {
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject为空.");
				} else {
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					
					Map<String, Object> paramMap = null;
					if((parameterObject instanceof Map)){
						paramMap = (Map<String, Object>) parameterObject;
					}
					
					// page为Null时，不作分页查询
					if(paramMap!=null && paramMap.get("page")==null){
						ReflectHelper.setValueByFieldName(boundSql, "sql", sql);
					}else{
						String countSql = "select count(0) from (" + sql
								+ ") temp ";
						PreparedStatement countStmt = connection
								.prepareStatement(countSql);
						BoundSql countBS = new BoundSql(
								mappedStatement.getConfiguration(), countSql,
								boundSql.getParameterMappings(), parameterObject);
						setParameters(countStmt, mappedStatement, countBS,
								parameterObject);
						ResultSet rs = countStmt.executeQuery();
						int count = 0;
						if (rs.next()) {
							count = rs.getInt(1);
						}
						rs.close();
						countStmt.close();

						PageInfo page = null;
						if (parameterObject instanceof PageInfo) {
							page = (PageInfo) parameterObject;
							page.setTotalResult(count);
							page.setTotalPage(count);
						} else if (parameterObject instanceof Map) {
							Map<String, Object> map = (Map<String, Object>) parameterObject;
							page = (PageInfo) map.get("page");
							if (page == null)
								page = new PageInfo();
							page.setTotalResult(count);
							page.setTotalPage(count);
						} else {
							Field pageField = ReflectHelper.getFieldByFieldName(
									parameterObject, "page");
							if (pageField != null) {
								page = (PageInfo) ReflectHelper
										.getValueByFieldName(parameterObject,
												"page");
								if (page == null)
									page = new PageInfo();
								page.setTotalResult(count);
								page.setTotalPage(count);
								ReflectHelper.setValueByFieldName(parameterObject,
										"page", page);
							} else {
								throw new NoSuchFieldException(parameterObject
										.getClass().getName());
							}
						}
						String pageSql = generatePageSql(sql, page);
						ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
					}
				}
			}
		}
		return ivk.proceed();
	}

	/**
	 * DefaultParameterHandler
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters")
				.object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(
											propertyName.substring(prop
													.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement "
										+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value,
							parameterMapping.getJdbcType());
				}
			}
		}
	}

	/**
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePageSql(String sql, PageInfo page) {
 		if (page != null && (dialect != null || !dialect.equals(""))) {
			StringBuffer pageSql = new StringBuffer();
			
			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				int start = (page.getCurrentPage() - 1) * page.getShowCount();
				page.setCurrentResult(start);
				pageSql.append(" limit " + page.getCurrentResult() + ","
						+ page.getShowCount());
			} else if ("oracle".equals(dialect)) {
				/*int start = (page.getCurrentPage() - 1) * page.getShowCount();
				page.setCurrentResult(start);
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(")  tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentResult() + page.getShowCount());
				pageSql.append(") where row_id>");
				pageSql.append(page.getCurrentResult());*/
				
				int start = (page.getCurrentPage() - 1) * page.getShowCount();
				page.setCurrentResult(start);
				pageSql.append("select *from (select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(")  tmp_tb");
				pageSql.append(") where row_id<=");
				pageSql.append(page.getCurrentResult() + page.getShowCount());
				pageSql.append(")");
				pageSql.append("where row_id>");
				pageSql.append(page.getCurrentResult());
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (dialect == null || dialect.equals("")) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
