package my.study.sprintbootabc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import my.study.sprintbootabc.dao.annotation.Mapper;

/**
 * MyBatis基础配置
 *
 * @author liuzh
 * @since 2015-12-19 10:11
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="my.study.sprintbootabc.mapper", annotationClass=Mapper.class, sqlSessionFactoryRef="sqlSessionFactory")
public class MyBatisConfig implements TransactionManagementConfigurer {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
			.addScript("db/schema.sql")
			.build();
		return db;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setTypeAliasesPackage("my.study.sprintbootabc.model");

		//分页插件
		//        PageHelper pageHelper = new PageHelper();
		//        Properties properties = new Properties();
		//        properties.setProperty("reasonable", "true");
		//        properties.setProperty("supportMethodsArguments", "true");
		//        properties.setProperty("returnPageInfo", "check");
		//        properties.setProperty("params", "count=countSql");
		//        pageHelper.setProperties(properties);
		//
		//        //添加插件
		//        bean.setPlugins(new Interceptor[]{pageHelper});

		//添加XML目录
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		try {
//			bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//			return bean.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
		
		return bean; 
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name="txManager")
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}