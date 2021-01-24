package shoonye.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations={"classpath:test-dbmvc-ctx.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTest {

	@Test
	@Rollback(false)
	@Transactional(readOnly=false)
	public void test(){
		//DO Nothing, test app context loading	
	}
}
