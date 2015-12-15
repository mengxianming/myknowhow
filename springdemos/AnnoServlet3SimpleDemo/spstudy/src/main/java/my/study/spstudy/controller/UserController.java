package my.study.spstudy.controller;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController implements ApplicationContextAware{
	private ApplicationContext ctx;

	@RequestMapping("/")
	@ResponseBody
	public String index(){
		System.out.println(Arrays.asList(ctx.getBeanDefinitionNames()));
		System.out.println(Arrays.asList(ctx.getParent().getBeanDefinitionNames()));
		
		return "Hello";
				
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
		
	}

}
