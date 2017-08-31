package com.yl.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.yl.utils.PageBean;
@NoRepositoryBean
public interface IBaseDao <T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T>{
	/**
	 * ��������ѯdomainClass����ʾ����ļ���
	 * @date 2015��1��22�� ����10:06:23
	 * @author ����
	 * @param sql(ԭ��sql��䣬��?1��ʼ)
	 * @param start
	 * @param length
	 * @param params
	 * @param domainClass
	 * @return List<T>����Ϊ��
	 */
	public List<T> queryListBySql(String sql, int start, int length, Object[] params, Class<T> domainClass);
	/**
	 * ��������ѯdomainClass����ʾ����ļ���
	 * @date 2015��1��22�� ����10:22:15
	 * @author ����
	 * @param sql(ԭ��sql��䣬��:xxxռλ��ֵ��ʽ)
	 * @param start
	 * @param length
	 * @param params
	 * @param domainClass
	 * @return List<T>����Ϊ��
	 */
	public List<T> queryListBySql(String sql, int start, int length, Map<String, Object> params, Class<T> domainClass);
	/**
	 * ͨ��ԭ��sql��ѯ��map�е�keyֵȫСд
	 * @date 2015��1��23�� ����4:20:50
	 * @author ����
	 * @param sql		(ԭ��sql��䣬��:xxxռλ��ֵ��ʽ)
	 * @param start		��ʼindex
	 * @param length	��ѯ��¼����
	 * @param params	��ѯ����
	 * @return 	List ��Map��String, Object�������ϣ���δ��ѯ��ֵ������size=0��list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Map<String, Object> params);
	
	/**
	 * ͨ��ԭ��sql��ѯ������ֵmap�е�keyֵȫСд
	 * @date 2015��1��23�� ����4:20:50
	 * @author ����
	 * @param sql		sql(ԭ��sql��䣬��?1��ʼ)
	 * @param start		��ʼindex
	 * @param length	��ѯ��¼����
	 * @param params	��ѯ����
	 * @return 	List ��Map��String, Object�������ϣ���δ��ѯ��ֵ������size=0��list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Object[] params);
	/**
	 * ��ȡ��ѯ����
	 * @date 2015��1��22�� ����10:07:03
	 * @author ����
	 * @param sql(ԭ��sql��䣬��?1��ʼ)
	 * @param params
	 * @return 0������������0
	 */
	public Long queryCountBySql(String sql, Object[] params);
	/**
	 * ��ȡ��ѯ����
	 * @date 2015��1��22�� ����10:32:06
	 * @author ����
	 * @param sql(ԭ��sql��䣬��:xxxռλ��ֵ��ʽ)
	 * @param params
	 * @return 0������������0
	 */
	public Long queryCountBySql(String sql, Map<String, Object> params);
	
	/**
	 * ͨ��ԭ��sql���з�ҳ��ѯ
	 * @date 2015��1��26�� ����9:15:40
	 * @author ����
	 * @param sql		(ԭ��sql��䣬��:xxxռλ��ֵ��ʽ)
	 * @param params	��ѯ����
	 * @param pageBean	��ҳ����
	 * @return 
	 * key=PageBean.PAGE_INDEX_KEY��value=��ǰҳ��</br>	
	 * key=PageBean.TOTAL_KEY�� value=�ܼ�¼��</br>
	 * key=rows��value=��ѯ���list����List��Map��String, Object����
	 */
	public Map<String, Object> queryPaginationListBySql(String sql, Map<String, Object> params, PageBean pageBean);
	
	/**
	 * ͨ��ԭ��sql���з�ҳ��ѯ
	 * @date 2015��1��26�� ����9:18:00
	 * @author ����
	 * @param sql		sql(ԭ��sql��䣬��?1��ʼ)
	 * @param params	��ѯ����
	 * @param pageBean	��ҳ����
	 * @return 
	 * key=PageBean.PAGE_INDEX_KEY��value=��ǰҳ��</br>	
	 * key=PageBean.TOTAL_KEY�� value=�ܼ�¼��</br>
	 * key=rows��value=��ѯ���list����List��Map��String, Object����
	 */
	public Map<String, Object> queryPaginationListBySql(String sql, Object[] params, PageBean pageBean);
	
	/**
	 * ͨ��ids�ַ�������ɾ��
	 * @date 2015��1��26�� ����2:33:44
	 * @author ����
	 * @param classOfT	ʵ������class
	 * @param ids		����id�ַ���������ö��ŷָ�
	 * @return 
	 */
	public Boolean deleteByIds(Class<T> classOfT, String ids);
	/**
	 * ��������ѯdomainClass����ʾ����ļ���
	 * @date 2015��1��22�� ����10:06:23
	 * @author ����
	 * @param sql(ԭ��sql��䣬��?1��ʼ)
	 * @param params
	 * @param domainClass
	 * @return List<T>����Ϊ��
	 */
	public List<T> queryListBySql(String sql, Object[] params, Class<T> domainClass);
	/**
	 * ��������ѯdomainClass����ʾ����ļ���
	 * @date 2015��1��22�� ����10:22:15
	 * @author ����
	 * @param sql(ԭ��sql��䣬��:xxxռλ��ֵ��ʽ)
	 * @param params
	 * @param domainClass
	 * @return List<T>����Ϊ��
	 */
	public List<T> queryListBySql(String sql, Map<String, Object> params, Class<T> domainClass);
	/**
	 * ͨ��ԭ��sql��ѯ��map�е�keyֵȫСд
	 * @date 2015��1��23�� ����4:20:50
	 * @author ����
	 * @param sql		(ԭ��sql��䣬��:xxxռλ��ֵ��ʽ)
	 * @param params	��ѯ����
	 * @return 	List ��Map��String, Object�������ϣ���δ��ѯ��ֵ������size=0��list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> params);
	
	/**
	 * ͨ��ԭ��sql��ѯ������ֵmap�е�keyֵȫСд
	 * @date 2015��1��23�� ����4:20:50
	 * @author ����
	 * @param sql		sql(ԭ��sql��䣬��?1��ʼ)
	 * @param params	��ѯ����
	 * @return 	List ��Map��String, Object�������ϣ���δ��ѯ��ֵ������size=0��list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, Object[] params);
	
	/**
	 * ִ��ԭ��sql������update/delete
	 * @date 2015��1��28�� ����11:39:12
	 * @author ����
	 * @param sql 
	 * @return ���ļ�¼����
	 */
	public int executeSql(String sql, Map<String, Object> params);
}
