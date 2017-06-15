package cn.yq.springmvc.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.yq.springmvc.entity.Account;
import cn.yq.springmvc.service.AccountService;
/**
 * 
 * @author zzz
 *
 */
@Controller
public class SessionController extends BaseController {
	
	public static final String CURRENT_ACCOUNT="currentAccount";
	
	@Autowired
	private AccountService accountServcie;
	/**
	 * 进入登录页面
	 * @return
	 */
	@RequestMapping(value="/signIn",method=RequestMethod.GET)
	public String signIn(){
		return "signIn";
	}
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/signOut",method=RequestMethod.GET)
	public String signOut(HttpSession session){
		
		//让session失效
		session.invalidate();
		return "redirect:/signIn";
	}
	
	/**
	 * 提交账号信息
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/signIn",method=RequestMethod.POST)
	public String signIn(HttpSession session,
			String account,String password,
			Model model){
		Account acc = accountServcie.validate(account, password);
		if(acc!=null){
			session.setAttribute(CURRENT_ACCOUNT, acc);
			accountServcie.updateLastSignInDateTime(acc.getAccount());
			return "redirect:hello";
		}else{
			System.out.println("----------------");
			model.addAttribute("signInError", "用户名或密码错误");
			return "signIn";
		}
	}
	

}
