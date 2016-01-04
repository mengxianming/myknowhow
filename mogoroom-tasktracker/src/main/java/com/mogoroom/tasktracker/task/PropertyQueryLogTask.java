package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mogoroom.facade.ICommonFacade;
import com.mogoroom.facade.IFlatsFacade;

/**
 * 产调task
 * @author lcx
 * 
 *
 */
@Component
public class PropertyQueryLogTask implements Task{
	@Autowired
	private IFlatsFacade flatsFacade;
	@Autowired
	private ICommonFacade commonFacade;
	
	private static Log log = LogFactory.getLog(MsgHostAcctDuedateTask.class);
	@Override
	public void run(Map<String, String> params) throws Throwable {
		log.info("产调执行开始:" +commonFacade.now());
		flatsFacade.handleUnFinishedPropertyQueryLog();
		log.info("产调执行结束:" +commonFacade.now());
		
	}

}
