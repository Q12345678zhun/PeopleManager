package org.fkit.hrm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class UserController {
	
	/*
	 * �Զ�ע��userService
	 */
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			HttpSession session,ModelAndView mv) {
		User user=hrmService.login(loginname, password);
		if(user!=null) {
			//���û����浽httpsession��
			session.setAttribute("user",user);
		//	System.out.println(user);
			//�ͻ�����ת��mainҳ��
			mv.setViewName("redirect:/main");
			//mv.setView(new RedirectView("/PeopleManager/main"));
		}else {
			//mv.addObject("message","��¼���������������������");
			//�������ڲ���ת����¼ҳ��
			mv.setViewName("forward:/loginForm");
		}
			return mv;
		
	}
	/*
	 * �����ѯ����
	 */
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex,@ModelAttribute User user,Model model) {
		PageModel pageModel=new PageModel();
		if(pageIndex !=null) {
			pageModel.SetPageIndex(pageIndex);
		}
		///��ѯ�û���Ϣ
		List<User> users=hrmService.findUser(user, pageModel);
		model.addAttribute("users",users);
		model.addAttribute("pageModel",pageModel);
		return "user/user";
		
		
	}
	@RequestMapping(value="/user/removeUser")
	public ModelAndView removeUser(String ids,ModelAndView mv) {
		String []idArray=ids.split(",");
		for(String id:idArray) {
			hrmService.removeUserById(Integer.parseInt(id));
		}
		//���ÿͻ�����ת����ѯ����
		mv.setViewName("redirect:/user/selectUser");
		
		return mv;
		
	}
	/*
	 * @param String flag��ǣ�1��ʾ��ת���޸ĵ�ҳ�棬2��ʾִ���޸Ĳ���
	 * @param User user Ҫ�޸ĵ��û�����
	 * @param ModelAttribute mv
	 */
	@RequestMapping(value="/user/updateUser")
	public ModelAndView updateUser(String flag,
			@ModelAttribute User user,
			ModelAndView mv){
		if(flag.equals("1")) {
			User target=hrmService.findUserById(user.getId());
			//System.out.println(target);
			mv.addObject("user",target);
			mv.setViewName("/user/showUpdateUser");
		}else {
			
			hrmService.modify(user);
			//System.out.println(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
		
	}
	/*
	 * ִ����Ӳ���
	 * @param String flag��ǣ�1��ʾ��ת�����ҳ�棬2��ʾִ����Ӳ���
	 * @param User userҪ��ӵ��û�����
	 * @param modelAndView mv
	 */
	@RequestMapping(value="/user/addUser")
	public ModelAndView addUser(String flag,
			@ModelAttribute User user,
			ModelAndView mv) {
		if(flag.equals("1")) {
			
			mv.setViewName("user/showAddUser");
		}else {
			hrmService.addUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
		
	}
	
}
