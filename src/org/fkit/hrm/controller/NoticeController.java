package org.fkit.hrm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value="/notice/selectNotice")
	public String selectNotice(Model model,Integer pageIndex,@ModelAttribute Notice notice) {
		PageModel pageModel=new PageModel();
		if(pageIndex!=null) {
			pageModel.SetPageIndex(pageIndex);
		}
		List<Notice>notices=hrmService.findNotice(notice, pageModel);
		model.addAttribute("notices",notices);
		model.addAttribute("pageModel",pageModel);
		
		return "notice/notice";
		
	}
	
	@RequestMapping(value="/notice/previewNotice")
	//处理添加请求，要显示的公告id
	public String previewNotice(Integer id,Model model) {
		Notice notice=hrmService.findNoticeById(id);
		model.addAttribute(notice);
		return "notice/previewNotice";
		
	}
	@RequestMapping(value="/notice/removeNotice")
	public ModelAndView removeNotice(String ids,ModelAndView mv) {
		String[] idArray=ids.split(",");
		for(String id:idArray) {
			hrmService.removeNoticeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/notice/selectNotice");
		return mv;
		
	}
	@RequestMapping(value="/notice/addNotice")
	public ModelAndView addNotice(String flag,
			@ModelAttribute Notice notice,
			ModelAndView mv,
			HttpSession session) {
		if(flag.equals("1")) {
			mv.setViewName("notice/showAddNotice");
		}else {
			User user=(User) session.getAttribute("user");
			notice.setUser(user);
			hrmService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
			
		}
		return mv;
		
	}
	@RequestMapping(value="/notice/updateNotice")
	public ModelAndView updateNotice(String flag,@ModelAttribute Notice notice,ModelAndView mv,HttpSession session) {
		if(flag.equals("1")) {
			Notice target=hrmService.findNoticeById(notice.getId());
			mv.addObject("notice",target);
			mv.setViewName("notice/showUpdateNotice");
		}else {
			hrmService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
		
	}

}
