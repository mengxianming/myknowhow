package my.study.spstudy.listjoiner;

import java.util.Collections;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class JoinExpression{	
	String expr;
	

	private static ScriptEngine engine;
	static{
		ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");
	}

	public JoinExpression(String expr){
		if(expr == null || expr.length() == 0){
			throw new IllegalArgumentException("expr不能为空");
		}
		this.expr = expr;		
	}
	
	public String getExpr() {
		return expr;
	}
		
	public boolean eval(Map<String, Object> context){		
		if(context == null){
			context = Collections.emptyMap();
		}
		
		Object val;
		try {
			SimpleBindings binding = new SimpleBindings(context);
			val = engine.eval(expr, binding);
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
		if(val instanceof Boolean){
			return (Boolean)val;
		}else{
			throw new IllegalArgumentException("表达式必须为关系表达式. expr='" + expr + "'");
		}
	}
	
}
