/**
 * 
 */
package my.study.spstudy;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import my.study.spstudy.RootContextConfig;
import my.study.spstudy.WebContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration(value = "src/main/webapp")  
@ContextHierarchy({  
	@ContextConfiguration(name = "parent", classes=RootContextConfig.class),  
	@ContextConfiguration(name = "child", classes=WebContextConfig.class)  
})  
public abstract class MvcTestBase {  

	@Autowired  
	protected WebApplicationContext wac;  
	protected MockMvc mockMvc;  

	@Before  
	public void setUp() {  
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
	}  
}  