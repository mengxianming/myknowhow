package my.study.spstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.study.spstudy.service.IUserService;

@Configuration
public class RootContextConfig {
	@Bean
	public IUserService userService(){
		return new UserServiceImpl();
	}

}
