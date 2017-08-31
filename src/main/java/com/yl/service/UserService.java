package com.yl.service;

import java.util.List;
import java.util.Map;

import com.yl.entity.UserEntity;
import com.yl.utils.PageBean;

public interface UserService extends BaseService {

	
	/**
	 * */
	
	/**
	 * ��ѯ�û���ҳ�����б�
	 * 
	 * @date 2015��1��21�� ����5:59:05
	 * @author TW
	 * @param pars
	 *            ��������
	 * @param start
	 *            ��ʼ����
	 * @param length
	 *            ��ҳ����
	 * @return
	 */
	public List<Map<String, Object>> getUserList(Map<String, Object> pars,
			int start, int length);

	/**
	 * ��ѯ�û��б�������
	 * 
	 * @date 2015��1��21�� ����6:01:08
	 * @author TW
	 * @param pars
	 *            ��������
	 * @return
	 */
	public long getUserListCount(Map<String, Object> pars);

	/**
	 * ��ѯ�û���ҳ�����б�������
	 * 
	 * @date 2015��1��28�� ����12:22:48
	 * @author TW
	 * @param pars
	 *            ��ѯ��������
	 * @param pageBean
	 *            ��ҳ����
	 * @return
	 */
	public Map<String, Object> getUserListAndCount(Map<String, Object> pars,
			PageBean pageBean);

	/**
	 * �����û�
	 * 
	 * @date 2015��1��23�� ����2:51:29
	 * @author TW
	 * @param user
	 *            �û�ʵ��
	 */
	public void saveUser(UserEntity user);

	/**
	 * �����û�Idɾ���û�
	 * 
	 * @date 2015��1��23�� ����5:52:03
	 * @author TW
	 * @param userId
	 *            �û�Id
	 */
	public Boolean deleteUser(String userId);

	/**
	 * ɾ���Զ��ŷָ����û�Id����eg:"id,id,id,id,"��
	 * 
	 * @date 2015��1��23�� ����7:37:12
	 * @author TW
	 * @param userIds
	 *            �Զ��ŷָ��Ķ���û�Id
	 */
	public void deleteSplitUsers(String userIds);

	public Integer getMaxOrderNo();

	/**
	 * �û��޸�����ʱ����֤��������뾭MD5���ܺ���ԭ�����Ƿ�һ��
	 * 
	 * @date 2015��2��2�� ����4:19:13
	 * @author ����
	 * @param loginId
	 * @param oldPwd
	 * @param inputPwd
	 * @return
	 */
	public boolean validUserPwd(String loginId, String oldPwd, String inputPwd);

	/**
	 * ��ȡϵͳ���н�ɫ
	 * 
	 * @date 2015��2��11�� ����6:35:35
	 * @author TW
	 * @return
	 */
	public List<Map<String, Object>> getSystemRole();

	/**
	 * �����û�Id ��ȡ�û�ӵ�еĽ�ɫ
	 * 
	 * @date 2015��2��11�� ����6:35:57
	 * @author TW
	 * @param userId
	 *            �û�Id
	 * @return
	 */
	public List<Map<String, Object>> getUserRoleByUserId(String userId);
	
	/**
	 * �����û�Id ��ȡ�û�ӵ�еĽ�ɫ��Ӧ��ϵͳ����
	 * 
	 * @date 2015��2��11�� ����6:35:57
	 * @author chengcong
	 * @param userId �û�Id
	 * @return
	 */
	public List<Map<String, Object>> getRoleCodeByUserId(String userId);

	/**
	 * �����û����ɫ������ϵ
	 * 
	 * @date 2015��2��11�� ����6:36:54
	 * @author TW
	 * @param userId
	 *            �û�id
	 * @param roleIds
	 *            �Զ��ŷָ���һ��������ɫId
	 */
	public void saveUserRole(String userId, String roleIds);
	
	/**
	 * ����Ȩ�޻�ȡ�û��б�
	 * @date 2015��4��15�� ����9:33:08
	 * @author Awesan
	 * @param orgId
	 * @return 
	 */
	public List<Map<String, Object>> queryUsersWithPermission(String orgId);
	
	/**
	 * ������֯����ID��״̬���ı��û���״̬��������֯����ͣ�ã�������ͬʱ�ı��û�ͣ�ã����ã�
	 * @date 2015��4��17�� ����1:57:22
	 * @author ��  ��
	 * @param orgId		��֯�ṹID
	 * @param status 	��֯����״̬
	 */
	public void updateUserByOrg(String orgId, int status);
	
	/**
	 * �����û�����Ϊ6��0
	 * @date 2015��4��17�� ����2:24:58
	 * @author ��  ��
	 * @param userId 
	 */
	public void updateUserPassword(String userId);

	/**
	 * ��ȡ�ֻ����ż���
	 * @date 2016��9��28�� ����9:58:49
	 * @author ����
	 * @return
	 */
	public List<String> getImeiList();
	
	/**
	 * ��ȡ��ά�޵��û���
	 * 
	 * @date 2017��3��11�� ����11:51:11
	 * @author ����
	 * @return 
	 */
	public List<Map<String,Object>> findUserTree();
	
	public  UserEntity findOne(String userId);
	
	/**
	 * ������֯�������û����ͻ�ȡ�û�
	 * @date 2017��4��8�� ����5:59:18
	 * @author Administrator
	 * @param orgId
	 * @param userType
	 * @author hy 
	 * @param status �û�״̬0��ͣ�� 1������ 2����ɾ��
	 * @return 
	 */
	public List<Map<String, Object>> queryUsersByOrgAndType(String orgId, List<String> userTypes, List<String> userOccupations,String status);
	
	/**
	 * ������֯�������û����ͻ�ȡ�û�</br>
	 * �û����ͺ͹����������ļ���ȡ
	 * @date 2017��4��10�� ����10:18:40
	 * @author Administrator
	 * @param orgId
	 * @author hy 
	 * @param status �û�״̬0��ͣ�� 1������ 2����ɾ��
	 * @return 
	 */
	public List<Map<String, Object>> queryUsersByOrgAndType(String orgId,String status);
}
