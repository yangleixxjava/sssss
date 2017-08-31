package com.yl.service;

import java.util.List;
import java.util.Map;

import com.yl.entity.UserEntity;
import com.yl.utils.PageBean;

public interface UserService extends BaseService {

	
	/**
	 * */
	
	/**
	 * 查询用户分页数据列表
	 * 
	 * @date 2015年1月21日 下午5:59:05
	 * @author TW
	 * @param pars
	 *            过滤条件
	 * @param start
	 *            开始条数
	 * @param length
	 *            分页长度
	 * @return
	 */
	public List<Map<String, Object>> getUserList(Map<String, Object> pars,
			int start, int length);

	/**
	 * 查询用户列表总条数
	 * 
	 * @date 2015年1月21日 下午6:01:08
	 * @author TW
	 * @param pars
	 *            过滤条件
	 * @return
	 */
	public long getUserListCount(Map<String, Object> pars);

	/**
	 * 查询用户分页数据列表及总条数
	 * 
	 * @date 2015年1月28日 下午12:22:48
	 * @author TW
	 * @param pars
	 *            查询过滤条件
	 * @param pageBean
	 *            分页参数
	 * @return
	 */
	public Map<String, Object> getUserListAndCount(Map<String, Object> pars,
			PageBean pageBean);

	/**
	 * 保存用户
	 * 
	 * @date 2015年1月23日 下午2:51:29
	 * @author TW
	 * @param user
	 *            用户实体
	 */
	public void saveUser(UserEntity user);

	/**
	 * 根据用户Id删除用户
	 * 
	 * @date 2015年1月23日 下午5:52:03
	 * @author TW
	 * @param userId
	 *            用户Id
	 */
	public Boolean deleteUser(String userId);

	/**
	 * 删除以逗号分隔的用户Id集（eg:"id,id,id,id,"）
	 * 
	 * @date 2015年1月23日 下午7:37:12
	 * @author TW
	 * @param userIds
	 *            以逗号分隔的多个用户Id
	 */
	public void deleteSplitUsers(String userIds);

	public Integer getMaxOrderNo();

	/**
	 * 用户修改密码时，验证输入的密码经MD5加密后，与原密码是否一致
	 * 
	 * @date 2015年2月2日 下午4:19:13
	 * @author 屈锐华
	 * @param loginId
	 * @param oldPwd
	 * @param inputPwd
	 * @return
	 */
	public boolean validUserPwd(String loginId, String oldPwd, String inputPwd);

	/**
	 * 获取系统所有角色
	 * 
	 * @date 2015年2月11日 下午6:35:35
	 * @author TW
	 * @return
	 */
	public List<Map<String, Object>> getSystemRole();

	/**
	 * 根据用户Id 获取用户拥有的角色
	 * 
	 * @date 2015年2月11日 下午6:35:57
	 * @author TW
	 * @param userId
	 *            用户Id
	 * @return
	 */
	public List<Map<String, Object>> getUserRoleByUserId(String userId);
	
	/**
	 * 根据用户Id 获取用户拥有的角色对应的系统代码
	 * 
	 * @date 2015年2月11日 下午6:35:57
	 * @author chengcong
	 * @param userId 用户Id
	 * @return
	 */
	public List<Map<String, Object>> getRoleCodeByUserId(String userId);

	/**
	 * 保存用户与角色关联关系
	 * 
	 * @date 2015年2月11日 下午6:36:54
	 * @author TW
	 * @param userId
	 *            用户id
	 * @param roleIds
	 *            以逗号分隔的一个或多个角色Id
	 */
	public void saveUserRole(String userId, String roleIds);
	
	/**
	 * 根据权限获取用户列表
	 * @date 2015年4月15日 上午9:33:08
	 * @author Awesan
	 * @param orgId
	 * @return 
	 */
	public List<Map<String, Object>> queryUsersWithPermission(String orgId);
	
	/**
	 * 根据组织机构ID的状态，改变用户的状态（用于组织机构停用，启动。同时改变用户停用，启用）
	 * @date 2015年4月17日 下午1:57:22
	 * @author 蒋  迪
	 * @param orgId		组织结构ID
	 * @param status 	组织机构状态
	 */
	public void updateUserByOrg(String orgId, int status);
	
	/**
	 * 重置用户密码为6个0
	 * @date 2015年4月17日 下午2:24:58
	 * @author 蒋  迪
	 * @param userId 
	 */
	public void updateUserPassword(String userId);

	/**
	 * 获取手机串号集合
	 * @date 2016年9月28日 上午9:58:49
	 * @author 胡毅
	 * @return
	 */
	public List<String> getImeiList();
	
	/**
	 * 获取抢维修的用户树
	 * 
	 * @date 2017年3月11日 上午11:51:11
	 * @author 陈涛
	 * @return 
	 */
	public List<Map<String,Object>> findUserTree();
	
	public  UserEntity findOne(String userId);
	
	/**
	 * 根据组织机构和用户类型获取用户
	 * @date 2017年4月8日 下午5:59:18
	 * @author Administrator
	 * @param orgId
	 * @param userType
	 * @author hy 
	 * @param status 用户状态0：停用 1：可用 2：已删除
	 * @return 
	 */
	public List<Map<String, Object>> queryUsersByOrgAndType(String orgId, List<String> userTypes, List<String> userOccupations,String status);
	
	/**
	 * 根据组织机构和用户类型获取用户</br>
	 * 用户类型和工种由配置文件读取
	 * @date 2017年4月10日 上午10:18:40
	 * @author Administrator
	 * @param orgId
	 * @author hy 
	 * @param status 用户状态0：停用 1：可用 2：已删除
	 * @return 
	 */
	public List<Map<String, Object>> queryUsersByOrgAndType(String orgId,String status);
}
