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
	 * 返回所查询domainClass所表示对象的集合
	 * @date 2015年1月22日 上午10:06:23
	 * @author 吴炫
	 * @param sql(原生sql语句，以?1开始)
	 * @param start
	 * @param length
	 * @param params
	 * @param domainClass
	 * @return List<T>：不为空
	 */
	public List<T> queryListBySql(String sql, int start, int length, Object[] params, Class<T> domainClass);
	/**
	 * 返回所查询domainClass所表示对象的集合
	 * @date 2015年1月22日 上午10:22:15
	 * @author 吴炫
	 * @param sql(原生sql语句，用:xxx占位赋值方式)
	 * @param start
	 * @param length
	 * @param params
	 * @param domainClass
	 * @return List<T>：不为空
	 */
	public List<T> queryListBySql(String sql, int start, int length, Map<String, Object> params, Class<T> domainClass);
	/**
	 * 通过原生sql查询，map中的key值全小写
	 * @date 2015年1月23日 下午4:20:50
	 * @author 蒋迪
	 * @param sql		(原生sql语句，用:xxx占位赋值方式)
	 * @param start		开始index
	 * @param length	查询记录条数
	 * @param params	查询参数
	 * @return 	List 《Map《String, Object》》集合，如未查询到值，返回size=0的list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Map<String, Object> params);
	
	/**
	 * 通过原生sql查询，返回值map中的key值全小写
	 * @date 2015年1月23日 下午4:20:50
	 * @author 蒋迪
	 * @param sql		sql(原生sql语句，以?1开始)
	 * @param start		开始index
	 * @param length	查询记录条数
	 * @param params	查询参数
	 * @return 	List 《Map《String, Object》》集合，如未查询到值，返回size=0的list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, int start, int length, Object[] params);
	/**
	 * 获取查询数量
	 * @date 2015年1月22日 上午10:07:03
	 * @author 吴炫
	 * @param sql(原生sql语句，以?1开始)
	 * @param params
	 * @return 0或者其他大于0
	 */
	public Long queryCountBySql(String sql, Object[] params);
	/**
	 * 获取查询数量
	 * @date 2015年1月22日 上午10:32:06
	 * @author 吴炫
	 * @param sql(原生sql语句，用:xxx占位赋值方式)
	 * @param params
	 * @return 0或者其他大于0
	 */
	public Long queryCountBySql(String sql, Map<String, Object> params);
	
	/**
	 * 通过原生sql进行分页查询
	 * @date 2015年1月26日 上午9:15:40
	 * @author 蒋迪
	 * @param sql		(原生sql语句，用:xxx占位赋值方式)
	 * @param params	查询参数
	 * @param pageBean	分页对象
	 * @return 
	 * key=PageBean.PAGE_INDEX_KEY，value=当前页数</br>	
	 * key=PageBean.TOTAL_KEY， value=总记录数</br>
	 * key=rows，value=查询结果list集合List《Map《String, Object》》
	 */
	public Map<String, Object> queryPaginationListBySql(String sql, Map<String, Object> params, PageBean pageBean);
	
	/**
	 * 通过原生sql进行分页查询
	 * @date 2015年1月26日 上午9:18:00
	 * @author 蒋迪
	 * @param sql		sql(原生sql语句，以?1开始)
	 * @param params	查询参数
	 * @param pageBean	分页对象
	 * @return 
	 * key=PageBean.PAGE_INDEX_KEY，value=当前页数</br>	
	 * key=PageBean.TOTAL_KEY， value=总记录数</br>
	 * key=rows，value=查询结果list集合List《Map《String, Object》》
	 */
	public Map<String, Object> queryPaginationListBySql(String sql, Object[] params, PageBean pageBean);
	
	/**
	 * 通过ids字符串批量删除
	 * @date 2015年1月26日 下午2:33:44
	 * @author 蒋迪
	 * @param classOfT	实体类型class
	 * @param ids		主键id字符串，多个用逗号分割
	 * @return 
	 */
	public Boolean deleteByIds(Class<T> classOfT, String ids);
	/**
	 * 返回所查询domainClass所表示对象的集合
	 * @date 2015年1月22日 上午10:06:23
	 * @author 吴炫
	 * @param sql(原生sql语句，以?1开始)
	 * @param params
	 * @param domainClass
	 * @return List<T>：不为空
	 */
	public List<T> queryListBySql(String sql, Object[] params, Class<T> domainClass);
	/**
	 * 返回所查询domainClass所表示对象的集合
	 * @date 2015年1月22日 上午10:22:15
	 * @author 吴炫
	 * @param sql(原生sql语句，用:xxx占位赋值方式)
	 * @param params
	 * @param domainClass
	 * @return List<T>：不为空
	 */
	public List<T> queryListBySql(String sql, Map<String, Object> params, Class<T> domainClass);
	/**
	 * 通过原生sql查询，map中的key值全小写
	 * @date 2015年1月23日 下午4:20:50
	 * @author 蒋迪
	 * @param sql		(原生sql语句，用:xxx占位赋值方式)
	 * @param params	查询参数
	 * @return 	List 《Map《String, Object》》集合，如未查询到值，返回size=0的list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> params);
	
	/**
	 * 通过原生sql查询，返回值map中的key值全小写
	 * @date 2015年1月23日 下午4:20:50
	 * @author 蒋迪
	 * @param sql		sql(原生sql语句，以?1开始)
	 * @param params	查询参数
	 * @return 	List 《Map《String, Object》》集合，如未查询到值，返回size=0的list
	 */
	public List<Map<String, Object>> queryListBySql(String sql, Object[] params);
	
	/**
	 * 执行原生sql，用于update/delete
	 * @date 2015年1月28日 上午11:39:12
	 * @author 蒋迪
	 * @param sql 
	 * @return 更改记录条数
	 */
	public int executeSql(String sql, Map<String, Object> params);
}
