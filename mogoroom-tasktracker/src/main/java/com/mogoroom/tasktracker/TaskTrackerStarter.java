package com.mogoroom.tasktracker;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lts.tasktracker.TaskTracker;
import com.mogoroom.util.CheckUtil;


/**
 * LTS TaskTracker启动类
 * @author mengxianming-2015年12月17日
 *
 */
public class TaskTrackerStarter implements ApplicationContextAware{		
	private String registryAddress;
	private String nodeGroup;
	private String clusterName;
	private int workThreads;
	private String monitorUrl;
	private String jobFailStore;
	private ApplicationContext ctx;
	private TaskTracker taskTracker;

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public String getNodeGroup() {
		return nodeGroup;
	}

	public void setNodeGroup(String nodeGroup) {
		this.nodeGroup = nodeGroup;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public int getWorkThreads() {
		return workThreads;
	}

	public void setWorkThreads(int workThreads) {
		this.workThreads = workThreads;
	}

	public String getMonitorUrl() {
		return monitorUrl;
	}

	public void setMonitorUrl(String monitorUrl) {
		this.monitorUrl = monitorUrl;
	}

	public String getJobFailStore() {
		return jobFailStore;
	}

	public void setJobFailStore(String jobFailStore) {
		this.jobFailStore = jobFailStore;
	}

	public void start(){
		taskTracker = new TaskTracker();
		// 任务执行类，实现JobRunner 接口
		JobDispatcher.setApplicationContext(ctx);
		taskTracker.setJobRunnerClass(JobDispatcher.class);
		taskTracker.setRegistryAddress(registryAddress);
		//	         taskTracker.setRegistryAddress("redis://127.0.0.1:6379");
		taskTracker.setNodeGroup(nodeGroup); // 同一个TaskTracker集群这个名字相同
		taskTracker.setClusterName(clusterName);
		taskTracker.setWorkThreads(workThreads);
		// 反馈任务给JobTracker失败，存储本地文件路径
		// taskTracker.setDataPath(Constants.USER_HOME);
		// master 节点变化监听器，当有集群中只需要一个节点执行某个事情的时候，可以监听这个事件
		//	        taskTracker.addMasterChangeListener(new MasterChangeListenerImpl());
		//	        taskTracker.addConfig(Constants.JOB_PULL_FREQUENCY, "1"); //设置TaskTracker Pull Job的频率，精度要求高的，可以设置为1
		// 业务日志级别
		// taskTracker.setBizLoggerLevel(Level.INFO);
		// 可选址  leveldb(默认), rocksdb, berkeleydb
		taskTracker.addConfig("job.fail.store", CheckUtil.isEmpty(jobFailStore) ? "leveldb" : jobFailStore);
		taskTracker.addConfig("lts.monitor.url", monitorUrl);
		//	         taskTracker.addConfig("lts.remoting", "mina");
		//	        taskTracker.addConfig("lts.remoting.serializable.default", "fastjson");
		taskTracker.start();
		
	}
	
	public void stop(){
		taskTracker.stop();		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;		
	}

}