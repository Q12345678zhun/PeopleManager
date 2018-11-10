package org.fkit.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fkit.hrm.dao.DeptDao;
import org.fkit.hrm.dao.DocumentDao;
import org.fkit.hrm.dao.EmployeeDao;
import org.fkit.hrm.dao.JobDao;
import org.fkit.hrm.dao.NoticeDao;
import org.fkit.hrm.dao.UserDao;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/*
 * 人事管理服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
//将该类配置成一个spring的bean，标识符为hrmservice

@Service("hrmService")

public class HrmServiceImpl implements HrmService{
	
	
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private DocumentDao documentDao;

	/*
	 * 用户实现接口
	 * @see org.fkit.hrm.service.HrmService#login(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
		// TODO Auto-generated method stub
		return userDao.selectByIdLoginnameAndPassword(loginname, password);
	}

	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object>params=new HashMap<>();
		params.put("user", user);
		int recordCount=userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<User> users=userDao.selectByPage(params);
		return users;
	}
	@Transactional(readOnly=true)
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}


	@Override
	public void removeUserById(int id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
		
	}

	@Override
	public void modify(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
		
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		
	}
	/*
	 * 实现员工
	 */
			
    @Transactional(readOnly=true)
	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object>params=new HashMap<>();
		params.put("employee", employee);
		int recordCount=employeeDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<Employee> employees=employeeDao.selectByPage(params);
		return employees;
	}

	@Override
	public void removeEmployeeById(int id) {
		// TODO Auto-generated method stub
		employeeDao.deleteById(id);
		
	}
    @Transactional(readOnly=true)
	@Override
	public Employee findEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeDao.selectById(id);
	}

	@Override
	public void addEmloyee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);
		
	}

	@Override
	public void modifyEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.updatEmployee(employee);
		
	}
    /*
     * (non-Javadoc)部门
     * @see org.fkit.hrm.service.HrmService#findDept(org.fkit.hrm.domain.Dept, org.fkit.hrm.util.tag.PageModel)
     */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object>params=new HashMap<>();
		params.put("dept", dept);
		int recordCount=deptDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
			
		}
		List<Dept>depts=deptDao.selectByPage(params);
		return depts;
	}

	@Override
	public void removeDeptById(int id) {
		// TODO Auto-generated method stub
		deptDao.deleteByID(id);
		
	}

	@Override
	public void addDept(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.save(dept);
		
	}
    @Transactional(readOnly=true)
	@Override
	public Dept findDeptById(int id) {
		// TODO Auto-generated method stub
    	
		return deptDao.selectById(id);
	}

	@Override
	public void modifyDept(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.update(dept);
		
	}
	public List<Dept> findAllDept(){
		return deptDao.selectAllDept();
		
	}
	
	/*
	 * (non-Javadoc)工作
	 * @see org.fkit.hrm.service.HrmService#findAllJob()
	 */
    @Transactional(readOnly=true)
	@Override
	public List<Job> findAllJob() {
		// TODO Auto-generated method stub
		
		return jobDao.selectAllJob();
	}
    @Transactional(readOnly=true)
	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		// TODO Auto-generated method stub	
    	Map<String,Object>params=new HashMap<>();
    	params.put("job", job);
    	int recordCount=jobDao.count(params);
    	pageModel.setRecordCount(recordCount);
    	
    	if(recordCount>0) {
    		params.put("pageModel", pageModel);
    	}
    	List<Job> jobs=jobDao.selectByPage(params);
		return jobs;
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		jobDao.DeleteById(id);
		
	}

	@Override
	public void addJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.save(job);
		
	}
    @Transactional(readOnly=true)
	@Override
	public Job FindJobById(int id) {
		// TODO Auto-generated method stub
		return jobDao.selectById(id);
	}

	@Override
	public void modifyJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.update(job);
	}
	/*
	 * 公告
	 * @see org.fkit.hrm.service.HrmService#findNotice(org.fkit.hrm.domain.Notice, org.fkit.hrm.util.tag.PageModel)
	 */
    @Transactional(readOnly=true)
	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object>params=new HashMap<>();
		params.put("notice", notice);
		int recordCount=noticeDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<Notice>notices=noticeDao.selectByPage(params);
		return notices;
	}
   @Transactional(readOnly=true)
	@Override
	public Notice findNoticeById(int id) {
		// TODO Auto-generated method stub
	   
		return noticeDao.selectById(id);
	}

	@Override
	public void removeNoticeById(int id) {
		// TODO Auto-generated method stub
		noticeDao.deleteById(id);
		
	}

	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.save(notice);
		
	}

	@Override
	public void modifyNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.update(notice);
		
	}
	/*
	 * 下载文件
	 * @see org.fkit.hrm.service.HrmService#findDocument(org.fkit.hrm.domain.Document, org.fkit.hrm.util.tag.PageModel)
	 */
    @Transactional(readOnly=true)
	@Override
	public List<Document> findDocument(Document document, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object>params=new HashMap<>();
		params.put("document", document);
		int recordCount=documentDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			params.put("pageModel", pageModel);
		}
		List<Document>documents=documentDao.selectByPage(params);
		return documents;
	}

	@Override
	public void addDocument(Document document) {
		// TODO Auto-generated method stub
		documentDao.save(document);
		
	}
   @Transactional(readOnly=true)
	@Override
	public Document findDocumentById(int id) {
		// TODO Auto-generated method stub
		return documentDao.selectById(id);
	}

	@Override
	public void removeDocumentById(int id) {
		// TODO Auto-generated method stub
		documentDao.deleteById(id);
		
	}

	@Override
	public void modifyDocument(Document document) {
		// TODO Auto-generated method stub
		documentDao.update(document);
	}

	

}
