package com.yl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yl.dao.UserDao;
import com.yl.entity.UserEntity;
import com.yl.utils.PageBean;
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	
	private enum QueryType {
		LIST, COUNT
	}


	private static final Class<UserEntity> UserEntitye = null;

	
	@Autowired
	private UserDao userDao  ;
	/**
	 * 
	 * 获取用户的list集合
	 */
	

	@Autowired(required = false)
	@Qualifier("passwordEncoder")
	private Md5PasswordEncoder passwordEncoder;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserList(Map<String, Object> pars,
			int start, int length) {
		Object[] obj = this.getQuerySentence(pars, QueryType.LIST);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = userDao.queryListBySql(obj[0].toString(), start, length,
				(Map<String, Object>) obj[1]);

		return list;
	}
	@SuppressWarnings("unchecked")
	public long getUserListCount(Map<String, Object> pars) {
		Object[] obj = this.getQuerySentence(pars, QueryType.COUNT);
	
		long count = userDao.queryCountBySql(obj[0].toString(),
				(Map<String, Object>) obj[1]);
		return count;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserListAndCount(Map<String, Object> pars,
			PageBean pageBean) {
		
		Object[] querySentence = this.getQuerySentence(pars, QueryType.LIST);//查询总的数据条数
		Map<String,Object>  userListAndCount=new  HashMap<String, Object>();
		
		 userListAndCount=	userDao.queryPaginationListBySql(querySentence[0].toString(),(Map<String, Object>)querySentence[1] , pageBean);
		
			
		return  userListAndCount;
	}
	/**
	 * 
	 * 查询语句拼接
	 */
	
	private Object[] getQuerySentence(Map<String, Object> pars,
			QueryType type){
		
		StringBuffer  sql=new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		
		switch (type) {
		case LIST:
			sql.append("select * ");
			break;
		case COUNT:
			
			sql.append("select count(1)");
			
			break;
		default:
			
			sql.append("select count(1)");
			break;
		}
		
		sql.append("from  custom  " );
		
		
		if(pars.containsKey("uname")&&pars.get("uname")!=null&&StringUtils.isNotBlank(pars.get("uname").toString())){
			
			
			sql.append("where uname like :userNameCon");
			
			parsCon.put("userNameCon", "%"+pars.get("uname")+"%");
			
		System.out.println("hahha");	
		}
		
		if (pars.containsKey("uid") && pars.get("uid") != null
				&& StringUtils.isNotBlank(pars.get("uid").toString())) {
			sql.append(" where   uid =:userIdCon");
			parsCon.put("userIdCon", pars.get("uid"));
		}
		
		if (pars.containsKey("uaccount") && pars.get("uaccount") != null
				&& StringUtils.isNotBlank(pars.get("uaccount").toString())) {
			sql.append(" where   uaccount =:userAccount");
			parsCon.put("userAccount", pars.get("uaccount"));
		}
		
		return  new Object[]{sql,parsCon};
		
		
		
		
		
	}

	public void saveUser(UserEntity user) {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * 修改了原来的方法，直接删除，不是设置状态
	 */

	public  Boolean  deleteUser(String userId) {
		// TODO Auto-generated method stub
		/* UserEntity findOne = userDao.findOne(userId);
		user.setStatus(Integer.valueOf(SystemConstant.STATUS_DELETE));
		userDAO.save(user);
*/
		Boolean isTrue = userDao.deleteByIds(UserEntity.class ,userId);
		
		return  isTrue;
	}

	public void deleteSplitUsers(String userIds) {
		String[] userIdArr = userIds.split(",");
		System.out.println(userIdArr+"youmeiyou ids");
      UserEntity userEntity=new UserEntity();
     
		for (String userId : userIdArr) {
			 System.out.println(userId);
			 userEntity.setId(userId);
			userDao.delete(userEntity);
			//Boolean deleteByIds = userDao.deleteByIds(UserEntity.class, userId);
		}

	}

	public Integer getMaxOrderNo() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validUserPwd(String loginId, String oldPwd, String inputPwd) {
		boolean flag = false;
		if (StringUtils.isNotBlank(loginId) && StringUtils.isNotBlank(oldPwd)
				&& StringUtils.isNotBlank(inputPwd)) {
			flag = passwordEncoder.isPasswordValid(oldPwd, inputPwd, loginId);
		}
		return flag;
	}

	public List<Map<String, Object>> getSystemRole() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getUserRoleByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getRoleCodeByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveUserRole(String userId, String roleIds) {
		// TODO Auto-generated method stub

	}

	public List<Map<String, Object>> queryUsersWithPermission(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUserByOrg(String orgId, int status) {
		// TODO Auto-generated method stub

	}

	public void updateUserPassword(String userId) {
		
		
	}

	public List<String> getImeiList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> findUserTree() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity findOne(String userId) {
		return userDao.findOne(userId);
	}

	public List<Map<String, Object>> queryUsersByOrgAndType(String orgId,
			List<String> userTypes, List<String> userOccupations, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> queryUsersByOrgAndType(String orgId,
			String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
