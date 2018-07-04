package com.forte.auto.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.forte.auto.dao.InterfaceMapper;
import com.forte.auto.entity.ForteInterface;
import com.forte.auto.entity.ForteRunner;



@Service
public class InterfaceService {
	@Resource
	InterfaceMapper mapper;

	public List<ForteInterface> getAll() {
		List<ForteInterface> interfaceList = mapper.getAll();
		return interfaceList;
	}

	public void addInterface(ForteInterface bean) {
		mapper.addInterface(bean);
	}

	public ForteInterface getById(int id) {
		ForteInterface bean = mapper.getById(id);
		return bean;
	}
	

	public String sendRequest(String f_url, MultiValueMap<String, String> map) {
		String result = "";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		if(map==null){
			result = restTemplate.postForObject( f_url, "", String.class);
		}
	    result = restTemplate.postForObject( f_url, request, String.class);
		return result;
	}

	public void addRunResult(ForteRunner runner) {
		mapper.addRunResult(runner);
	}

	public List<ForteRunner> getRunResultByPatch(String f_patch) {
		return mapper.getRunResultByPatch(f_patch);
	}

	public void executeInterface(int idf_interface, String f_name, String f_url, String f_method, String f_desc,
			String[] keys, String[] values, String f_patch) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		String str = "";
		List f_parameters = new ArrayList();
		if (keys.length == 1 && "".equals(keys[0])) {
			httppost = new HttpPost(f_url);
			str = "无参数";
		} else {
			if(keys.length == 1){
				f_parameters.add(new BasicNameValuePair(keys[0], values[0]));
				StringBuffer sb = new StringBuffer();
				str = sb.append(new BasicNameValuePair(keys[0], values[0]).toString()).toString();
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(f_parameters, Consts.UTF_8);
				httppost = new HttpPost(f_url);
				httppost.setEntity(entity);
			}else{
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keys.length; i++) {
					f_parameters.add(new BasicNameValuePair(keys[i], values[i]));
					sb.append(new BasicNameValuePair(keys[i], values[i]).toString()).append("|");
				}
				str = sb.toString().substring(0,sb.length()-1);
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(f_parameters, Consts.UTF_8);
				httppost = new HttpPost(f_url);
				httppost.setEntity(entity);
			}
		}
		
		try {
			CloseableHttpResponse response = httpclient.execute(httppost);
			ForteRunner runner = new ForteRunner();
			runner.setF_interface_id(idf_interface);
			runner.setF_parameters(str);
			String resp = EntityUtils.toString(response.getEntity());
			if(resp.length() >= 500)
				resp = resp.substring(0,500);
			runner.setF_response(resp);
			Date now = new Date();
			runner.setF_createtime(now);
			runner.setF_patch(f_patch);
			mapper.addRunResult(runner);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateInterface(ForteInterface bean) {
		mapper.updateInterface(bean);
	}

	public void deleteInterface(int idf_interface) {
		mapper.deleteInterface(idf_interface);
	}

}
