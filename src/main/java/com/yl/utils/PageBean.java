package com.yl.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class PageBean {


	//��ǰҳ����ǰ�˽���request��������
	public static final String PAGE_INDEX = "pager.pageNo";
	//ÿҳ��¼����ǰ�˽���request��������
	public static final String PAGE_SIZE = "pager.pageSize";
	//�ܼ�¼����ǰ�˽���request��������
	public static final String TOTAL = "pager.totalRows";
	//���������ƣ�ǰ�˽���request��������
	public static final String SORT_NAME = "sort";
	//����˳��Ĭ��desc��ǰ�˽���request��������
	public static final String SORT_ORDER = "direction";
	//Ĭ���������
	private static final String DEFAULT_SORT_DIRECTION = "desc";
	//��ǰҳ
	private int pageIndex;
	//ÿҳ��¼����
	private int pageSize;
	//��ҳ��
	private Long total;
	//������
	private String sortName;
	//�������
	private String sortOrder;
	
	public PageBean(HttpServletRequest request){
		//TODO JiangDi ��request��ȡ����ҳ���������Ϊ���������ֵ�Ĭ��ֵ����pageSize
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
		//TODO JiangDi �����ù��캯������ɾ��
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	@Deprecated
	public PageBean(int pageIndex, int pageSize, long total, String sortName, String sortOrder){
		//TODO JiangDi �����ù��캯������ɾ��
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
	 * ���ݵ�ǰҳ��ÿҳ��¼������������ʼ��ѯindex
	 * @date 2015��1��26�� ����10:48:28
	 * @author ����
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
