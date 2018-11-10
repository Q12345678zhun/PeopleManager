package org.fkit.hrm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.common.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthorizedInterceptor implements HandlerInterceptor{

	/*
	 * 定义不需要拦截的请求
	 */
	private static final String[] INGORE_URL= {"/loginForm","/login","/404.html"};
	/*
	 *该方法需要preHandle方法的返回值为TRUE时才会执行
	 *该方法将在整个请求完成后执行，主要作用是用于清理资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*
	 *该方法需要preHandle方法的返回值为TRUE时才会执行
	 *执行时间是在处理器进行处理之后，也就是controller的方法调用之后执行
	 */
	

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*
	*prehandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用
	*当prehandle的返回值为false的时候整个请求就结束了
	*当prehandle的返回值true的时候，将继续执行preHandle和afterCompletion
	*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handle) throws Exception {
		// TODO Auto-generated method stub
		/*默认用户没有登录*/
		boolean flag=false;
		/* 获得请求的servletPath*/
		String servletPath=request.getServletPath();
		for(String s:INGORE_URL) {
			if(servletPath.contains(s)) {
				flag=true;
				break;
			}
		}
		/*
		 * 拦截请求
		 */
		if(!flag) {
			User user=(User) request.getSession().getAttribute("user");
			if(user==null) {
				request.setAttribute("message", "请先登录在访问网站");
				request.getRequestDispatcher("loginForm").forward(request, response);
				return flag;
			}else {
				flag=true;
			}
					
		}
		return flag;
	}

	
}
