package my.study.spstudy;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

//XML风格  
@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration(value = "src/main/webapp")  
@ContextConfiguration(name = "parent", classes=RootContextConfig.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public abstract class TxTestBase extends AbstractTransactionalJUnit4SpringContextTests{  

	
} 