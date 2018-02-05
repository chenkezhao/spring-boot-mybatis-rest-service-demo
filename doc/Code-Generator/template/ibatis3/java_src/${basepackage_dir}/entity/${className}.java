<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.chenkezhao.framework.utils.DateConvertUtils;

/**
 * ${table.sqlName}
 * @author Chenkezhao
 *
 */
public class ${className} extends BaseEntity{
	//alias
	public static final String TABLE_ALIAS = "${table.sqlName}";

	//columns START
	<#list table.columns as column>
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	//columns END

<@generateConstructor className/>
<@generateJavaColumns/>


	public String toString() {
		return new ReflectionToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.${table.pkColumn.columnNameLower}).toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnName}(),other.get${column.columnName}())
			</#list>
			.isEquals();
	}
}

<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
	public String get${column.columnName}String() {
		return DateConvertUtils.dateTimeToString(get${column.columnName}());

	}

		</#if>
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}

	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>
