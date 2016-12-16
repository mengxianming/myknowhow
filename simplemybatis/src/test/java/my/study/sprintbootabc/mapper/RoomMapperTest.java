package my.study.sprintbootabc.mapper;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import my.study.sprintbootabc.dao.qbc.Condition;
import my.study.sprintbootabc.dao.qbc.QCriteria;
import my.study.sprintbootabc.model.Room;

public class RoomMapperTest extends BaseMapperTest<Room>{

    @Autowired
    private RoomMapper mapper;

	@Override
	protected BaseMapper<Room> getBaseMapper() {
		return mapper;
	}


	@Override
	protected void modifyEntity(Room rec2) {
		rec2.setName("test id");		
	}
	
	@Test
	public void testSelectByCriteria() throws Exception {
		QCriteria crt = QCriteria.createFor(Room.class);
		crt.and(Condition.eq("createTime", new Date()));
		mapper.selectByCriteria(crt);
	}

}