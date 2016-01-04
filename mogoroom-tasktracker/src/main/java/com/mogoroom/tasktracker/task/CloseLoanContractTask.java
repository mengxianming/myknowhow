package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ILoanLandLordFacade;
import com.mogoroom.util.CheckUtil;

@Component
public class CloseLoanContractTask implements Task {
	private static Log log = LogFactory.getLog(CloseLoanContractTask.class);
	@Autowired
	ILoanLandLordFacade loanLandlordFacade;

	@Override
	public void run(Map<String, String> params) throws Throwable {
		log.debug("task params:" + params);
		String signedOrderId = params.get("signedOrderId");
		if (CheckUtil.isEmpty(signedOrderId)) {
			throw new IllegalArgumentException("请输入要关闭蘑菇宝的签约单号");
		}		
		
		log.info("开始关闭蘑菇宝:" + signedOrderId);
		loanLandlordFacade.closeMogoBao(Integer.valueOf(signedOrderId),null);
		log.info("完成关闭蘑菇宝:" + signedOrderId);
		
	}
	
}