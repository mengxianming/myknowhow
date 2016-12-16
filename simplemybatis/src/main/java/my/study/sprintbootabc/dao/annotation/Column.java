package my.study.sprintbootabc.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.ibatis.type.JdbcType;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
	String value() default "";	
	boolean isPK() default false;
	JdbcType jdbcType() default JdbcType.NULL;
}
