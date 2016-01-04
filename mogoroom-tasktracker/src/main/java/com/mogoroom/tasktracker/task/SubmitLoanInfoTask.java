package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ILoanFacade;

/**
 * 参见原BS.LoanQuartz.submitLoanInfo方法
 * 
 * @author admin
 * 
 */
@Component
public class SubmitLoanInfoTask implements Task {

	public static Log logger = LogFactory.getLog(SubmitLoanInfoTask.class);

	@Autowired
	private ILoanFacade loanFacade;

	/**
	 * 提交贷款资料给贷款公司
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		loanFacade.submitLoanInfo();
	}

}
