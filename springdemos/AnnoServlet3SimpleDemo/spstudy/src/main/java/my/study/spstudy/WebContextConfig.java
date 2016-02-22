package my.study.spstudy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="my.study.spstudy.controller")
public class WebContextConfig extends WebMvcConfigurerAdapter{	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/*.jsp").setViewName("");
//		super.addViewControllers(registry);
//	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*.js").addResourceLocations("/js/");
		super.addResourceHandlers(registry);
	}
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setViewClass(JstlView.class);
		vr.setSuffix(".jsp");
		return vr;
		
	}

}
