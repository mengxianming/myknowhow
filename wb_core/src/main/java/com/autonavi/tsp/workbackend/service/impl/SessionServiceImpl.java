/**
 * 
 */
package com.autonavi.tsp.workbackend.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.autonavi.tsp.workbackend.cache.jedis.SessionCacheExecutor;
import com.autonavi.tsp.workbackend.dto.SessionDto;
import com.autonavi.tsp.workbackend.exception.ValidationException;
import com.autonavi.tsp.workbackend.service.ISessionService;
import com.autonavi.tsp.workbackend.util.ExceptionCodeEnum;

/**
 * @author mengxianming
 *
 * 2015年5月11日
 */
@Service("sessionService")
public class SessionServiceImpl implements ISessionService{
	private static Log log = LogFactory.getLog(SessionServiceImpl.class);
	private static final List<String> fixedSessionIds = Arrays.asList("0123456789abcdef0123456789abcdef");
	
	@Autowired
	private SessionCacheExecutor sessionCacheExecutor;
	@Value("${session.maxage}")
	private Long sessionMaxAgeInMinute;

	/**
	 * @return the sessionMaxAgeInMinute
	 */
	private Long getSessionMaxAgeInMinute() {
		return sessionMaxAgeInMinute;
	}
	
	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ISessionService#createSession(java.lang.String, java.lang.String)
	 */
	@Override
	public String createSession(String userId, String deviceId) throws Exception {
		SessionDto session = new SessionDto();
		session.setSessionId(UUID.randomUUID().toString().replace("-", ""));
		session.setUserId(userId);
		session.setDeviceId(deviceId);
		session.setCreateTime(new Date());
		session.setExpireTime(computeExpireTime(session.getCreateTime(), getSessionMaxAgeInMinute()));
		
		sessionCacheExecutor.add(session);
		
		return session.getSessionId();
	}

	/**
	 * @param createTime
	 * @param sessionMaxAgeInMinute2
	 * @return
	 */
	private Date computeExpireTime(Date createTime, Long maxAgeInMinute) {		
		return new Date(createTime.getTime() + maxAgeInMinute * 60 * 1000);
	}

	/* (non-Javadoc)
	 * @see com.autonavi.tsp.workbackend.service.ISessionService#validateSession(java.lang.String)
	 */
	@Override
	public void validateSession(String sessionId) throws ValidationException {
		if(sessionId == null || sessionId.length() == 0){
			throw new ValidationException(ExceptionCodeEnum.PARAM_MUST_INPUT, "sessionId");
		}
		if(fixedSessionIds.contains(sessionId)){
			//特殊sessionId不需要校验、直接通过
			return;
		}
		
		SessionDto session;
		try {
			 session = sessionCacheExecutor.find(sessionId);			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ValidationException(ExceptionCodeEnum.UNKNOW_EXCEPTION);
		}
		if(session == null){
			throw new ValidationException(ExceptionCodeEnum.SESSION_NOT_EXIST);
		}
		if(session.getExpireTime() != null 
				&& session.getExpireTime().compareTo(new Date()) < 0){
			//remove from cache...
			try {
				sessionCacheExecutor.remove(sessionId);
			} catch (Exception e) {
				log.warn("error when delete expired session from cache:" + e.getMessage());
			}
			
			throw new ValidationException(ExceptionCodeEnum.SESSION_EXPIRED);
		}
				
	}

}
