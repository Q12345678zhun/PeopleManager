package org.fkit.hrm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.common.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthorizedInterceptor implements HandlerInterceptor{

	/*
	 * ���岻��Ҫ���ص�����
	 */
	private static final String[] INGORE_URL= {"/loginForm","/login","/404.html"};
	/*
	 *�÷�����ҪpreHandle�����ķ���ֵΪTRUEʱ�Ż�ִ��
	 *�÷�����������������ɺ�ִ�У���Ҫ����������������Դ
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*
	 *�÷�����ҪpreHandle�����ķ���ֵΪTRUEʱ�Ż�ִ��
	 *ִ��ʱ�����ڴ��������д���֮��Ҳ����controller�ķ�������֮��ִ��
	 */
	

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*
	*prehandle�����ǽ��д����������õģ��÷�������Controller����֮ǰ���е���
	*��prehandle�ķ���ֵΪfalse��ʱ����������ͽ�����
	*��prehandle�ķ���ֵtrue��ʱ�򣬽�����ִ��preHandle��afterCompletion
	*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handle) throws Exception {
		// TODO Auto-generated method stub
		/*Ĭ���û�û�е�¼*/
		boolean flag=false;
		/* ��������servletPath*/
		String servletPath=request.getServletPath();
		for(String s:INGORE_URL) {
			if(servletPath.contains(s)) {
				flag=true;
				break;
			}
		}
		/*
		 * ��������
		 */
		if(!flag) {
			User user=(User) request.getSession().getAttribute("user");
			if(user==null) {
				request.setAttribute("message", "���ȵ�¼�ڷ�����վ");
				request.getRequestDispatcher("loginForm").forward(request, response);
				return flag;
			}else {
				flag=true;
			}
					
		}
		return flag;
	}

	
}
