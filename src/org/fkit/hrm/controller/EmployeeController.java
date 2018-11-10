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
		//ģ����ѯʱ�ж��Ƿ��й������󴫵ݣ�����У���������װ��������
		this.genericAssociation(job_id,dept_id,employee);
		//������ҳ����
		PageModel pageModel=new PageModel();
		//�������pageIndex��Ϊnull������pageindex������ʾ�ڼ�ҳ
		if(pageIndex!=null) {
			pageModel.SetPageIndex(pageIndex);
		}
		//��ѯְλ��Ϣ������ģ����ѯ
		List<Job> jobs=hrmService.findAllJob();
		//��ѯ������Ϣ������ģ����ѯ
		List<Dept> depts=hrmService.findAllDept();
		//��ѯԱ����Ϣ
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
			//�鿴ְλ��Ϣ
			List<Job>jobs=hrmService.findAllJob();
			List<Dept>depts=hrmService.findAllDept();
			mv.addObject("jobs",jobs);
			mv.addObject("depts",depts);
			mv.setViewName("employee/showAddEmployee");
		}else {
			//�ж��Ƿ��й������󴫵ݣ�����У�������������
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
