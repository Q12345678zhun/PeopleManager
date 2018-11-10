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
	 * 自动注入userService
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
			//将用户保存到httpsession中
			session.setAttribute("user",user);
		//	System.out.println(user);
			//客户端跳转到main页面
			mv.setViewName("redirect:/main");
			//mv.setView(new RedirectView("/PeopleManager/main"));
		}else {
			//mv.addObject("message","登录名或密码错误，请重新输入");
			//服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
			return mv;
		
	}
	/*
	 * 处理查询请求
	 */
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex,@ModelAttribute User user,Model model) {
		PageModel pageModel=new PageModel();
		if(pageIndex !=null) {
			pageModel.SetPageIndex(pageIndex);
		}
		///查询用户信息
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
		//设置客户端跳转到查询条件
		mv.setViewName("redirect:/user/selectUser");
		
		return mv;
		
	}
	/*
	 * @param String flag标记，1表示跳转到修改的页面，2表示执行修改操作
	 * @param User user 要修改的用户对象
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
	 * 执行添加操作
	 * @param String flag标记，1表示跳转到添加页面，2表示执行添加操作
	 * @param User user要添加的用户类型
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
