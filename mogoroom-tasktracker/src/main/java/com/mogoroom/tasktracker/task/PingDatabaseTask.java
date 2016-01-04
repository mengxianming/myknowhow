package com.mogoroom.tasktracker.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.facade.ICommonFacade;

import java.util.Map;

import org.apache.log4j.Logger;

@Component
public class PingDatabaseTask implements Task{

	@Autowired
	private ICommonFacade commonFacade;
	public static Logger logger = Logger.getLogger(PingDatabaseTask.class);
	@Override
	public void run(Map<String, String> params) throws Throwable {
		try{
			logger.info("Ping database again at "+commonFacade.now());
		}catch(Exception ex){
			logger.error("",ex);
			throw ex;
		}

	}


}
