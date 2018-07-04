package com.forte.auto.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.forte.auto.common.ExcelProcess;
import com.forte.auto.entity.ForteInterface;
import com.forte.auto.entity.ForteRunner;
import com.forte.auto.service.InterfaceService;

@Controller
public class InterfaceController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	InterfaceService service;
	
	@RequestMapping("/index")
	public String visitIndex(){
		return "pages/index";
	}
	
	@RequestMapping("/showInterfaceList")
	public String showInterfacePage(Model model){
		List<ForteInterface> interfaceList = service.getAll();
		model.addAttribute("interfaceList", interfaceList);
		return "pages/interfaceList";
	}
	
	@RequestMapping("/showAddInterface")
	public String showAddInterface(){
		return "pages/addInterface";
	}
	
	@RequestMapping(value="/addInterface",method=RequestMethod.POST)
	public String addInterface(@ModelAttribute("myForm") ForteInterface forteInterface, BindingResult result, Model model){
		String f_name = forteInterface.getF_name();
		String f_desc = forteInterface.getF_desc();
		String f_url = forteInterface.getF_url();
		String f_method = forteInterface.getF_method();
		String f_parameters = forteInterface.getF_parameters();
		
		ForteInterface bean = new ForteInterface();
		bean.setF_name(f_name);
		bean.setF_desc(f_desc);
		bean.setF_url(f_url);
		bean.setF_method(f_method);
		bean.setF_parameters(f_parameters);
	
		Date now = new Date();
		bean.setF_createtime(now);
		service.addInterface(bean);
		
		List<ForteInterface> interfaceList = service.getAll();
		model.addAttribute("interfaceList", interfaceList);
		return "pages/interfaceList";
	}
	
	@RequestMapping("/showInterfaceDetail")
	public String showInterfaceDetail(@RequestParam int idf_interface , Model model){
		ForteInterface bean = (ForteInterface)service.getById(idf_interface);
		
		List<String> paramList = new ArrayList<String>();
		String f_parameters = bean.getF_parameters();
		if(f_parameters == null || "".equals(f_parameters)){
			paramList.add("没有参数");
		}else{
			if("".equals(f_parameters) != true && f_parameters.split("\\|").length == 1){
				paramList.add(f_parameters);
			}else{
				String[] strs = f_parameters.split("\\|");
				paramList = Arrays.asList(strs);
			}
		}
		model.addAttribute("paramList", paramList);
		model.addAttribute("bean", bean);
		return "pages/interfaceDetail";
	}
	
	@RequestMapping("/runInterface")
	public String runInterface(@ModelAttribute("detailForm") ForteInterface forteInterface, BindingResult result, Model model , HttpServletRequest request){
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		String f_parameters = "";
		String f_url = forteInterface.getF_url();
		String[] paramListKey = request.getParameterValues("paramListKey");
		String[] paramListValue = request.getParameterValues("paramListValue");
		StringBuffer sb = new StringBuffer();
		if(paramListKey == null){
			map = null;
			f_parameters = "";
		}else if (paramListKey.length == 1 && "没有参数".equals(paramListKey[0])) {
			map = null;
			f_parameters  = "";
		}else{
			for(int i=0;i<paramListKey.length;i++){
				map.add(paramListKey[i], paramListValue[i]);
				sb.append(paramListKey[i]).append("=").append(paramListValue[i]).append("|");
			}
			f_parameters = sb.toString();
		}
		
		String runResult = service.sendRequest(f_url,map);
		
		ForteRunner runner = new ForteRunner();
		runner.setF_interface_id(forteInterface.getIdf_interface());
		runner.setF_parameters(f_parameters);
		if(runResult.length() >= 500)
			runResult = runResult.substring(0,500);
		runner.setF_response(runResult);
		Date now = new Date();
		runner.setF_createtime(now);
		String f_patch = "patch" + now.getTime();
		runner.setF_patch(f_patch);
		service.addRunResult(runner);
		
		List<ForteRunner> resultPatch = service.getRunResultByPatch(f_patch);
		
		model.addAttribute("forteInterface", forteInterface);
		model.addAttribute("resultPatch", resultPatch);
		return "pages/runResult";
	}
	
	@RequestMapping("/download")
	public String download(@RequestParam int idf_interface , HttpServletRequest request , HttpServletResponse response){
		ForteInterface jiekou = service.getById(idf_interface);
		String f_name = jiekou.getF_name();
		String f_desc = jiekou.getF_desc();
		String f_parameters = jiekou.getF_parameters();
		if(f_parameters == null || "".equals(f_parameters)){
			f_parameters = "";
		}
		
		String fileName = "" + new Date().getTime();
		String downLoadPath = "e:\\" + fileName + ".xls";
		ExcelProcess excel = new ExcelProcess();
		excel.createExcelModule(downLoadPath , f_parameters , f_name , f_desc);
		response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + "module.xls");
        try {
            InputStream inputStream = new FileInputStream(downLoadPath);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
	
	@RequestMapping(value = "upload.html", method = RequestMethod.POST)
	public String uploadAndRunPatch(HttpServletRequest request, HttpServletResponse response , Model model){

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String realFileName = file.getOriginalFilename();
//		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator;
//		File dirPath = new File(ctxPath);
//		if (!dirPath.exists()) {
//			dirPath.mkdir();
//		}
		File uploadFile = new File("e:\\" + realFileName);
		try {
			FileCopyUtils.copy(file.getBytes(), uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int idf_interface = Integer.parseInt(request.getParameter("idf_interface"));
		ForteInterface forteInterface = service.getById(idf_interface);
		String f_url = forteInterface.getF_url();
		String[] paramListKey = forteInterface.getF_parameters().split("\\|");
		
		if (paramListKey.length == 1 && ("".equals(paramListKey[0]) || paramListKey[0] == null)) {
			paramListKey[0] = "";
		}
		int length = paramListKey.length;
		ExcelProcess excel = new ExcelProcess();
		String fullPath = "e:\\" + realFileName;
		int rowCount = excel.getRowCount(fullPath);
		Date now = new Date();
		String f_patch = "patch" + now .getTime();
		for(int i=1;i<=rowCount-1;i++){
//			String f_name = excel.readCellValue(fullPath , i , 0);
//			String f_desc = excel.readCellValue(fullPath, i, 1);
			
			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			String f_parameters = "";
			List<String> list = new ArrayList<String>();    
			for(int j=2;j<2+length;j++){
				String value = excel.readCellValue(fullPath, i, j);
				list.add(value);
			}
			String[] paramListValue = list.toArray(new String[list.size()]);    
			StringBuffer sb = new StringBuffer();
			if(paramListKey == null){
				map = null;
				f_parameters = "";
			}else if (paramListKey.length == 1 && "没有参数".equals(paramListKey[0])) {
				map = null;
				f_parameters  = "";
			}else{
				for(int temp=0;temp<paramListKey.length;temp++){
					map.add(paramListKey[temp], paramListValue[temp]);
					sb.append(paramListKey[temp]).append("=").append(paramListValue[temp]).append("|");
				}
				f_parameters = sb.toString();
			}
			
			String runResult = service.sendRequest(f_url,map);
			
			ForteRunner runner = new ForteRunner();
			runner.setF_interface_id(idf_interface);
			runner.setF_parameters(f_parameters);
			if(runResult.length() >= 500){
				runResult = runResult.substring(0,500);
			}
			runner.setF_response(runResult);
			runner.setF_createtime(now);
			runner.setF_patch(f_patch);
			service.addRunResult(runner);
		}
		List<ForteRunner> resultPatch = service.getRunResultByPatch(f_patch);
		model.addAttribute("forteInterface", forteInterface);
		model.addAttribute("resultPatch", resultPatch);
		
		return "pages/runResult";
	}
	
	@RequestMapping("/showUpdatePage")
	public String showUpdatePage(Model model , @RequestParam int idf_interface){
		ForteInterface bean = (ForteInterface)service.getById(idf_interface);
		model.addAttribute("bean", bean);
		return "pages/updateInterface";
	}
	
	@RequestMapping("/updateInterface")
	public String updateInterface(Model model , @ModelAttribute("updateForm") ForteInterface forteInterface){
		int idf_interface = forteInterface.getIdf_interface();
		String f_name = forteInterface.getF_name();
		String f_desc = forteInterface.getF_desc();
		String f_url = forteInterface.getF_url();
		String f_method = forteInterface.getF_method();
		String f_parameters = forteInterface.getF_parameters();
		
		ForteInterface bean = new ForteInterface();
		bean.setIdf_interface(idf_interface);
		bean.setF_name(f_name);
		bean.setF_desc(f_desc);
		bean.setF_url(f_url);
		bean.setF_method(f_method);
		bean.setF_parameters(f_parameters);
	
		Date now = new Date();
		bean.setF_updatetime(now);
		service.updateInterface(bean);
		
		List<ForteInterface> interfaceList = service.getAll();
		model.addAttribute("interfaceList", interfaceList);
		return "pages/interfaceList";
	}
	
	@RequestMapping("/deleteInterface")
	public String deleteInterface(Model model , @RequestParam int idf_interface){
		service.deleteInterface(idf_interface);
		List<ForteInterface> interfaceList = service.getAll();
		model.addAttribute("interfaceList", interfaceList);
		return "pages/interfaceList";
	}
	
}
