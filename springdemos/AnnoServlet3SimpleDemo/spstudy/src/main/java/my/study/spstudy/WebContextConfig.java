package my.study.spstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.study.spstudy.controller.UserController;

@Configuration
public class WebContextConfig {
	
	@Bean
	public UserController userController(){
		return new UserController();
	}

}
