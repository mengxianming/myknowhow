package my.study.spstudy;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="my.study.spstudy",
excludeFilters={@Filter(type=FilterType.ANNOTATION, value=Controller.class)})
@EnableAspectJAutoProxy
@EnableTransactionManagement//不需要指定txManager、自动检查
public class RootContextConfig implements ApplicationContextAware{
	
	private ApplicationContext appCtx;
	
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//	    PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//	    ClassPathResource resource = new ClassPathResource("/META-INF/spring/database.properties");
//	    placeholderConfigurer.setLocation(resource);
//	    return placeholderConfigurer;
//	}

	@Bean
	public DataSource dataSource(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	    EmbeddedDatabase db = builder
	      .setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
	      .addScript("db/sql/schema.sql")
	      .build();
	    return db;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		DataSourceTransactionManager txm = new DataSourceTransactionManager(dataSource());
		return txm;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sb = new SqlSessionFactoryBean();
		sb.setDataSource(dataSource());
		sb.setMapperLocations(appCtx.getResources("classpath:/my/study/spstudy/dao/resource/*Mapper.xml"));		
		
		return sb.getObject();
	}	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appCtx = applicationContext;		
	}

}
