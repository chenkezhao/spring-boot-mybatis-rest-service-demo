/*
 * Powered By [Chenkezhao]
 * Web Site: http://www.chenkezhao.com/
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
	private java.lang.Integer id;
	private java.lang.String name;
	private java.lang.String pkey;
	private java.lang.String pvalue;
	private java.lang.String remark;
	//columns END

	public Params(){
	}

	public Params(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getName() {
		return this.name;
	}
	public void setPkey(java.lang.String value) {
		this.pkey = value;
	}

	public java.lang.String getPkey() {
		return this.pkey;
	}
	public void setPvalue(java.lang.String value) {
		this.pvalue = value;
	}

	public java.lang.String getPvalue() {
		return this.pvalue;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.String getRemark() {
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

