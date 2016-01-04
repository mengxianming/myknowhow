package com.mogoroom.tasktracker.task;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ILoanFacade;
import com.mogoroom.service.base.enums.LoanContractStatus;
import com.mogoroom.service.domain.loan.RenterLoanContract;

/**
 * 参见原BS.LoanQuartz.syncContractStatus方法
 * 
 * @author admin
 * 
 */
@Component
public class SyncContractStatusTask implements Task {

	public static Log logger = LogFactory.getLog(SyncContractStatusTask.class);

	@Autowired
	private ILoanFacade loanFacade;

	/**
	 * 同步合同状态
	 * 
	 * @throws Exception
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		// loanFacade.syncContractStatus();
		List<RenterLoanContract> contracts = loanFacade.findRenterLoanContractsByStatus(LoanContractStatus.REVIEWING.getIndex());
		for (RenterLoanContract contract : contracts) {
			loanFacade.syncContractStatus(contract);
		}
	}

}
