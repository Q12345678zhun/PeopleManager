package org.fkit.hrm.controller;

import java.util.List;

import org.fkit.hrm.domain.Job;
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
public class JobController {
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value="/job/selectJob")
	public String selectJob(Model model,Integer pageIndex,@ModelAttribute Job job) {
		PageModel pageModel=new PageModel();
		if(pageIndex!=null) {
			pageModel.SetPageIndex(pageIndex);
		}
		List<Job>jobs =hrmService.findJob(job, pageModel);
		model.addAttribute("jobs",jobs);
		model.addAttribute("pageModel",pageModel);
		return "job/job";
		
	}
	
	@RequestMapping(value="/job/removeJob")
	public ModelAndView removeJob(String ids,ModelAndView mv) {
		String []idArray=ids.split(",");
		for(String id:idArray) {
			hrmService.removeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/job/selectJob");
		return mv;
		
	}
	

}
