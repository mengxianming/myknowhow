package my.study.sprintbootabc.vo;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class User{
	public static class ErrorCode implements Payload{
		String code;
		String msg;
	}

	interface ErrorCodes{
		ErrorCode ec1 = new ErrorCode();
	}
	
	@Target({ METHOD, FIELD})
	@Retention(RUNTIME)
	@Documented
	public @interface EC {
		String code();
	}




	
	private String name;	
	private Integer age=0;
	private Date birth;

	@NotBlank
	@Size(min=1, max=10, message="必须>={min}, <={max}")
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Min(value=18, message="{mxm.validmesg.User.age.min}")
	@Max(value=30, message="{propertyPath}不能大于{value}, inval={invalidValue}")
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	@Past()
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public static void main(String[] args){
		Validator validator =  getValidator();
		User obj = new User();

		Set<ConstraintViolation<User>> vrs = validator.validate(obj, Default.class);
		System.out.println(vrs);
		
		obj.setAge(40);
		obj.setName("mengmengmeng");
		obj.setBirth(new Date(System.currentTimeMillis() + 100000));
		vrs = validator.validate(obj, Default.class);
		System.out.println(vrs);
		for(ConstraintViolation<User> cv : vrs){
			System.out.println(cv.getPropertyPath() + cv.getMessage());
		}
		
		
		//testParser();

	}
	
	private static void testParser() {
		Map<String, String> ctx = new HashMap<String, String>();
		ctx.put("name", "Meng");
		ctx.put("me.age", "12");
		ctx.put("me.birth", "9912");
		System.out.println(renderString("{name}, Again {name}, Your Age is {age}, and {me.birth} is great", ctx));
		
	}

	private static ExpressionParser parser = new SpelExpressionParser();
	private static String parseSpEL(String message, Map<String, Object> params) {
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		if(params != null){
			ctx.setVariables(params);
		}
		
		TemplateParserContext tplCtx = new TemplateParserContext("{", "}");		
		Expression exp = parser.parseExpression(message, tplCtx);
		return exp.getValue(ctx, String.class);
		
	}
	
	/**
    * 根据键值对填充字符串，如("hello ${name}",{name:"xiaoming"})
    * 输出：
    * @param content
    * @param map
    * @return
    */
   public static String renderString(String content, Map<String, String> map){
//       Set<Entry<String, String>> sets = map.entrySet();
//       for(Entry<String, String> entry : sets) {
//           String regex = "\\$\\{" + entry.getKey() + "\\}";
//           Pattern pattern = Pattern.compile(regex);
//           Matcher matcher = pattern.matcher(content);
//           content = matcher.replaceAll(entry.getValue());
//       }
	   
	   StringBuilder result = new StringBuilder();
	   String regex = "\\{([_a-zA-z][_a-zA-Z0-9\\.]*)\\}";
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(content);
       int lastStart = 0;
       for(; matcher.find(); lastStart = matcher.end()){
    	   String paraName = matcher.group(1);
    	   String paramVal = matcher.group();
    	   if(map.containsKey(paraName)){
    		   paramVal = map.get(paraName);
    	   }
    	   
    	  result.append(content.substring(lastStart, matcher.start())).append(paramVal == null ? "" : paramVal);
       }
       
       result.append(content.substring(lastStart));
       
       return result.toString();
   }
	
	private static Validator getValidator() {
		HibernateValidatorConfiguration config = Validation.byProvider( HibernateValidator.class ).configure();
		//config.messageInterpolator(new MyMessageInterpolator());

		ValidatorFactory factory = config.buildValidatorFactory();
		return factory.getValidator();
	}
	
	

}


