package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ILoanLandLordFacade;

/**
 * 参见原BS.LandlordQuartz.closeAllSuspendMogoBao方法
 * 
 * @author admin
 * 
 */
@Component
public class CloseAllSuspendMogoBaoTask implements Task {

	public static Log logger = LogFactory.getLog(CloseAllSuspendMogoBaoTask.class);

	@Autowired
	private ILoanLandLordFacade loanLandlordFacade;

	/**
	  * @Description: 关闭所有中止的蘑菇宝
	  * @author hzm
	  * @date 2015年12月5日 下午4:41:46  
	  * @throws
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		loanLandlordFacade.closeAllSuspendMogoBao();
	}

}
