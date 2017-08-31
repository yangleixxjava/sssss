package com.yl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.sun.tools.internal.ws.processor.model.Model;
import com.yl.entity.UserEntity;
import com.yl.service.UserService;
import com.yl.utils.PageBean;

@Controller
@RequestMapping("/user")
public class UserController {
	
  @Autowired
  
	private UserService userService;
/***
 * 
 * ��ʼ��ҳ��
 * @return
 */
	@RequestMapping("/init")
	public String test() {

		return "list";
	}

	@RequestMapping("/findUserById")
	public UserEntity findUserById() {

		UserEntity findOne = userService.findOne(UserEntity.class, 1);

		return findOne;
	}

	@RequestMapping("/getUserInfo")
	@ResponseBody
	@SuppressWarnings("unchecked")
	
	public Map<String, Object>  getUserInfo(HttpServletRequest  request,ModelMap map){
		
		PageBean bean = new PageBean(request);

		Map<String, Object> par = new HashMap<String, Object>();
		// ��ȡҳ���ѯ����
		String userName = WebUtils.findParameterValue(request, "uname");

		par.put("uname", userName);

		// ��ѯ���

		Map<String, Object> userListAndCount = userService.getUserListAndCount(
				par, bean);
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) userListAndCount
				.get("rows");
		long count = (Long) userListAndCount.get(PageBean.TOTAL);

		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("pager.pageNo", 1);
		resultData.put("pager.pageSize", 20);
		resultData.put("pager.totalRows", count);
		resultData.put("rows", list);
		return resultData;
		
		
		
		
	}
   
	
	/**
	 * 
	 * �鿴�û�
	 */
	@RequestMapping("/toShowUser")
	public String toShowUser(HttpServletRequest request,ModelMap map, String uid){
		
		Map<String, Object> userInfo = new HashMap<String, Object>();

		// ��ȡ�û���Ϣ
		if (uid != null && StringUtils.isNotBlank(uid)) {
			Map<String, Object> pars = new HashMap<String, Object>();
			pars.put("uid", uid);
			List<Map<String, Object>> list = userService
					.getUserList(pars, 0, 0);
		
			userInfo = list.get(0);

			Object json = JSONObject.toJSON(userInfo);
			// map.addAttribute("user", json);

			map.put("user", json);
		}

		return "showuser";
	}
	
	/**
	 * 
	 * �����û�idɾ���û�
	 */
	
	
	@ResponseBody
	@RequestMapping("/delUser")
	public Map<String, Object> delUser(HttpServletRequest request, ModelMap map, String ids) {
		Map<String, Object> result = new HashMap<String, Object>();

		Boolean delete = userService.delete(UserEntity.class, ids);

		if (delete) {

			result.put("status", 1);

		} else {

			result.put("status", 0);
		}

		return result;
	}
	
	/**
	 * �޸��û�
	 */
	@RequestMapping("/toModifyUser")
	public String toUpdUser(HttpServletRequest request, ModelMap map, String uid) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();

		// ��ȡ�û���Ϣ
		if (uid != null && StringUtils.isNotBlank(uid)) {
			Map<String, Object> pars = new HashMap<String, Object>();
			pars.put("uid", uid);
			List<Map<String, Object>> list = userService
					.getUserList(pars, 0, 0);
			
			userInfo = list.get(0);

			Object json = JSONObject.toJSON(userInfo);
			// map.addAttribute("user", json);

			map.put("user", json);
		}

		return "modifyUser";
	}
	
	/**
	 * �޸�����
	 */
	
	@ResponseBody
	@RequestMapping("/doAlterUserPwd")
	public Map<String, Object> doAlterUserPwd(HttpServletRequest request, ModelMap map, String uid, String inputPwd,
 String newPwd) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = "�����ɹ���";
		if (!StringUtils.isNotBlank(newPwd)) {
			msg = "�����벻��Ϊ�գ�";
			result.put("msg", msg);
			return result;
		}
	
		if (StringUtils.isNotBlank(uid)) {

			UserEntity user = userService.findOne(uid);
			
			user.setPassword(newPwd);
			
			userService.update(user);
			msg = "�ɹ�";
			result.put("msg", msg);
			return result;
		} else {

			result.put("msg", "ʧ��");
			return result;
		}

		
	}
	
	/**
	 * ���޸�����ҳ������
	 * 
	 * 
	 */
	@RequestMapping("/toAlterUserPwd")
	public String toAlterUserPwd(HttpServletRequest request, ModelMap map, String path, String uid) {
		
		
		 map.put("uid", uid);
		
		return "alertUserPwd";
	}

	@ResponseBody
	@RequestMapping("/doAddUser")
	public Map<String, Object> doAddUser(HttpServletRequest request, ModelMap map, UserEntity user) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "�����ɹ���");
		return result;
	}
	
	@RequestMapping("/toAddNewUser")
	public String doAddNewUser(){
		
		
		return "addUser";
	}
	/**
	 * ��֤�û��Ƿ����
	 * @param validateValue
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validLoginId")
	public Map<String, Object> validLoginId(String validateValue) {

		// ���ݿ��Ѵ�������������ѯ
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("uaccount", validateValue);
		long count = userService.getUserListCount(pars);

		// ����ж�
		Map<String, Object> result = new HashMap<String, Object>();
		if (count > 0) {
			result.put("valid", false);
		} else {
			result.put("valid", true);
		}

		// ����ָ����ʽ���
		Map<String, Object> resultJson = new HashMap<String, Object>();
		resultJson.put("validateResult", result);
		return resultJson;
	}
	/**
	 * ����û�
	 */
	
	@ResponseBody
	@RequestMapping("/addNewUser")
	public Map<String, Object> addNewUser(HttpServletRequest request, ModelMap map) {
		
		    String name = request.getParameter("uname");
		    String password = request.getParameter("upassword");
		    String sex = request.getParameter("usex");
		    if(sex.equals("0")){
		    	
		    	sex="man";
		    	
		    }
		    else{
		    
		    	sex="woman";
		    
		    }
		    String phone = request.getParameter("uphone");
		    String account = request.getParameter("uaccount");
	
		 UserEntity entity=new UserEntity();
		 entity.setAccount(account);
		 entity.setName(name);
		 entity.setPassword(password);
		 entity.setPhone(phone);
		 entity.setSex(sex);
		
	    UserEntity save = userService.save(entity);
		Map<String, Object> result = new HashMap<String, Object>();
	    if(save!=null){
		
		result.put("msg", "�����ɹ���");
		return result;
	}else{
		
		
		result.put("msg", "����ʧ��");
		return result;
	}
	
	
	}
	/**
	 * ɾ��������û�
	 * @param request
	 * @param map
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delUsers")
	public Map<String, Object> delUsers(HttpServletRequest request, ModelMap map) {
		Map<String, Object> result = new HashMap<String, Object>();
		String ids = request.getParameter("ids");
		userService.deleteSplitUsers(ids);
		result.put("status", 1);
			
		return result;
	}
}
