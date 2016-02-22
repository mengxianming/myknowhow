package my.study.spstudy.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import my.study.spstudy.util.JsonUtil;
import my.study.spstudy.util.TestUtil;


/**
 * 异常处理类，主要作用是将所有抛出的异常，进行统一拦截
 * 并写入日志中。
 */
@Controller
public class CommonExceptionResolver  implements HandlerExceptionResolver {

	public static final Logger logger = Logger.getLogger(CommonExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
			logger.error(String.format("由统一异常拦截处理器捕获的异常：%s, \n请求参数:%s", ex, JsonUtil.toJsonExcludeNull(request.getParameterMap())));
			
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/pages/error/error");
						
			modelAndView.addObject("ec", TestUtil.toMap("code", 1, "msg", ex.getMessage()));			
			return modelAndView;		
	}
			
}
