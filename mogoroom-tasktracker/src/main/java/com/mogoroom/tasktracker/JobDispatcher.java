package com.mogoroom.tasktracker;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.lts.core.domain.Action;
import com.lts.core.domain.Job;
import com.lts.tasktracker.Result;
import com.lts.tasktracker.runner.JobRunner;
import com.mogoroom.tasktracker.exception.IllegalTaskException;
import com.mogoroom.tasktracker.task.Task;

/**
 * 作业分发器。客户提交的作业将根据参数分发给不同的任务执行类执行。<br>
 * 客户在提交作业时必须指定taskImpl参数。该参数为Task接口的实现类全名。<br>
 * <pre>例如：
 *    Job job = new Job();
 *    job.setTaskId("11112222");
 *    job.setParam("taskImpl", "com.mogoroom.tasktracker.task.SMSSendTaskImpl");
 *  </pre>
 * @author mengxianming-2015年12月17日
 *
 */
public class JobDispatcher implements JobRunner{
	private static ApplicationContext ctx;
	
	public static void setApplicationContext(ApplicationContext context){
		ctx = context;
	}

	@Override
	public Result run(Job job) throws Throwable {
		
		String taskImplName = job.getParam("taskImpl");
		if(taskImplName == null || taskImplName.length() == 0){
			throw new IllegalTaskException("提交job时没有指定合法的taskImpl参数。该参数为Task接口的实现类全名。taskImpl=" + taskImplName);
		}
		
		Class<?> clazz;
		try {
			clazz = Class.forName(taskImplName);
		} catch (Exception e) {
			throw new IllegalTaskException("提交job时没有指定合法的taskImpl参数。该参数为Task接口的实现类全名。taskImpl=" + taskImplName, e);
		}
		if(!Task.class.isAssignableFrom(clazz)){
			throw new IllegalTaskException("提交job时没有指定合法的taskImpl参数。该参数为Task接口的实现类全名。taskImpl=" + taskImplName);
		}
				
		Task taskImpl = (Task) ctx.getBean(clazz);
		
		Map<String, String> params = job.getExtParams();
		if(params == null){
			params = Collections.emptyMap();
		}		
		taskImpl.run(params);
		
		return new Result(Action.EXECUTE_SUCCESS, "Sucess!");
	}

}
