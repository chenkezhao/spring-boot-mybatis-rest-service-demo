/*
 * Powered By [Chenkezhao]
 * Web Site: http://www.Chenkezhao.com/
 * Since 2014 - 2018
 */
package com.chenkezhao.clientinf.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.chenkezhao.framework.utils.DateConvertUtils;

/**
 * T_FT_PARAMS
 * @author Chenkezhao
 *
 */
public class Params extends BaseEntity{
	//alias
	public static final String TABLE_ALIAS = "T_FT_PARAMS";

	//columns START
	private Integer id;
	private String name;
	private String pkey;
	private String pvalue;
	private String remark;
	//columns END

	public Params(){
	}

	public Params(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}
	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}
	public void setPkey(String value) {
		this.pkey = value;
	}

	public String getPkey() {
		return this.pkey;
	}
	public void setPvalue(String value) {
		this.pvalue = value;
	}

	public String getPvalue() {
		return this.pvalue;
	}
	public void setRemark(String value) {
		this.remark = value;
	}

	public String getRemark() {
		return this.remark;
	}


	public String toString() {
		return new ReflectionToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.id).toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof Params == false) return false;
		if(this == obj) return true;
		Params other = (Params)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

