package com.study.autoprodtool.common;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 切片方法，用于在类的函数(before, parameter, after, around, throwing)中输出日志
 * 可单独指定每项日志的输出级别
 *
 * @version 2013-9-24
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class LogAspect {
	/* parameter name cache */
	private static Map<String, String[]> parameterNameCaches = new ConcurrentHashMap<String, String[]>();
	/* parameter name discoverer */
	private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer  = new LocalVariableTableParameterNameDiscoverer();
	
	public enum LogLevel {
		TRACE, DEBUG, INFO, WARN, ERROR, FATAL
	}
	
	/* write before information to which Level, default Level is DEBUG */
	private LogLevel beforeLevel = LogLevel.INFO;
	/* write parameter information? */
	private boolean includParameterLog = true;
	/* write parameter information to which Level, default Level is DEBUG */
	private LogLevel parameterLevel = LogLevel.DEBUG;
	/* write after information to which Level, default Level is DEBUG */
	private LogLevel afterLevel = LogLevel.INFO;
	/* write around information to which Level, default Level is DEBUG */
	private LogLevel aroundLevel = LogLevel.INFO;
	/* write exception information to which Level, default Level is ERROR */
	private LogLevel throwingLevel = LogLevel.ERROR;

	/**
	 * 取得Before切片方法中输出日志的级别
	 * @return 日志级别
	 */
	public LogLevel getBeforeLevel() {
		return beforeLevel;
	}

	/**
	 * 设置Before切片方法输出日志的级别
	 * @param beforeLevel 日志级别
	 */
	public void setBeforeLevel(LogLevel beforeLevel) {
		this.beforeLevel = beforeLevel;
	}

	/**
	 * 取得Before切片方法中是否输出参数日志
	 * @return 是否输出参数日志
	 */
	public boolean isIncludParameterLog() {
		return includParameterLog;
	}

	/**
	 * 设置Before切片方法中是否输出参数日志。此属性设置为false时，parameterLevel属性无效。
	 * @param includParameterLog 是否输出参数日志
	 */
	public void setIncludParameterLog(boolean includParameterLog) {
		this.includParameterLog = includParameterLog;
	}

	/**
	 * 取得Before切片方法中输出参数日志的级别
	 * @return 日志级别
	 */
	public LogLevel getParameterLevel() {
		return parameterLevel;
	}

	/**
	 * 设置Before切片方法中输出参数日志的级别
	 * @param parameterLevel 日志级别
	 */
	public void setParameterLevel(LogLevel parameterLevel) {
		this.parameterLevel = parameterLevel;
	}

	/**
	 * 取得After切片方法输出日志的级别
	 * @return 日志级别
	 */
	public LogLevel getAfterLevel() {
		return afterLevel;
	}

	/**
	 * 设置After切片方法输出日志的级别
	 * @param afterLevel 日志级别
	 */
	public void setAfterLevel(LogLevel afterLevel) {
		this.afterLevel = afterLevel;
	}

	/**
	 * 取得Around切片方法输出日志的级别
	 * @return 日志级别
	 */
	public LogLevel getAroundLevel() {
		return aroundLevel;
	}

	/**
	 * 设置Around切片方法输出日志的级别
	 * @param aroundLevel 日志级别
	 */
	public void setAroundLevel(LogLevel aroundLevel) {
		this.aroundLevel = aroundLevel;
	}

	/**
	 * 取得Throwing切片方法输出日志的级别
	 * @return 日志级别
	 */
	public LogLevel getThrowingLevel() {
		return throwingLevel;
	}

	/**
	 * 设置Throwing切片方法输出日志的级别
	 * @param throwingLevel 日志级别
	 */
	public void setThrowingLevel(LogLevel throwingLevel) {
		this.throwingLevel = throwingLevel;
	}

	/**
	 * 切片方法，在目标方法调用开始被调用
	 * 输出内容：目标方法名（带类名）开始和调用参数
	 * @param jp JoinPoint
	 */
	public void doBefore(JoinPoint jp) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		write(log, getBeforeLevel(), String.format("%s: Begin", getMethodName(jp)));
		if (isIncludParameterLog()) {
			write(log, getParameterLevel(), String.format("Parameters: %s", buildParameterString(jp)));
		}
	}
	
	/**
	 * 切片方法，在目标方法调用结束后被调用
	 * 输出内容：目标方法（带类名）结束
	 * @param jp
	 */
	public void doAfter(JoinPoint jp) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		write(log, getAfterLevel(), String.format("%s: End", getMethodName(jp)));
	}
	
	/**
	 * 切 片方法，包含在目标方法周围被调用
	 * 输出内容：输出目标方法的调用时间
	 * @param jp JoinPoint
	 * @return 目标方法调用的返回值
	 * @throws Throwable
	 */
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        long time = System.currentTimeMillis();  
        Object retVal = jp.proceed();  
        time = System.currentTimeMillis() - time;  
        Log log = LogFactory.getLog(jp.getTarget().getClass());
        write(log, getAroundLevel(), String.format("%1$s: return: %2$s", getMethodName(jp), retVal));
        write(log, getAroundLevel(), String.format("%s: expend %d milliseconds", getMethodName(jp), time));
        return retVal;  
	}
	
	/**
	 * 切片方法，当目标方法抛出异常时被调用
	 * 输出内容：输出异常类及异常信息
	 * @param jp
	 * @param ex
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) {
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		write(log, getThrowingLevel(), String.format("%s: throw Exception(%s)", getMethodName(jp), ex.getClass().getName()), ex);
	}
	
	/*
	 * 取得带类名的方法名
	 */
	private String getMethodName(JoinPoint jp) {
		return jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
	}
	
	/*
	 * build参数字符串
	 */
	private String buildParameterString(JoinPoint jp) {
		Object[] parameterValues = jp.getArgs();
		String parametersString = null;
		MethodSignature methodSignature = (MethodSignature)jp.getSignature();	
		Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();
		String[] parameterNames = getParameterNames(jp);
		Map<String, Object> parameterMap = new LinkedHashMap<String, Object>();
		String unnamedParameterFormat = "arg%d";
		if (parameterNames != null) {
			assert (parameterNames.length == parameterValues.length);
			for (int i = 0; i < parameterValues.length; i++) {
				parameterMap.put(parameterNames[i], parameterValues[i]);
			}
		}
		else {
			for (int i = 0; i < parameterValues.length; i++) {
				parameterMap.put(String.format(unnamedParameterFormat, i), parameterValues[i]);
			}
		}
		
		try {
			// 使用jackson序列化参数
			ObjectMapper mapper = new ObjectMapper();
			parametersString = mapper.writeValueAsString(parameterMap);
		}
		catch (Throwable t) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < parameterValues.length; i++) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append("\"");
				if (parameterNames != null && parameterNames[i] != null) {
					sb.append(parameterNames[i]);
				}
				else {
					sb.append(String.format(unnamedParameterFormat, i));
				}
				sb.append("\":");
				if (parameterTypes[i].isPrimitive()) {
					sb.append(parameterValues[i]);
				} else {
					sb.append("\"");
					sb.append(parameterValues[i]);
					sb.append("\"");
				}
			}
			sb.append("]");
			parametersString = sb.toString();
		}
		return parametersString;
	}
	
	/*
	 * 取得参数名称
	 */
	private String[] getParameterNames(JoinPoint jp) {
		MethodSignature methodSignature = (MethodSignature)jp.getSignature();
		String[] parameterNames = methodSignature.getParameterNames();
		if (parameterNames == null) {
			String methodLongName = jp.getTarget().getClass().toString() + " - " + methodSignature.toLongString();
			// 根据长签名在缓存中查找
			parameterNames = parameterNameCaches.get(methodLongName);
			if (parameterNames == null) {
				Method method = getMethod(jp.getTarget().getClass(), methodSignature, jp.getArgs());
				if (method != null) {
					parameterNames = parameterNameDiscoverer.getParameterNames(method);
					if (parameterNames != null) {
						// 缓存
						parameterNameCaches.put(methodLongName, parameterNames);
					}
				}
			}
		}
		return parameterNames;
	}

	/*
	 * 在指定的类中寻找指定签名的方法
	 * 判断依据：返回值相同，参数个数相同，参数类型及顺序相同
	 * 返回指定方法的Method对象，若未找到，返回null
	 */
	private Method getMethod(Class<?> clazz, MethodSignature methodSignature, Object[] args) {
		Method method = null;
		Class<?>[] parameterTypes = methodSignature.getParameterTypes();
		try {
			method = clazz.getMethod(methodSignature.getName(), parameterTypes);
			if (method.isBridge()) {
				method = BridgeMethodResolver.findBridgedMethod(method);
			}
		} 
		catch(NoSuchMethodException e) {
			// if exists exception, ignore it
			method = null;
		}
		return method;
	}
	
	/*
	 * 输出日志
	 */
	private void write(Log log, LogLevel level, Object arg0) {
		switch (level) {
		case TRACE:
			log.trace(arg0);
			break;
		case DEBUG:
			log.debug(arg0);
			break;
		case INFO:
			log.info(arg0);
			break;
		case WARN:
			log.warn(arg0);
			break;
		case ERROR:
			log.error(arg0);
			break;
		case FATAL:
			log.fatal(arg0);
			break;
		}
	}
	
	/*
	 * 输出日志（带异常）
	 */
	private void write(Log log, LogLevel level, Object arg0, Throwable arg1) {
		switch (level) {
		case TRACE:
			log.trace(arg0, arg1);
			break;
		case DEBUG:
			log.debug(arg0, arg1);
			break;
		case INFO:
			log.info(arg0, arg1);
			break;
		case WARN:
			log.warn(arg0, arg1);
			break;
		case ERROR:
			log.error(arg0, arg1);
			break;
		case FATAL:
			log.fatal(arg0, arg1);
			break;
		}		
	}
}
