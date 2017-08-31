<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="${path }/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="${path }/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="customSkin"/>
<script type="text/javascript" src="${path }/libs/js/jquery.js"></script>
<script type="text/javascript" src="${path }/libs/js/language/cn.js"></script>
<script type="text/javascript" src="${path }/libs/js/main.js"></script>
<!--框架必需end-->
<!--分离模式-框架必需start-->
<script type="text/javascript" src="${path }/libs/js/framework.js"></script>
<!--分离模式-框架必需end-->
<!--数据表格start-->
<script type="text/javascript" src="${path }/libs/js/table/quiGrid.js"></script>
<!--数据表格end-->

<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>

</head>
<body>
<div class="box1" id="formContent" whiteBg="true">

    <table class="tableStyle" formMode="view">

        <tr>

            <th colspan="2">用户信息</th>

        </tr>

        <tr>

            <td width="150">用户名id：</td>

            <td><c:out value="${user.uid}"/></td>

        </tr>

        <tr>

            <td>密码：</td>

            <td><c:out value="${user.upassword}"/></td>

        </tr>

        <tr>

            <td width="150">姓名：</td>

            <td><c:out value="${user.uname}"/></td>

        </tr>

        <tr>

            <td>phone：</td>

            <td><c:out value="${user.uphone}"/></td>

        </tr>

        <tr>

            <td>性别：</td>

            <td>

               <%--  <c:if test="${user.usex}"</c:if>

                <c:if test="${user.usex}"</c:if>
                
                
 --%><c:out value="${user.usex}"/>
            </td>

        </tr>

        <tr>

            <td>账户：</td>

            <td><c:out value="${user.uaccount}"/></td>

        </tr>

    </table>

    </div>

</body>
</html>