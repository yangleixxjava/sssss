package com.yl.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {
	//Excel表头名称
		String label();
		//数据字典id
		String dictCode() default "";
		/**
		 * 例如：
		 * @Column(name = "OrgTypeId")
		 * @ExcelAnnotation(label = "机构类型", dictCode = "4028800a4b2a645d014b2a69c2740000")
		 * private String orgTypeId;
		 */
}
