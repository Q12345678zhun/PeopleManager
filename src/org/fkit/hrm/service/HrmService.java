package org.fkit.hrm.service;

import java.util.List;

import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.tag.PageModel;

public interface HrmService {
	/*
	 * 用户登录
	 */
	User  login(String loginname,String password);
	/*
	 * 根据id查询用户
	 */
	User findUserById(int id);
	/*
	 * 获得所有的用户
	 */
	List<User> findUser(User user,PageModel pageModel);
	/*
	 * 根据id删除用户
	 */
	void removeUserById(int id);
	/*
	 * 修改用户
	 */
	void modify(User user);
	/*
	 * 添加用户
	 */
	void addUser(User user);
	
	/*
	 * 获得所有的员工
	 */
	List<Employee>findEmployee(Employee employee,PageModel pageModel);
	/*
	 * 根据id删除员工
	 */
	void removeEmployeeById(int id);
	/*
	 * 根据id查询员工
	 */
	Employee findEmployeeById(int id);
	/*
	 *添加员工
	 */
	void addEmloyee(Employee employee);
	void modifyEmployee(Employee employee);
	
	List<Dept> findDept(Dept dept,PageModel pageModel);
	List<Dept> findAllDept();
	void removeDeptById(int id);
	void addDept(Dept dept);
	Dept findDeptById(int id);
	void modifyDept(Dept dept);
	
	
	List<Job> findAllJob();
	List<Job> findJob(Job job,PageModel pageModel);
	public void removeById(int id);
	void addJob(Job job);
	Job FindJobById(int id);
	void modifyJob(Job job);
	
	List<Notice> findNotice(Notice notice,PageModel pageModel);
	Notice findNoticeById(int id);
	public void removeNoticeById(int id);
	void addNotice(Notice notice);
	void modifyNotice(Notice notice);
	
	List<Document>findDocument(Document document,PageModel pageModel);
	void addDocument(Document document);
	Document findDocumentById(int id);
	public void removeDocumentById(int id);
	void modifyDocument(Document document);
	

}
