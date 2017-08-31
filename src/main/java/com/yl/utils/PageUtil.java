package com.yl.utils;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class PageUtil {

	/**
	 * ��ȡҳ��
	 * 
	 * @param request
	 * @return
	 */
	public static int getPageNo(HttpServletRequest request) {
		int pageNo = 1;
		String page_str = request.getParameter("pager.pageNo");
		if (StringUtils.isNotBlank(page_str)
				&& Pattern.matches("\\d*", page_str)) {
			pageNo = Integer.parseInt(page_str);
		}
		return pageNo;
	}

	/**
	 * ��ȡ��ҳ��С����������ֵ��������ΪĬ��ֵ
	 * 
	 * @param request
	 * @param defaultValue
	 * @return
	 */
	public static int getPageSize(HttpServletRequest request, int defaultValue) {
		int pageSize = defaultValue;
		String selectPageSize = request.getParameter("pager.pageSize");
		if (StringUtils.isNotBlank(selectPageSize)
				&& Pattern.matches("\\d*", selectPageSize)) {
			pageSize = Integer.parseInt(selectPageSize);
		}
		return pageSize;
	}
	
	/**
	 * @date 2015��1��23�� ����8:49:12
	 * @author Awesan
	 * @param pageNo
	 * @param total
	 * @param datas
	 * @return 
	 */
	public static <T> String getPageReturn(int pageNo, long total, List<T> datas){
		//ƴ��json
		StringBuffer result = new StringBuffer();
		result.append("{\"pager.pageNo\":");
		result.append(pageNo);
		result.append(",\"pager.totalRows\":");
		result.append(total);
		result.append(",\"rows\":");
		result.append(new Gson().toJson(datas));
		result.append("}");
		return result.toString();
	}

	public static String getSortName(HttpServletRequest request){
		return request.getParameter(PageBean.SORT_NAME);
	}
	
	public static String getSortOrder(HttpServletRequest request){
		return request.getParameter(PageBean.SORT_ORDER);
	}
}
