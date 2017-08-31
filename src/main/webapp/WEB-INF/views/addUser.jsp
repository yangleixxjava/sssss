<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
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
}

//重置
function closeWin(){
    //刷新数据
    top.refresh(false);
    //关闭窗口
    parent.Dialog.close();
}

//提供给父页面调用提交表单
function submitThisForm() {
	jQuery("#myFormId").submit();
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
	width: 440px !important;
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
	<form action="${path }/user/addNewUser" method="post" id="myFormId">
	<div panelWidth="650" style="margin-right: 5px !important;">
	<div class="height_2"></div>
	<fieldset> 
		<legend>用户基本信息</legend> 
		<table class="tableStyle" formMode="transparent" footer="normal">
			<tr>
				<td width="17%">密码：</td>
				<td width="33%"><input type="text" name="upassword" class="validate[required,length[0,25]"/><span style="color: red;"> *</span></td>
				<td width="17%">性别：</td>
				<td width="33%"><input type="radio" id="radio-sex-0" name="usex" value="0" class="hand validate[minCheckbox[1]] checkbox" checked="checked"/><label for="radio-sex-0" class="hand">男</label>
					<input type="radio" id="radio-sex-1" name="usex" value="1" class="hand validate[minCheckbox[1]] checkbox"/><label for="radio-sex-1" class="hand">女</label>
					<span style="color: red;"> *</span>
				</td>
			</tr>
			<tr>
				<td>账户：</td>
				<td><input type="text" name="uaccount" class="validate[required,length[0,25],ajax[${path }/user/validLoginId|* 用户名已存在]]"/><span style="color: red;"> *</span></td>
				<td>电话：</td>
				<td><input type="text" name="uphone" /><span style="color: red;"> *</span></td>
				
			</tr>
			<tr>
			<td>姓名：</td>
				<td><input type="text" name="uname" /><span style="color: red;"> *</span></td>
				
			</tr>
<!-- 			<tr> -->
<!-- 				<td>帐号有效期：</td> -->
<!-- 				<td><input type="text" name="_validDate" class="date"/></td> -->
<!-- 			</tr> -->
		</table>
	</fieldset> 
	<div class="height_2"></div>
	 
	<div class="height_2"></div>
	
	<div class="height_2"></div>
	</div>
</form>
</body>
</html>