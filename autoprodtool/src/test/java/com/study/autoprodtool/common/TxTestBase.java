package com.study.autoprodtool.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Descriptions
 * test base class for transaction operations
 * @version 2013-12-12
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/conf/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/conf/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
@ActiveProfiles("test")
public abstract class TxTestBase extends AbstractTransactionalJUnit4SpringContextTests{
	
	
}
