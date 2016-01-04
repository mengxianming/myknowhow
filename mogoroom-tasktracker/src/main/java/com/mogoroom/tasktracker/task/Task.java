package com.mogoroom.tasktracker.task;

import java.util.Map;

/**
 * 任务接口。根据不同业务实现本接口。
 * @author mengxianming-2015年12月18日
 *
 */
public interface Task {
	/**
	 * 任务执行入口。参数由任务提交时指定。
	 * @param params 执行任务所需的各种参数
	 * @throws Throwable
	 */
	void run(Map<String, String> params) throws Throwable;
}
