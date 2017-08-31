package com.yl.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {
	//Excel��ͷ����
		String label();
		//�����ֵ�id
		String dictCode() default "";
		/**
		 * ���磺
		 * @Column(name = "OrgTypeId")
		 * @ExcelAnnotation(label = "��������", dictCode = "4028800a4b2a645d014b2a69c2740000")
		 * private String orgTypeId;
		 */
}
