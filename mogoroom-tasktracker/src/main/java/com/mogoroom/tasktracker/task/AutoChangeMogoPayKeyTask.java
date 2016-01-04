package com.mogoroom.tasktracker.task;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mogoroom.constant.MogoConstant;
import com.mogoroom.util.MogoNumberUtil;

import net.rubyeye.xmemcached.MemcachedClient;

/**
 * 每天定时修改一次API的 key。key随机
 * @author charlesfeng
 *
 */
@Component
public class AutoChangeMogoPayKeyTask implements Task{
	
	public static Log logger = LogFactory.getLog(AutoChangeMogoPayKeyTask.class);
	
	@Autowired
	private MemcachedClient client;

	@Override
	public void run(Map<String, String> params) throws Throwable {		
		try {
			logger.info("==》每天定时修改一次 API的密码。");
			client.set(MogoConstant.MOGO_PAY_KEY, (60*60*24*7), MogoNumberUtil.creatRandomString(32));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
	
	

}
