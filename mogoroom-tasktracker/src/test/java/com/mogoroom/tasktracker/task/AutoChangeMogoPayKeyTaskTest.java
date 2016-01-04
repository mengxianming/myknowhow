package com.mogoroom.tasktracker.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mogoroom.tasktracker.BaseTest;
import com.mogoroom.tasktracker.ParamsBuilder;

public class AutoChangeMogoPayKeyTaskTest extends BaseTest{
	@Autowired
	AutoChangeMogoPayKeyTask task;

	@Test
	public void testRun() throws Throwable {
		task.run(ParamsBuilder.newOne().
				put("taskImpl", AutoChangeMogoPayKeyTask.class.getName())
				.build());
	}

}
