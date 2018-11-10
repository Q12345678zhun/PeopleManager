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
	 * flag=1��ʾ��ת���ϴ�ҳ��
	 * flag=2��ʾִ���ϴ�����
	 * ���ҳ��
	 */
	@RequestMapping(value="/document/addDocument")
	public ModelAndView addDocument(String flag,
			@ModelAttribute Document document,
			ModelAndView mv,
			HttpSession session)throws Exception{
		
		if(flag.equals("1")) {
			mv.setViewName("document/showAddDocument");
		}else {
			//�ϴ��ļ�·��
			String path=session.getServletContext().getRealPath("/upload/");
			//�ϴ��ļ���
			String fileName=document.getFile().getOriginalFilename();
			File filePath=new File(path,fileName);
			//�ж�·���Ƿ���ڣ���������ھʹ���һ��
			if(!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			//���ϴ��ļ����浽һ��Ŀ���ļ���
			document.getFile().transferTo(new File(path+File.separator+fileName));
			
			//�������ݿ�
			//����filename
			document.setFilename(fileName);
			//���ù�����user����
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
	 * ɾ���ĵ�
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
     * �����޸��ĵ�
     * flag=1��ʾ��ת���޸�ҳ�棬
     * flag=2��ʾִ���޸ĵĲ���
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
     * �����ĵ���������
     */
    @RequestMapping(value="/document/download")
    public ResponseEntity<byte[]> download(Integer id,HttpSession session) throws IOException{
    	//����id��ѯ�ĵ�
    	Document target=hrmService.findDocumentById(id);
    	String fileName=target.getFilename();
    	//�ϴ��ļ�·��
    	String path=session.getServletContext().getRealPath("/upload/");
    	//���Ҫ���ص�file����
    	File file=new File(path+File.separator+fileName);
    	//����springframework��httpheaders����
    	HttpHeaders headers=new HttpHeaders();
    	//������ʾ���ļ�������������������������
    	String downloadFileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
    	//ͨ�������һattachment���صķ�ʽ��ͼƬ
    	headers.setContentDispositionFormData("attachment", downloadFileName);
    	//�����������ݣ����������ļ�����
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
    	
    }
    
	

}
