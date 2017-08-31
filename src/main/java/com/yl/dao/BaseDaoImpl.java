package com.yl.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.yl.utils.ExcelAnnotation;
import com.yl.utils.MyResultTransformer;
import com.yl.utils.PageBean;
@SuppressWarnings("unchecked")
 public class BaseDaoImpl<T, ID extends Serializable>
extends SimpleJpaRepository<T, ID> implements IBaseDao<T, ID >{
private EntityManager entityManager;
	
	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}
	
	public BaseDaoImpl(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager) {   
	    super(entityInformation, entityManager);   
	    this.entityManager = entityManager;   
	}

	public List<T> queryListBySql(String sql, int start, int length,
			Object[] params, Class<T> domainClass) {
		Query query = entityManager.createNativeQuery(sql, domainClass);
		setParameter(query, params);
		setPage(query, start, length);
		return (List<T>)query.getResultList();
	}

	public List<T> queryListBySql(String sql, int start, int length,
			Map<String, Object> params, Class<T> domainClass) {
		Query query = entityManager.createNativeQuery(sql, domainClass);
		setParameter(query, params);
		setPage(query, start, length);
		return (List<T>)query.getResultList();
	}
	
	public Long queryCountBySql(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		setParameter(query, params);
		Object result = query.getSingleResult();
		if (result != null) {
			return Long.valueOf(result.toString());
		}
		return 0l;
	}
	
	public Long queryCountBySql(String sql, Map<String, Object> params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params!=null){
			setParameter(query, params);
		}
		Object result = query.getSingleResult();
		if (result != null) {
			return Long.valueOf(result.toString());
		}
		return 0l;
	}
	
	private void setParameter(Query query, Object[] params){
		if (params != null) {
			int size = params.length;
			for (int i = 0; i < size; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
	}

	private void setParameter(Query query, Map<String, Object> params){
		if (params != null) {
			Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
			Entry<String, Object> en = null;
			while (it.hasNext()) {
				en = it.next();
				query.setParameter(en.getKey(), en.getValue());
			}
		}
	}
	
	private void setPage(Query query, int start, int length){
		if (length > 0) {
			query.setFirstResult(start);
			query.setMaxResults(length);
		}
	}

	public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Map<String, Object> params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params!=null){
			setParameter(query, params);
		}
		setPage(query, start, length);
		query.unwrap(SQLQuery.class).setResultTransformer(new MyResultTransformer());  
		return query.getResultList(); 
	}

	public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		setParameter(query, params);
		setPage(query, start, length);
		query.unwrap(SQLQuery.class).setResultTransformer(new MyResultTransformer());  
		return query.getResultList(); 
	}

	public Map<String, Object> queryPaginationListBySql(String sql, Map<String, Object> params, PageBean pageBean) {
		String sortSql = assemblySQLWithSort(sql, pageBean);
		List<Map<String, Object>> list = queryListBySql(sortSql, pageBean.getStartIndex(), pageBean.getPageSize(), params);
		sql = "select count(1) from ( " + sql + " ) as t";
		//sql = "select count(1) from custom";
		Long total = queryCountBySql(sql, params);
		pageBean.setTotal(total);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(PageBean.PAGE_INDEX, pageBean.getPageIndex());
		result.put(PageBean.TOTAL, total);
		result.put("rows", list);
		return result;
	}

	public Map<String, Object> queryPaginationListBySql(String sql, Object[] params, PageBean pageBean) {
		String sortSql = assemblySQLWithSort(sql, pageBean);
		List<Map<String, Object>> list = queryListBySql(sortSql, pageBean.getStartIndex(), pageBean.getPageSize(), params);
		sql = "select count(1) from ( " + sql + " ) ";
		Long total = queryCountBySql(sql, params);
		pageBean.setTotal(total);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(PageBean.PAGE_INDEX, pageBean.getPageIndex());
		result.put(PageBean.TOTAL, total);
		result.put("rows", list);
		return result;
	}
	
	public Boolean deleteByIds(Class<T> classOfT, String ids){
		String tableName = classOfT.getAnnotation(javax.persistence.Table.class).name();
		String primaryKey = "";
		Field fields[] = classOfT.getDeclaredFields();
		for (Field field : fields) {
			ExcelAnnotation ea = field.getAnnotation(com.yl.utils.ExcelAnnotation.class);
			ea.label();
			Column col = field.getAnnotation(javax.persistence.Column.class);
			Id id = field.getAnnotation(javax.persistence.Id.class);
			if (id!=null){
				primaryKey = col.name();
				break;
			}
		}
		String sql = "delete " + tableName + " where " + primaryKey + " in ( " + warpSplitString(ids) + " ) "; 
		int count = entityManager.createNativeQuery(sql).executeUpdate();
		if (count==0)	return false;
		return true;
	}
	
	/**
	 * 组织sql排序规则
	 * @date 2015年1月26日 上午10:19:55
	 * @author 蒋迪
	 * @param sql		原生sql
	 * @param pageBean	分页对象
	 * @return 
	 */
	private String assemblySQLWithSort(String sql, PageBean pageBean){
		if (!StringUtils.isEmpty(pageBean.getSortName())){
			if (sql.toLowerCase().contains("order by")){
				sql += ",";
			}else{
				sql += " order by ";
			}
			sql += pageBean.getSortName()+ " " + pageBean.getSortOrder();
		}
		return sql;
	}
	
	/**
	 * 将字符串用逗号分割，并用单引号包裹
	 * @date 2015年1月26日 下午2:32:35
	 * @author 蒋迪
	 * @param ids
	 * @return 
	 */
	private String warpSplitString(String ids){
		String result = "";
		String tmp[] = ids.split(",");
		for (String string : tmp) {
			result += "'"+string+"',";
		}
		result = result.substring(0, result.length()-1);
		return result;
	}

	public List<T> queryListBySql(String sql, Object[] params, Class<T> domainClass) {
		Query query = entityManager.createNativeQuery(sql, domainClass);
		setParameter(query, params);
		return (List<T>)query.getResultList();
	}

	public List<T> queryListBySql(String sql, Map<String, Object> params, Class<T> domainClass) {
		Query query = entityManager.createNativeQuery(sql, domainClass);
		setParameter(query, params);
		return (List<T>)query.getResultList();
	}

	public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params!=null){
			setParameter(query, params);
		}
		query.unwrap(SQLQuery.class).setResultTransformer(new MyResultTransformer());  
		return query.getResultList(); 
	}

	public List<Map<String, Object>> queryListBySql(String sql, Object[] params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params!=null){
			setParameter(query, params);
		}
		query.unwrap(SQLQuery.class).setResultTransformer(new MyResultTransformer());  
		return query.getResultList(); 
	}
	public int executeSql(String sql, Map<String, Object> params) {
		Query query = entityManager.createNativeQuery(sql);
		if (params!=null){
			setParameter(query, params);
		}
		return query.executeUpdate();
	}

}
