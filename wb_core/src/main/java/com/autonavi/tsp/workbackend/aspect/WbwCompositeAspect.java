package com.autonavi.tsp.workbackend.aspect;



import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.autonavi.tsp.common.dto.BucSSOUserDto;
import com.autonavi.tsp.common.dto.OppLogDto;

@Component
@Aspect
public class WbwCompositeAspect {

	 private static Log log = LogFactory.getLog(WbwCompositeAspect.class);
	@Pointcut("execution(* com.autonavi.tsp.workbackend.service.*.*.*(..))")
	public void pointCut() {
	}
	@Before("pointCut()")
	public void logging(JoinPoint joinPoint) {
		try {
			log.info("---------start wb_web logging --------------------");
			Object[] args = joinPoint.getArgs();

			Logger logger = Logger.getLogger(joinPoint.getTarget().getClass()
					.getName());
			String methodName = joinPoint.getSignature().getName();
			Class<? extends Object> tc = joinPoint.getTarget().getClass();
			String objectName = tc.getSimpleName();
			logger.info("method name--" + methodName);
			logger.info("object name--" + objectName);
			HttpServletRequest request = null;
			BucSSOUserDto user = null;
			logger.info("args length=" + args.length);
			Class<?>[] c = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				c[i] = args[i].getClass();
				log.info("method arg" + i + " -- " + args[i]);
				if (args[i] instanceof BucSSOUserDto) {
					user = (BucSSOUserDto) args[i];
					log.info("this is BucSSOUser");
				}

			}
			//得到request
			 Field field=ReflectionUtils.findField(tc, "request");
			 logger.info("field="+field);
			 if(field!=null)
			 {
				 Object obj=field.get(joinPoint.getTarget());
				 logger.info("obj="+obj);
				 request=(HttpServletRequest)obj;
			 }
			
			logger.info("user=" + user);
			if (user != null) {
				OppLogDto.setEmpId(user.getEmpId());
				OppLogDto.setLoginName(user.getLoginName());
				OppLogDto.setNickName(user.getNickName());
				OppLogDto.setUMID(user.getUmid());
				OppLogDto.setAccount(user.getAccount());
				OppLogDto.setLastName(user.getLastName());
				OppLogDto.setOpItem(objectName);
				OppLogDto.setOpItemID(methodName);
				log.info(user.getAccount());
			}
			if(request!=null)
			{
				String sessionId = request.getSession().getId();
				OppLogDto.setSessionId(sessionId);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

