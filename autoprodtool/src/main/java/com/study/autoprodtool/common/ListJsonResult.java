/**
 *  Copyright(C) 2013 Suntec Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.study.autoprodtool.common;

import java.util.List;

/**
 * Descriptions
 *
 * @version 2015-2-5
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class ListJsonResult extends JsonResult{	
	private Pager pager;

	public ListJsonResult() {

	}
	
	public static ListJsonResult success(List<?> data, Pager pager) {
		ListJsonResult ins = new ListJsonResult();
		ins.setCode(CommonErrorCodes.S_OK.code);
		ins.setData(data);
		ins.setPager(pager);
		return ins;
	}
	
	/**
	 * @return the pager
	 */
	public Pager getPager() {
		return pager;
	}
	/**
	 * @param pager the pager to set
	 */
	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
