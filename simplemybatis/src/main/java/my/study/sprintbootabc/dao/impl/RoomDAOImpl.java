package my.study.sprintbootabc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.study.sprintbootabc.dao.RoomDAO;
import my.study.sprintbootabc.mapper.BaseMapper;
import my.study.sprintbootabc.mapper.RoomMapper;
import my.study.sprintbootabc.model.Room;

@Repository
public class RoomDAOImpl extends BaseDAOImpl<Room> implements RoomDAO{
	@Autowired
	private RoomMapper baseMapper;

	@Override
	protected BaseMapper<Room> getBaseMapper() {		
		return baseMapper;
	}
	
}
