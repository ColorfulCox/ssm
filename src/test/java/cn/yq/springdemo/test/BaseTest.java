package cn.yq.springdemo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BaseTest {
	protected Log log=LogFactory.getLog(this.getClass());
	
}
