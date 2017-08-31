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

<!--分离模式-弹窗组件start-->
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<!--分离模式-弹窗组件end-->

<!--树组件start -->
<link href="${path }/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${path }/libs/js/tree/ztree/ztree.js"></script>
<!--树组件end -->

<!-- 表单start -->
<script type="text/javascript" src="${path }/libs/js/form/form.js"></script>
<!-- 表单end -->
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>
<script type="text/javascript" src="${path }/libs/js/popup/dialog.js"></script>
<script type="text/javascript" src="${path }/libs/js/popup/drag.js"></script>
<!--布局控件start-->
<script type="text/javascript" src="${path }/libs/js/nav/layout.js"></script>
<!--布局控件end-->
<script src="${path }/libs/js/form/form.js" type="text/javascript"></script>

<!--数字分页start-->
<script type="text/javascript" src="${path }/libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->
</head>
<body>

<div class="box2" panelTitle="查询用户" id="searchPanel">

    <form action="${path }/user/getUserInfo" id="queryForm" method="post">

        <input type="hidden" id="parentId" name="parentId" value="1"/>

        <table>

            <tr>

                <td>姓名：</td>

                <td>

                    <input type="text" id="nameInput" name="uname" />

                    <input type="text" style="width:2px;display:none;"/>

                </td>

                <td><button type="button"   onclick="searchHandler()"><span class="icon_find">查询</span></button></td>

                <td><button type="button"   onclick="resetSearch()"><span class="icon_reload">重置</span></button></td>

                <td><div class="red">此示例由后台支持</div></td>

            </tr>

        </table>

    </form>

</div>


<div class="padding_right5">

    <div id="maingrid">
    </div>

</div>

<script type="text/javascript">

var grid = null;
var path = "${path}";
function initComplete(){    
     console.log("有没有");
    grid = $("#maingrid").quiGrid({

       columns:[

           { display: 'id', name: 'uid',     align: 'left', width: "10%"},
           { display: '账户', name: 'uaccount',  align: 'left', width: "20%"},
           { display: '姓名', name: 'uname', align: 'left', width: "10%"},

           { display: '密码', name: 'upassword',  align: 'left',  width:"20%"} ,

           { display: '手机', name: 'uphone',    align: 'left',  width:"20%"} ,
           { display: '性别', name: 'usex',    align: 'left',  width:"20%"} ,

       { display: '操作', isAllowHide: false, align: 'left', width:80,

                   render: function (rowdata, rowindex, value, column){

        return '<div class="padding_top4 padding_left5">'

                                 + '<span class="img_list hand" title="查看" onclick="onView(' + rowdata.uid + ')"></span>'

                                 + '<span class="img_edit hand" title="修改" onclick="onEdit(' + rowdata.uid + ')"></span>' 

                                 + '<span class="img_delete hand" title="删除" onclick="onDelete(' + rowdata.uid+','+rowindex + ')"></span>'

                             + '</div>';

                     }

                }

         ],

     url: path+'/user/getUserInfo', sortName: 'uid',rownumbers:true,checkbox:true,

     height: '100%', width:"100%",pageSize:10,percentWidthMode:true,

     toolbar:{

     items:[
           
{text: '新增', click: addUser,    iconClass: 'icon_add'},

{ line : true },

{text: '批量删除', click: deleteUnit, iconClass: 'icon_delete'},

{ line : true }



            

        ]

    }

    });

}


function searchHandler(){
	 var query = $("#queryForm").formToArray(); 
	 grid.setOptions({ params : query});
	 //页号重置为1
	 grid.setNewPage(1);
	//刷新表格数据 
	grid.loadData();
}

//重置查询
function resetSearch(){
	//$("#queryForm")[0].reset();
	$("#nameInput").val("");
	/*  jQuery("#accountInput").val("");
	$("#queryForm select").attr("selectedValue","");
	$("#queryForm select").resetValue();  */
	searchHandler();
}

function onView(rowid){
	Dialog.open({
		URL:"${path }/user/toShowUser?uid=" + rowid,
		Title:"查看",Width:650, Height:520});
}

function onDelete(rowid){
	Dialog.confirm("确定要删除该记录吗？",function(){
	  	//删除记录
	  	$.post("${path }/user/delUser",
	  			{"ids":rowid},
	  			function(result){
	  				handleResult(result.status);
				},"json");
		});
}

function handleResult(result){
	if(result == 1){
		Dialog.alert("删除成功！",null,null,null,1);
		grid.loadData();
	}else{
		Dialog.alert("删除失败！");
	}
}

//修改	
function onEdit(rowid){
	var diag = new Dialog();
	diag.Title = "修改用户";
	diag.URL = "${path }/user/toModifyUser?uid=" + rowid;
	diag.ShowButtonRow=true;
	diag.Height = 521;
	diag.Width = 650;
	diag.OkButtonText=" 确 定 ";
	diag.OKEvent = function(){
		diag.innerFrame.contentWindow.submitThisForm();
	};
	diag.show();
}
//刷新表格数据并重置排序和页数
function refresh(isUpdate){
	if(!isUpdate){
		//重置排序
    	grid.options.sortName='uid';
    	grid.options.sortOrder="desc";
    	//页号重置为1
		grid.setNewPage(1);
	}
	grid.loadData();
}
function addUser() {
	var diag = new Dialog();
	diag.Title = "新增用户";
	diag.URL = "${path }/user/toAddNewUser";
	diag.ShowButtonRow=true;
	diag.Height = 521;
	diag.Width = 650;
	diag.OkButtonText=" 确 定 ";
	diag.OKEvent = function(){
		diag.innerFrame.contentWindow.submitThisForm();
	};
	diag.show();
}
function deleteUnit() {
	var rows = grid.getSelectedRows();
	var rowsLength = rows.length;
	if(rowsLength == 0) {
		Dialog.alert("请选中要删除的记录!");
		return;
	}else{
		for (var i = 0; i < rows.length; i++) {
			if("admin" == rows[i].loginid){
				Dialog.alert("您要删除的行中包含管理员用户<br />　　　请剔除管理员用户");
				return;
			}
		}
	}
	Dialog.confirm("确定要删除吗？",function(){
		$.post("${path }/user/delUsers",
				//获取所有选中行
				getSelectIds(grid),
				function(result){
					handleResult(result.status);
				},
				"json");
	});
}




//获取所有选中行获取选中行的id 格式为 ids=1&ids=2 
function getSelectIds(grid) {
	var selectedRows = grid.getSelectedRows();
	var selectedRowsLength = selectedRows.length;
	var ids = "";
	
	for(var i = 0;i<selectedRowsLength;i++) {
		ids += selectedRows[i].uid + ",";
	}
	return {"ids":ids};
}

</script>



</body>
</html>