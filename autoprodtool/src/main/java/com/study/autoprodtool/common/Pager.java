/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;

/**
 * Descriptions
 * 
 * @version 2015-2-5
 * @author SUNTEC
 * @since JDK1.6
 * 
 */
public class Pager {
	private Integer total;
	private Integer page;
	private Integer limit;

	/**
	 * @param total
	 * @param page
	 * @param limit
	 */
	public Pager(Integer page, Integer limit, Integer total) {
		super();
		this.total = total;
		this.page = page;
		this.limit = limit;
	}

	/**
	 * 
	 */
	public Pager() {

	}

	public Integer getTotal() {
		return total;
	}

	public Pager setTotal(Integer total) {
		this.total = total;
		return this;
	}

	/**
	 * page number start from 1.
	 * 
	 * @return
	 */
	public Integer getPage() {
		return page;
	}

	public Pager setPage(Integer page) {
		this.page = page;
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public Pager setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public Integer getOffset() {
		if (page != null && limit != null) {
			return (page - 1)* limit;
		}
		return 0;
	}

	public Integer getTotalPages() {
		if (total != null && limit != null && limit > 0) {
			return total / limit + 1;
		}
		return 0;
	}
	
}
