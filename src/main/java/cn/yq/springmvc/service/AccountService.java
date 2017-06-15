package cn.yq.springmvc.service;

import cn.yq.springmvc.entity.Account;

public interface AccountService {

	
	/**
	 * 创建账号，密码使用系统默认的密码，在 配置文件中进行配置
	 * @param account
	 * @return
	 */
	public Account create(String account);
	
	/**
	 * 验证用户名和密码
	 * @param account
	 * @param password 
	 * @return
	 */
	public Account validate(String account,String password);
	
	/**
	 * 修改最后一次的登录时间
	 * @param account
	 */
	public void  updateLastSignInDateTime(String account);
	
}
