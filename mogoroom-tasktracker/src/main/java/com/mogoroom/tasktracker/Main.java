package com.mogoroom.tasktracker;

import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

public class Main{	
	private static Log logger = LogFactory.getLog(Main.class);
	private static final boolean runLocal = false;
	private static String confFilePath;
	private static String log4jConf;
	private static String springConf;

	public static void main(String[] args) {		
		configPaths(args, runLocal);
		
		//系统配置被spring配置文件使用
		System.setProperty("confFilePath", confFilePath);
		
		try {
			Log4jConfigurer.initLogging(log4jConf);
		} catch (FileNotFoundException e) {
			logger.warn("初始化log出错。", e);
		}
		
		
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {springConf});
		
				
		
		final TaskTrackerStarter starter = context.getBean(TaskTrackerStarter.class);
		starter.start();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				starter.stop();
				context.close();
			}
		}));
				
	}

	private static void configPaths(String[] args, boolean runLocal) {
		if(runLocal){
			confFilePath = "classpath:/conf/*.properties";
			log4jConf = "classpath:/conf/log4j.properties";
			springConf = "classpath:/context/spring-*.xml";
			return;
		}
		
		String confRoot = System.getProperty("user.dir");
		System.out.println("Current Path:" + confRoot);
		
		if(args.length > 0){
			confRoot = args[0].trim();
		}
		confFilePath = "file:" + confRoot + "/conf/*.properties";
		log4jConf = "file:" + confRoot + "/conf/log4j.properties";
		springConf = "file:" + confRoot + "/context/spring-*.xml";
	}
}