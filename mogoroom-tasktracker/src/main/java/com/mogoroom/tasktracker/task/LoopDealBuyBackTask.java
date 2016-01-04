package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ILoanLandLordFacade;

/**
 * 参见原BS.LandlordQuartz.loopDealBuyBack方法
 * 
 * @author admin
 * 
 */
@Component
public class LoopDealBuyBackTask implements Task {

	public static Log logger = LogFactory.getLog(LoopDealBuyBackTask.class);

	@Autowired
	private ILoanLandLordFacade loanLandlordFacade;

	/**
	  * @Description: 每天定时器循环调用买回生成方法
	  * @author Andubu
	  * @date 2015年8月22日 下午3:41:46  
	  * @throws
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		loanLandlordFacade.loopDealBuyBack();
	}

}
