package com.chenkezhao.framework.mybatis;

import java.io.Serializable;
import java.util.List;

public class PageResponse implements Serializable {
    private List list;
    private Pagination pagination;

    public PageResponse() {

    }

    public PageResponse(List rows, PageInfo page) {
        this.list = rows;
        this.pagination = new Pagination( page.getTotalResult(),page.getCurrentPage(),page.getTotalPage());
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    class Pagination {
        /**
         * 总条数
         */
        private int total;
        /**
         * Current page
         */
        private int current;
        /**
         * Page size
         */
        private int pageSize;

        public Pagination() {
        }

        public Pagination(int total, int current, int pageSize) {
            this.total = total;
            this.current = current;
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }
}
