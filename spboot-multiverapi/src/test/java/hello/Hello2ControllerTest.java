package hello;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class Hello2ControllerTest {

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		//mvc = MockMvcBuilders.standaloneSetup(new Hello2Controller()).build();
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getHelloV1() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/hello/").accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				;
	}
	
	@Test
	public void getHelloV2() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v2/hello/").accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				;
	}
}
