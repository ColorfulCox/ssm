package cn.yq.springmvc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.yq.springmvc.entity.Account;
import cn.yq.springmvc.web.controller.SessionController;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
	/**
	 * 鉴别用户是否登录，如果登录则放行 ，也就是说返回 true，调用流程就会进入 Controller中的处理器方法
	 * 返回false，则表示 流程结束，不调用Controller处理器方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		Account account=(Account)session.getAttribute(SessionController.CURRENT_ACCOUNT);
		if(account==null){
			//没有登录，跳转到登录
			response.sendRedirect(request.getContextPath()+"/signIn");
			return false;
		}
		return true;
	}

}
