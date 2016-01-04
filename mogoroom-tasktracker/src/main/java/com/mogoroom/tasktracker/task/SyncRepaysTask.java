package com.mogoroom.tasktracker.task;

import java.util.Calendar;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ILoanFacade;

/**
 * 参见原BS.LoanQuartz.syncRepays方法
 * 
 * @author admin
 * 
 */
@Component
public class SyncRepaysTask implements Task {

	public static Log logger = LogFactory.getLog(SyncRepaysTask.class);

	@Autowired
	private ILoanFacade loanFacade;

	/**
	 * 同步还款信息
	 * 
	 * @throws Exception
	 */
	@Override
	public void run(Map<String, String> params) throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, -1);

		Calendar cal2 = Calendar.getInstance();
		// cal2.set(Calendar.HOUR_OF_DAY, 0);
		// cal2.set(Calendar.MINUTE, 0);
		// cal2.set(Calendar.SECOND, 0);
		// cal2.set(Calendar.MILLISECOND, 0);

		loanFacade.syncRepays(cal.getTime(), cal2.getTime());
	}

}
