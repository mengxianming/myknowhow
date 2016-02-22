package my.study.spstudy.controller;

import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import my.study.spstudy.MvcTestBase;
import my.study.spstudy.dao.StudentMapper;
import my.study.spstudy.domain.Student;
import my.study.spstudy.util.TestUtil;

public class UserControllerTest extends MvcTestBase{
	
	@Autowired
	StudentMapper studentMapper;
	
	

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().string("Hello"));
		
	}
	
	@Test
	public void testFindUserList() throws Exception {
		Student record = TestUtil.createDefaultEntity(Student.class);
		studentMapper.insert(record);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/user/"))
		.andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().string(TestUtil.toJson(Arrays.asList(record))));
		
	}
	
	@Test
	public void testTestExceptoin() throws Exception {
//		Matcher<? super String> mt = CoreMatchers
		mockMvc.perform(MockMvcRequestBuilders.get("/user/ex").param("type", "1"))
		.andDo(MockMvcResultHandlers.print())
		.andDo(new ResultHandler(){

			@Override
			public void handle(MvcResult result) throws Exception {
				System.out.println("body:" + result.getResponse().getContentAsString());				
			}
			
		});
		
		
	}

}
