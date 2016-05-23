package my.study.spstudy.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import my.study.spstudy.MvcTestBase;

public class TestControllerTest extends MvcTestBase{
	
	@Test
	public void testBindData() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/binddata").param("doubleData", "11.3333")
				.param("decimalData", "22.3333"))
		.andDo(MockMvcResultHandlers.print());
		
	}
}