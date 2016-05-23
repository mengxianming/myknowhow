package my.study.spstudy.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.study.spstudy.util.TestUtil;

@Controller
public class TestController extends BaseController{
	
	@RequestMapping("/test/binddata")
	@ResponseBody
	public Object testBindData(Double doubleData, BigDecimal decimalData){
		return TestUtil.toMap("doubleData", doubleData, "decimalData", decimalData);
		
	}
}