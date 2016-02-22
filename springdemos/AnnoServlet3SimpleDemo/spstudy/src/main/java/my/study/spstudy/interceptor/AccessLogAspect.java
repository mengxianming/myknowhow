package my.study.spstudy.interceptor;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: AccessLog
 * @Description: 主要记录方法调用关系日志
 * @author charlesfeng (90167)
 * @date 2015年6月16日10:25:27
 */
@Aspect
@Component
public class AccessLogAspect {
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
	private ObjectMapper mapper = new ObjectMapper();
	private Log logger = LogFactory.getLog(AccessLogAspect.class);
	
	public AccessLogAspect() {
		mapper.setSerializationInclusion(Include.NON_NULL);
	}
	
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
		if (isIncludParameterLog()) {
			write(getLog(jp), getParameterLevel(), String.format("%s: Begin \nParameters: %s", getMethodName(jp), buildParameterString(jp)));
		}else{
			write(getLog(jp), getBeforeLevel(), String.format("%s: Begin", getMethodName(jp)));
		}
	}
	
	private Log getLog(JoinPoint jp) {
		return logger;
	}

	/**
	 * 切片方法，在目标方法调用结束后被调用
	 * 输出内容：目标方法（带类名）结束
	 * @param jp
	 */
	public void doAfter(JoinPoint jp) {
		write(getLog(jp), getAfterLevel(), String.format("%s: End", getMethodName(jp)));
	}
	
	/**
	 * 切 片方法，包含在目标方法周围被调用
	 * 输出内容：输出目标方法的调用时间
	 * @param jp JoinPoint
	 * @return 目标方法调用的返回值
	 * @throws Throwable
	 */
	@Around(value = "execution(public * my.study.spstudy..*.*(..)) ")
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		doBefore(jp);
		
        long time = System.currentTimeMillis();  
        Object retVal = jp.proceed();  
        time = System.currentTimeMillis() - time;  
        write(getLog(jp), getAroundLevel(), String.format("%s End: expend %d milliseconds, \nreturn: %s", getMethodName(jp), time, retVal));
        
        return retVal;  
	}
	
	/**
	 * 切片方法，当目标方法抛出异常时被调用
	 * 输出内容：输出异常类及异常信息
	 * @param jp
	 * @param ex
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) {
		write(getLog(jp), getThrowingLevel(), String.format("%s: throw Exception(%s)", getMethodName(jp), ex.getClass().getName()), ex);
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
			parametersString = mapper.writeValueAsString(parameterMap);
		}
		catch (Throwable t) {			
			parametersString = String.valueOf(parameterMap);
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
			Method method = getMethod(jp.getTarget().getClass(), methodSignature, jp.getArgs());
			if (method != null) {
				parameterNames = parameterNameDiscoverer.getParameterNames(method);
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
	private void write(Log log, LogLevel level, Object msg) {
		write(log, level, msg, null);
	}
	
	/*
	 * 输出日志（带异常）
	 */
	private void write(Log log, LogLevel level, Object msg, Throwable err) {
		Method method;
		Object[] params;
		if(err == null){
			method = ReflectionUtils.findMethod(log.getClass(), level.name().toLowerCase(), Object.class);
			params = new Object[]{msg};
		}else{
			method = ReflectionUtils.findMethod(log.getClass(), level.name().toLowerCase(), Object.class, Throwable.class);
			params = new Object[]{msg, err};
		}
		ReflectionUtils.invokeMethod(method, log, params);
	
	}
}
