package org.fkit.hrm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@RequestMapping(value="/document/selectDocument")
	public String selectDocument(Model model,
			@ModelAttribute Document document,
			Integer pageIndex) {
		PageModel pageModel=new PageModel();
		if(pageIndex!=null) {
			pageModel.SetPageIndex(pageIndex);
		}
		List<Document>documents=hrmService.findDocument(document, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel",pageModel);
		
		return "document/document";
		
	}
	/*
	 * flag=1表示跳转到上传页面
	 * flag=2表示执行上传操作
	 * 添加页面
	 */
	@RequestMapping(value="/document/addDocument")
	public ModelAndView addDocument(String flag,
			@ModelAttribute Document document,
			ModelAndView mv,
			HttpSession session)throws Exception{
		
		if(flag.equals("1")) {
			mv.setViewName("document/showAddDocument");
		}else {
			//上传文件路径
			String path=session.getServletContext().getRealPath("/upload/");
			//上传文件名
			String fileName=document.getFile().getOriginalFilename();
			File filePath=new File(path,fileName);
			//判断路径是否存在，如果不存在就创建一个
			if(!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			//将上传文件保存到一个目标文件中
			document.getFile().transferTo(new File(path+File.separator+fileName));
			
			//插入数据库
			//设置filename
			document.setFilename(fileName);
			//设置关联的user对象
			User user=(User) session.getAttribute("user");
			document.setUser(user);
			hrmService.addDocument(document);
			//System.out.println(document);
			mv.setViewName("redirect:/document/selectDocument");
			//mv.setView(new Redirect());
			
		}
		return mv;
		
	}
	/*
	 * 删除文档
	 */
    @RequestMapping(value="/document/removeDocument")
	public ModelAndView removeDocument(String ids,ModelAndView mv) {
    	String []idArray=ids.split(",");
    	for(String id:idArray) {
    		hrmService.removeDocumentById(Integer.parseInt(id));
    		
    	}
    	mv.setViewName("redirect:/document/selectDocument");
		return mv;
    	
		
	}
    
    /*
     * 处理修改文档
     * flag=1表示跳转到修改页面，
     * flag=2表示执行修改的操作
     */
    @RequestMapping(value="/document/updateDocument")
    public ModelAndView updateDocument(String flag,@ModelAttribute Document document,ModelAndView mv) {
    	if(flag.equals("1")) {
    		Document target=hrmService.findDocumentById(document.getId());
    		mv.addObject("document",target);
    		mv.setViewName("document/showUpdateDocument");
    		
    	}else {
    		hrmService.modifyDocument(document);
    		mv.setViewName("redirect:/document/selectDocument");
    	}
		return mv;
    	
    }
    /*
     * 处理文档下载请求
     */
    @RequestMapping(value="/document/download")
    public ResponseEntity<byte[]> download(Integer id,HttpSession session) throws IOException{
    	//根据id查询文档
    	Document target=hrmService.findDocumentById(id);
    	String fileName=target.getFilename();
    	//上传文件路径
    	String path=session.getServletContext().getRealPath("/upload/");
    	//获得要下载的file对象
    	File file=new File(path+File.separator+fileName);
    	//创建springframework的httpheaders对象
    	HttpHeaders headers=new HttpHeaders();
    	//下载显示的文件名，解决中文名称乱码的问题
    	String downloadFileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
    	//通过浏览器一attachment下载的方式打开图片
    	headers.setContentDispositionFormData("attachment", downloadFileName);
    	//二进制流数据，做常见的文件下载
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
    	
    }
    
	

}
