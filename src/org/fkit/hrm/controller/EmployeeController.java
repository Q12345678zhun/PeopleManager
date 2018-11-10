package org.fkit.hrm.controller;

import java.util.List;

import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value="/employee/selectEmployee")
	public String selectEmployee(Integer pageIndex,Integer job_id,Integer dept_id,
			@ModelAttribute Employee employee,Model model) {
		//模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
		this.genericAssociation(job_id,dept_id,employee);
		//创建分页对象
		PageModel pageModel=new PageModel();
		//如果参数pageIndex不为null，设置pageindex，即显示第几页
		if(pageIndex!=null) {
			pageModel.SetPageIndex(pageIndex);
		}
		//查询职位信息，用于模糊查询
		List<Job> jobs=hrmService.findAllJob();
		//查询部门信息，用于模糊查询
		List<Dept> depts=hrmService.findAllDept();
		//查询员工信息
		List<Employee> employees=hrmService.findEmployee(employee, pageModel);
		model.addAttribute("employees",employees);
		model.addAttribute("jobs",jobs);
		model.addAttribute("depts",depts);
		model.addAttribute("pageModel",pageModel);
		
				return "employee/employee";
		
		
	}
	@RequestMapping(value="/employee/addEmployee")
	public ModelAndView addEmployee(String flag,
			Integer job_id,Integer dept_id,
			@ModelAttribute Employee employee,
			ModelAndView mv) {
		if(flag.equals("1")) {
			//查看职位信息
			List<Job>jobs=hrmService.findAllJob();
			List<Dept>depts=hrmService.findAllDept();
			mv.addObject("jobs",jobs);
			mv.addObject("depts",depts);
			mv.setViewName("employee/showAddEmployee");
		}else {
			//判断是否有关联对象传递，如果有，创建关联对象
			this.genericAssociation(job_id,dept_id,employee);
			hrmService.addEmloyee(employee);
			mv.setViewName("redirect:/employee/selectEmployee");
		}
				return mv;
		
	}
	@RequestMapping(value="/employee/removeEmployee")
	public ModelAndView removeEmployee(String ids,ModelAndView mv) {
		String []idArray=ids.split(",");
		for(String id:idArray) {
			hrmService.removeEmployeeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/employee/selectEmployee");
		return mv;
	}
	@RequestMapping(value="/employee/updateEmployee")
	public ModelAndView updateEmployee(String flag,
			ModelAndView mv,Integer dept_id,Integer job_id,@ModelAttribute Employee employee) {
		if(flag.equals("1")) {
		List<Job> jobs=hrmService.findAllJob();
		List<Dept>depts=hrmService.findAllDept();
		Employee target=hrmService.findEmployeeById(employee.getId());
		mv.addObject("jobs",jobs);
		mv.addObject("depts",depts);
		mv.addObject("employee",target);
	    mv.setViewName("employee/showUpdateEmployee");
		}else{
			this.genericAssociation(job_id,dept_id,employee);
			System.out.println(employee);
			hrmService.modifyEmployee(employee);
			mv.setViewName("redirect:/employee/selectEmployee");
			
		}
		return mv;
	}
	
	


	private void genericAssociation(Integer job_id, Integer dept_id, Employee employee) {
		// TODO Auto-generated method stub
		if(job_id!=null) {
			Job job=new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		if(dept_id!=null) {
			Dept dept=new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
		
	}

}
