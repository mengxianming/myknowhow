package my.study.sprintbootabc.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import my.study.sprintbootabc.dao.qbc.Condition;
import my.study.sprintbootabc.dao.qbc.QCriteria;
import my.study.sprintbootabc.model.Renter;
import my.study.sprintbootabc.util.ComUtils;


public class RenterMapperTest extends BaseMapperTest<Renter>{
    @Autowired
    private RenterMapper mapper;
    
	@Override
	protected BaseMapper<Renter> getBaseMapper() {
		return mapper;
	}

	@Override
	protected void modifyEntity(Renter rec) {
		rec.setName("test name");		
	}
	
	@Test
	public void testSelectByMap() throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("names", Arrays.asList("meng", "xian"));
		params.put("phone", ComUtils.keyValues2Map("phoneNum","1234"));
		params.put("age", 12);
		mapper.selectByMap(params);
	}
	
	@Test
	public void testSelectByCriteria() throws Exception {
		QCriteria crt = QCriteria.createFor(Renter.class);
		crt.and(Condition.eq("name", "meng"));
		mapper.selectByCriteria(crt);
	}
}