package my.study.spstudy;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class BookstoreWebApplicationInitializer implements WebApplicationInitializer { 

	@Override 
	public void onStartup(final ServletContext servletContext) throws ServletException { 
		CharacterEncodingFilter defEncoding = new CharacterEncodingFilter();
		defEncoding.setEncoding("utf8");
		defEncoding.setForceEncoding(true);		
		servletContext.addFilter("encodingFilter", defEncoding)
		.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");;
		
		
		registerDispatcherServlet(servletContext); 
	}

	private void registerDispatcherServlet(ServletContext container) {
		AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
		rootCtx.register(RootContextConfig.class);

		// Manage the lifecycle of the root application context
	      container.addListener(new ContextLoaderListener(rootCtx));


	      
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebContextConfig.class);

		DispatcherServlet dpSvlt = new DispatcherServlet(ctx);
//		dpSvlt.setContextInitializerClasses(contextInitializerClasses);
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", dpSvlt);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	} 

}