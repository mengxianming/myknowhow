package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ITimerFacade;

/**
 * 参见原BS.LoanQuartz.closeMogoBaoWhenLoanOverDuedate方法
 * 
 * @author admin
 * 
 */
@Component
public class CloseMogoBaoWhenLoanOverDuedateTask implements Task {

	public static Log logger = LogFactory.getLog(CloseMogoBaoWhenLoanOverDuedateTask.class);

	@Autowired
	private ITimerFacade timerFacade;

	/**
	 * 【需要配置定时器】<br/>
	 * 租金借款逾期中止：租客超过逾期日期，中止其蘑菇宝
	 * 
	 * @author szl
	 * @date 2015年8月20日 下午2:19:02
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		timerFacade.closeMogoBaoWhenLoanOverDuedate();
	}

}
