package com.yl.service;

import java.io.Serializable;
import java.util.List;

import com.yl.entity.BaseEntity;



public interface BaseService {
	/**
	 * 保存实体对象
	 * @date 2015年1月23日 下午1:36:35
	 * @author 蒋迪
	 * @param t	
	 * @return 	返回保存后的实体对象
	 */
	public <T extends BaseEntity> T save(T t);
	
	/**
	 * 根据实体类型，主键查询实体对象
	 * @date 2015年1月23日 下午1:36:37
	 * @author 蒋迪
	 * @param classT		实体类型
	 * @param key			主键值
	 * @return 	返回实体对象，未找到对象返回null
	 */
	
	
	public <T extends BaseEntity> T findOne(Class<T> classT, Serializable key);
	
	/**
	 * 根据实体类型，主键删除实体
	 * @date 2015年1月23日 下午1:36:40
	 * @author 蒋迪
	 * @param classT		实体类型
	 * @param key			主键值
	 * @return 	成功返回true，失败返回false
	 */
	public <T extends BaseEntity> Boolean delete(Class<T> classT, Serializable key);
	
	/**
	 * 根据实体类型，批量删除实体
	 * @date 2015年5月15日 下午2:28:40
	 * @author 蒋迪
	 * @param classT
	 * @param keys
	 * @return
	 */
	public <T extends BaseEntity>Boolean deleteBatch(Class<T> classT, List<Serializable> keys);
	
	/**
	 * 更新实体对象
	 * @date 2015年1月23日 下午1:36:43
	 * @author 蒋迪
	 * @param t		
	 * @return 	返回更新后的实体对象
	 */
	public <T extends BaseEntity> T update(T t);
}
