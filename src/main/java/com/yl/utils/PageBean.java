package com.yl.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class PageBean {


	//当前页数，前端界面request参数命名
	public static final String PAGE_INDEX = "pager.pageNo";
	//每页记录数，前端界面request参数命名
	public static final String PAGE_SIZE = "pager.pageSize";
	//总记录数，前端界面request参数命名
	public static final String TOTAL = "pager.totalRows";
	//排序列名称，前端界面request参数命名
	public static final String SORT_NAME = "sort";
	//排序顺序，默认desc，前端界面request参数命名
	public static final String SORT_ORDER = "direction";
	//默认排序规则
	private static final String DEFAULT_SORT_DIRECTION = "desc";
	//当前页
	private int pageIndex;
	//每页记录条数
	private int pageSize;
	//总页数
	private Long total;
	//排序列
	private String sortName;
	//排序规则
	private String sortOrder;
	
	public PageBean(HttpServletRequest request){
		//TODO JiangDi 从request中取出分页参数，如果为空用数据字典默认值代替pageSize
		pageIndex = PageUtil.getPageNo(request);
		pageSize = PageUtil.getPageSize(request, 1);
		total = 0l;
		sortName = PageUtil.getSortName(request);
		sortOrder = PageUtil.getSortOrder(request);
		if (StringUtils.isEmpty(sortOrder)){
			sortOrder = DEFAULT_SORT_DIRECTION;
		}
	}
	
	
	@Deprecated
	public PageBean(int pageIndex, int pageSize, long total){
		//TODO JiangDi 测试用构造函数，需删除
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	@Deprecated
	public PageBean(int pageIndex, int pageSize, long total, String sortName, String sortOrder){
		//TODO JiangDi 测试用构造函数，需删除
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
		this.sortName = sortName;
		this.sortOrder = sortOrder;
		if (StringUtils.isEmpty(this.sortOrder)){
			this.sortOrder = DEFAULT_SORT_DIRECTION;
		}
	}
	
	/**
	 * 根据当前页，每页记录条数，返回起始查询index
	 * @date 2015年1月26日 上午10:48:28
	 * @author 蒋迪
	 * @return 
	 */
	public int getStartIndex(){
		return (pageIndex-1)*pageSize;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}
