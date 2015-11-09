package com.autonavi.tsp.atsp.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-dao-test.xml")
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
public abstract class DaoTestBase extends AbstractTransactionalJUnit4SpringContextTests{  

	
}  
