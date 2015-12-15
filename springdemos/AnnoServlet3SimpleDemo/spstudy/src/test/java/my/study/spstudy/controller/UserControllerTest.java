package my.study.spstudy.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import my.study.spstudy.MvcTestBase;

public class UserControllerTest extends MvcTestBase{

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().string("Hello"));
	}

}
