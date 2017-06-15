package cn.yq.springmvc.service.impl;

import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yq.springmvc.dao.AccountMapper;
import cn.yq.springmvc.entity.Account;
import cn.yq.springmvc.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	//默认初始密码
	private String defaultPassword="123456";
	/**
	 * 创建账号，密码使用系统默认的密码，在 配置文件中进行配置
	 * @param account
	 * @return
	 */
	@Override
	public Account create(String account) {
		//产生随机的盐值 16个字符的随机数    字母与数字混合
		String salt =RandomStringUtils.random(16, true, true);
		//密文
		String passwordDigest=crypt(defaultPassword,salt);
		Account acc=new Account();
		acc.setAccount(account);
		acc.setPasswordDigest(passwordDigest);
		acc.setSalt(salt);
		accountMapper.insertSelective(acc);
		return acc;
	}
	/**
	 * 验证用户名和密码
	 * @param account
	 * @param password 
	 * @return
	 */
	@Override
	public Account validate(String account, String password) {
		Account acc=accountMapper.selectByAccount(account);
		if(acc==null){ //找不到账号，返回null
			return acc;
		}
		//将从数据库拿到的盐值和输入的密码进行加密
		String passwordDigest=crypt(password,acc.getSalt());
		
		//正确的密码
		String realPasswordDigest=acc.getPasswordDigest();
		
		//正确
		if(passwordDigest.equals(realPasswordDigest)){
			return acc;
		}
		
		return null;
	}
	/**
	 * 加密方法
	 * @param salt
	 * @param defaultPassword
	 * @return
	 */
	private String crypt(String salt,String defaultPassword ){
		//先对原始密码加密
		String passwordCrypt = DigestUtils.md5Hex(defaultPassword+salt);
		//进行倒叙
		return StringUtils.reverse(passwordCrypt);
		
	}
	/**
	 * 记录最后一次修改时间
	 */
	@Override
	public void updateLastSignInDateTime(String account) {
		Account acc=accountMapper.selectByAccount(account);
		if(acc!=null){
			acc.setLastSignin(new Date());
			accountMapper.updateByPrimaryKeySelective(acc);
		}
	}
	
}
