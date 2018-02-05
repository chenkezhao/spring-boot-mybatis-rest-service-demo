package com.chenkezhao.clientinf.entity;

public class Category extends BaseEntity{
    private long id;
    private long parentid;
    private String name;
    private String code;
    private String remark;

    public Category() {
    }

    public Category(long id, long parentid, String name, String code, String remark) {
        this.id = id;
        this.parentid = parentid;
        this.name = name;
        this.code = code;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
