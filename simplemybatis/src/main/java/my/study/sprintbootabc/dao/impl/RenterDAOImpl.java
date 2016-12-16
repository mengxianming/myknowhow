package my.study.sprintbootabc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.study.sprintbootabc.dao.RenterDAO;
import my.study.sprintbootabc.mapper.BaseMapper;
import my.study.sprintbootabc.mapper.RenterMapper;
import my.study.sprintbootabc.model.Renter;

@Repository
public class RenterDAOImpl extends BaseDAOImpl<Renter> implements RenterDAO{
	@Autowired
	private RenterMapper baseMapper;

	@Override
	protected BaseMapper<Renter> getBaseMapper() {		
		return baseMapper;
	}
	
}
