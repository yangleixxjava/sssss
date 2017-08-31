package com.yl.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

public class MyResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 2547865729505825914L;

	/* (non-Javadoc)
	 * @see org.hibernate.transform.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
	 */
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = 0;
		for(String name : aliases){
			map.put(name.toLowerCase(), tuple[i++]);
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public List transformList(List collection) {
		return collection;
	}

}
