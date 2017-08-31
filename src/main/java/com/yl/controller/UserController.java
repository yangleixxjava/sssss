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
 * 初始化页面
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
		// 获取页面查询参数
		String userName = WebUtils.findParameterValue(request, "uname");

		par.put("uname", userName);

		// 查询结果

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
	 * 查看用户
	 */
	@RequestMapping("/toShowUser")
	public String toShowUser(HttpServletRequest request,ModelMap map, String uid){
		
		Map<String, Object> userInfo = new HashMap<String, Object>();

		// 获取用户信息
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
	 * 根据用户id删除用户
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
	 * 修改用户
	 */
	@RequestMapping("/toModifyUser")
	public String toUpdUser(HttpServletRequest request, ModelMap map, String uid) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();

		// 获取用户信息
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
	 * 修改密码
	 */
	
	@ResponseBody
	@RequestMapping("/doAlterUserPwd")
	public Map<String, Object> doAlterUserPwd(HttpServletRequest request, ModelMap map, String uid, String inputPwd,
 String newPwd) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = "操作成功！";
		if (!StringUtils.isNotBlank(newPwd)) {
			msg = "新密码不能为空！";
			result.put("msg", msg);
			return result;
		}
	
		if (StringUtils.isNotBlank(uid)) {

			UserEntity user = userService.findOne(uid);
			
			user.setPassword(newPwd);
			
			userService.update(user);
			msg = "成功";
			result.put("msg", msg);
			return result;
		} else {

			result.put("msg", "失败");
			return result;
		}

		
	}
	
	/**
	 * 打开修改密码页面内容
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
		result.put("msg", "操作成功！");
		return result;
	}
	
	@RequestMapping("/toAddNewUser")
	public String doAddNewUser(){
		
		
		return "addUser";
	}
	/**
	 * 验证用户是否存在
	 * @param validateValue
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validLoginId")
	public Map<String, Object> validLoginId(String validateValue) {

		// 数据库已存在数据条数查询
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("uaccount", validateValue);
		long count = userService.getUserListCount(pars);

		// 结果判断
		Map<String, Object> result = new HashMap<String, Object>();
		if (count > 0) {
			result.put("valid", false);
		} else {
			result.put("valid", true);
		}

		// 返回指定格式结果
		Map<String, Object> resultJson = new HashMap<String, Object>();
		resultJson.put("validateResult", result);
		return resultJson;
	}
	/**
	 * 添加用户
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
		
		result.put("msg", "操作成功！");
		return result;
	}else{
		
		
		result.put("msg", "操作失败");
		return result;
	}
	
	
	}
	/**
	 * 删除出多个用户
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
