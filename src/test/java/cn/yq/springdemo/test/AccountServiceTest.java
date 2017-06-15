package cn.yq.springdemo.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yq.springmvc.entity.Account;
import cn.yq.springmvc.service.AccountService;

public class AccountServiceTest extends BaseTest {
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testCreate(){
		String account="admin";
		Account acc=accountService.create(account);
		assertNotNull(acc);
		assertNotNull(acc.getId());
		
	}
	@Test
	public void testValidate(){
		String account="admin";
		Account acc=accountService.validate(account, "1122");
		assertNull(acc);
		acc=accountService.validate(account, "123456");
		
		assertNotNull(acc);
		
	}

}
