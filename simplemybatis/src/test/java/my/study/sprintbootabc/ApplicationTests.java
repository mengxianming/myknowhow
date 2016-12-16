package my.study.sprintbootabc;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import my.study.sprintbootabc.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void test() throws Exception {
    	jdbcTemplate.update("insert into Renter(id, name, phone) values(1,'meng','1234')");
    	
    	Map<String, Object> renter = jdbcTemplate.queryForMap("select * from renter");
    	System.out.println(renter);
    }

}