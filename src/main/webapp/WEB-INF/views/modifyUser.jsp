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
<script type="text/javascript" src="${path }/libs/js/form/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/selectTree.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validationRule.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/validation.js"></script>
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!-- 数字步进器start -->
<script type="text/javascript" src="${path }/libs/js/form/stepper.js"></script>

<link rel="stylesheet" type="text/css" href="${path }/libs/js/tree/ztree/ztree.css"></link>
<link rel="stylesheet" type="text/css" href="${path }/libs/css/import_basic.css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<!--框架必需end-->
<script type="text/javascript">

jQuery(function(){
	 jQuery("input[type='radio'][name='status'][value='${user.status}']").attr('checked','checked');
});

function initComplete(){
    //表单提交
    $('#myFormId').submit(function(){
            var valid = $('#myFormId').validationEngine({returnIsValid: true});
            if(valid){
               $(this).ajaxSubmit({
                    //表单提交成功后的回调
                    success: function(responseText, statusText, xhr, $form){
                        top.Dialog.alert(responseText.msg,function(){
                        	closeWin();
                        });
                    }
                }); 
             }
            //阻止表单默认提交事件
            return false; 
    });
    /* 屈锐华  */
    jQuery("#alterPwdBTN").click(function(){
    	alterPwdDialog=new top.Dialog();
		alterPwdDialog.Title = "修改密码";
		alterPwdDialog.URL = "${path }/user/toAlterUserPwd?uid=${user.uid}";
		//alterPwdDialog.URL = "${path }/user/toAlterUserPwd";
		alterPwdDialog.Width=500;
		alterPwdDialog.Height=300;
		alterPwdDialog.Left="60%";
		alterPwdDialog.show(); 
	 });
    
    //重置密码
    jQuery("#resetPWD").click(function(){
    	top.Dialog.confirm("是否将用户密码重置为000000",function(){
    		$.ajax({
    			type : 'POST',
    			url : '${path }/user/resetUserPWD',
    			data : {
    				userId:'${userId}',
    			},
    			success : function(result){
    				top.Dialog.alert("重置密码成功");
    			},
    			error : function(a){
    				Dialog.alert("重置密码失败");
    			},
    			dataType : 'json'
    		});
    	},
    	function(){
    		
    	});
	 });
}

function closeWin(){
    //刷新数据
    top.refresh(true);
    //关闭窗口
    parent.Dialog.close();
}
//提供给父页面调用提交表单
function submitThisForm() {
	jQuery("#myFormId").submit();
}


function refresh(isUpdate){
	if(!isUpdate){
		//重置排序
    	grid.options.sortName='userid';
    	grid.options.sortOrder="desc";
    	//页号重置为1
		grid.setNewPage(1);
	}
	grid.loadData();
}


</script>
<style type="text/css">
._address {
	width: 440px !important;
}

.selectbox {
	width: 107px !important;
}

textarea {
	width: 200px !important;
	height: 60px !important;
}

input[type="text"] {
	width: 130px;
}

input[type="password"] {
	width: 130px;
}
</style>
</head>
<body>
	<form action="${path }/user/doAddUser" method="post" id="myFormId">
	<input type="hidden" name="uid" value="${user.uid }">
	<input type="hidden" name="userName" value="${user.username }">
	<input type="hidden" name="loginId" value="${user.loginid }">
	<input type="hidden" name="sex" value="${user.sex }">
	<input type="hidden" name="password" value="${user.password }">
	<div panelWidth="650" style="margin-right: 5px !important;">
	<div class="height_10"></div>
	<fieldset> 
		<legend>用户基本信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="17%">姓名：</td>
				<td width="33%">${user.uname }</td>
				<td width="17%">性别：</td>
				<%-- <td width="33%"><c:if test="${user.sex eq '0'}">男</c:if><c:if test="${user.sex eq '1'}">女</c:if></td> --%>
				<td width="33%">${user.usex }</td>
			</tr>
			<tr>
				<td width="17%">账户：</td>
				<td width="33%">${user.uaccount }</td>
				<td width="17%">手机：</td>
				<td width="33%">${user.uphone }</td>
				
			</tr>
			
			<tr>
				<td>登录密码：</td><td >${user.upassword }<button type="button" id="alterPwdBTN">修改密码</button></td>
				<c:if test="${isRoot }">
					<td>重置密码：</td>
					<td><button type="button" id="resetPWD">重置密码</button></td>
				</c:if>
<!-- 				<td>帐号有效期：</td> -->
<%-- 				<td><input type="text" name="_validDate" class="date" value="<fmt:formatDate value="${user.validdate }" pattern="yyyy-MM-dd"/>"/></td> --%>
			</tr>
		</table>
	</fieldset> 
	
	</div>
</form>
</body>
</html>