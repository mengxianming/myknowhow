package my.study.spstudy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class BookstoreWebApplicationInitializer implements WebApplicationInitializer { 

	@Override 
	public void onStartup(final ServletContext servletContext) throws ServletException { 
		registerDispatcherServlet(servletContext); 
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebContextConfig.class);

		ServletRegistration.Dynamic dispatcher =
				servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	} 

}