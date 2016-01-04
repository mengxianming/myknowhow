package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ITimerFacade;

@Component
public class MsgComingHostAcctTask implements Task {
	private static Log log = LogFactory.getLog(MsgComingHostAcctTask.class);
	@Autowired
	private ITimerFacade timerFacade;

	@Override
	public void run(Map<String, String> params) throws Throwable {
		log.info("开始业主账单前7天触发提醒，给房东发短信");
		timerFacade.msgToPartnerComingHostAcct();
		log.info("完成业主账单前7天触发提醒，给房东发短信");
	}
	
}