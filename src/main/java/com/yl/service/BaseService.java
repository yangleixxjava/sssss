package com.yl.service;

import java.io.Serializable;
import java.util.List;

import com.yl.entity.BaseEntity;



public interface BaseService {
	/**
	 * ����ʵ�����
	 * @date 2015��1��23�� ����1:36:35
	 * @author ����
	 * @param t	
	 * @return 	���ر�����ʵ�����
	 */
	public <T extends BaseEntity> T save(T t);
	
	/**
	 * ����ʵ�����ͣ�������ѯʵ�����
	 * @date 2015��1��23�� ����1:36:37
	 * @author ����
	 * @param classT		ʵ������
	 * @param key			����ֵ
	 * @return 	����ʵ�����δ�ҵ����󷵻�null
	 */
	
	
	public <T extends BaseEntity> T findOne(Class<T> classT, Serializable key);
	
	/**
	 * ����ʵ�����ͣ�����ɾ��ʵ��
	 * @date 2015��1��23�� ����1:36:40
	 * @author ����
	 * @param classT		ʵ������
	 * @param key			����ֵ
	 * @return 	�ɹ�����true��ʧ�ܷ���false
	 */
	public <T extends BaseEntity> Boolean delete(Class<T> classT, Serializable key);
	
	/**
	 * ����ʵ�����ͣ�����ɾ��ʵ��
	 * @date 2015��5��15�� ����2:28:40
	 * @author ����
	 * @param classT
	 * @param keys
	 * @return
	 */
	public <T extends BaseEntity>Boolean deleteBatch(Class<T> classT, List<Serializable> keys);
	
	/**
	 * ����ʵ�����
	 * @date 2015��1��23�� ����1:36:43
	 * @author ����
	 * @param t		
	 * @return 	���ظ��º��ʵ�����
	 */
	public <T extends BaseEntity> T update(T t);
}
