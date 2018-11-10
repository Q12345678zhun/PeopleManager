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
	 * �û���¼
	 */
	User  login(String loginname,String password);
	/*
	 * ����id��ѯ�û�
	 */
	User findUserById(int id);
	/*
	 * ������е��û�
	 */
	List<User> findUser(User user,PageModel pageModel);
	/*
	 * ����idɾ���û�
	 */
	void removeUserById(int id);
	/*
	 * �޸��û�
	 */
	void modify(User user);
	/*
	 * ����û�
	 */
	void addUser(User user);
	
	/*
	 * ������е�Ա��
	 */
	List<Employee>findEmployee(Employee employee,PageModel pageModel);
	/*
	 * ����idɾ��Ա��
	 */
	void removeEmployeeById(int id);
	/*
	 * ����id��ѯԱ��
	 */
	Employee findEmployeeById(int id);
	/*
	 *���Ա��
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
