<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>树的编辑</title>
<!--框架必需start-->
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>

<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>

<script type="text/javascript">
	function initComplete(){
	    //表单提交
	    $('#alterPwdForm').submit(function(){
	            var valid = $('#alterPwdForm').validationEngine({returnIsValid: true});
	            if(valid){
	               $(this).ajaxSubmit({
	                    //表单提交成功后的回调
	                    success: function(responseText, statusText, xhr, $form){
	                        top.Dialog.alert(responseText.msg,function(){
	                        	top.Dialog.close();
	                        });
	                    }
	                }); 
	             }
	            //阻止表单默认提交事件
	            return false; 
	    });
	}
	
</script>

</head>
<body>
	<form action="${path }/user/doAlterUserPwd" method="post" id="alterPwdForm" target="frmright1">
	<input type="hidden" name="uid" value="${uid}">
	
	<%-- <input type="hidden" name="loginId" value="${alterUser.loginId }">
	<input type="hidden" name="oldPwd" value="${alterUser.password }"> --%>
	<div class="box3" panelWidth="200" style="margin-top:20px;">
	<table class="tableStyle" formMode="transparent" footer="normal">
			<%-- <tr>
				<td width="30%">旧密码：</td>
				<td width="70%"><input type="password" checkCaps="false" name="inputPwd" id="inputPwd" style="width:180px;" class="validate[required,ajax[${path }/user/validUserPwd?loginId=${alterUser.loginId }&oldPwd=${alterUser.password }|* 旧密码验证失败!]]"></td>
			</tr> --%>
			<tr>
				<td width="30%">新密码：</td>
				<td width="70%"><input type="password" checkCaps="false" name="newPwd" id="newPwd" style="width:180px;" class="validate[required,length[6,32]]"></td>
			</tr>
		<!-- 	<tr>
				<td width="30%">确认密码：</td>
				<td width="70%"><input type="password" checkCaps="false" name="confirmPwd" id="confirmPwd" style="width:180px;" class="validate[required,length[6,32],confirm[newPwd]]"></td>
			</tr> -->
	</table>
	<div class="height_5"></div>
	<div class="padding_top10">
		<table class="tableStyle" formMode="transparent">
			<tr>
				<td colspan="4">
					<input type="submit" value="提交"/>
					<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>
	</div> 
	</div>
</form>
</body>
</html>