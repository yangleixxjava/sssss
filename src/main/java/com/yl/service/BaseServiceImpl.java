package com.yl.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.yl.entity.BaseEntity;

public  abstract  class BaseServiceImpl  implements BaseService {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public <T extends BaseEntity> T save(T t) {
		entityManager.persist(t);
		return t;
	}

	public <T extends BaseEntity> T findOne(Class<T> classT, Serializable s) {
		return entityManager.find(classT, s);
	}

	@Transactional
	public <T extends BaseEntity> Boolean delete(Class<T> classT, Serializable key) {
		try {
			entityManager.remove(entityManager.find(classT, key));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public <T extends BaseEntity>Boolean deleteBatch(Class<T> classT, List<Serializable> keys) {
		try {
			for (Serializable key : keys) {
				delete(classT, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional
	public <T extends BaseEntity> T update(T t) {
		return entityManager.merge(t);
	}

}
