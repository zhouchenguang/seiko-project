<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9" > </meta>
	<!--head-->
	<%@ include file="../layout/head.jsp" %>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<%--<div data-options="region:'west',border:true,split:true," title="分类管理" style="width:150px; padding:5px;">--%>
		<%--<ul id="category-tree" class="easyui-tree"></ul>--%>
	<%--</div>--%>
	<form id="searchForm" >
	<div data-options="region:'center',border:false">
		<!-- Begin of toolbar -->
		<div id="toolbar">
			<div class="toolbar-button">
				<a href="javascript:void(0)" class="easyui-linkbutton addBtn" iconCls="icon-add"  plain="true">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton editBtn" iconCls="icon-edit" plain="true">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton delBtn" iconCls="icon-remove" plain="true">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton celBtn" iconCls="icon-cancel" plain="true">取消</a>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-print" onclick="openAdd()" plain="true">打印</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-help" onclick="openEdit()" plain="true">帮助</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="remove()" plain="true">撤销</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-redo" onclick="cancel()" plain="true">重做</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-sum" onclick="reload()" plain="true">总计</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-back" onclick="reload()" plain="true">返回</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-tip" onclick="reload()" plain="true">提示</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="reload()" plain="true">保存</a>--%>
				<%--<a href="#" class="easyui-linkbutton" iconCls="icon-cut" onclick="reload()" plain="true">剪切</a>--%>
			</div>
			<div class="toolbar-search">
				<label>起始时间：</label><input class="easyui-datebox" style="width:100px">
				<label>结束时间：</label><input class="easyui-datebox" style="width:100px">
				<label>用户组：</label>
				<select class="easyui-combobox" panelHeight="auto" style="width:100px">
					<option value="0">选择用户组</option>
					<option value="1">黄钻</option>
					<option value="2">红钻</option>
					<option value="3">蓝钻</option>
				</select>
				<label>关键词：</label><input class="text" style="width:100px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">开始检索</a>
			</div>
		</div>
		<!-- End of toolbar -->
		<table class="easyui-datagrid" id="datagrid" data-options="url:'${path}/user/listJson',pageList: [5,10,15,20],pageSize:10,fitColumns:'true'" pagination="true" width="width" sortName="userId" sortOrder="desc">
			<thead>
			<tr>
				<th data-options="field:'userId',checkbox:true" ></th>
				<th data-options="field:'userName',align:'center',width:150,sortable:'true'" >用户名称</th>
				<th data-options="field:'email',align:'center',width:150">email</th>
				<th data-options="field:'mobile',align:'center',width:150">手机号码</th>
				<th data-options="field:'remark',align:'center',width:200">备注</th>
				<%--<th data-options="field:'mktprice',align:'center',sortable:'true',width:70" formatter="formatMoney">销售价格</th>--%>
			</tr>
			</thead>
		</table>
	</div>
	</form>
</div>
<!-- Begin of easyui-dialog -->
<div id="userDialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="userForm" method="post">
		<input name="userId" type="hidden" />
		<table>
			<tr>
				<td width="60" align="right">姓 名:</td>
				<td><input type="text" name="userName" class="text" /></td>
			</tr>
			<tr>
				<td align="right">邮 箱:</td>
				<td><input type="text" name="email" class="text" /></td>
			</tr>
			<tr>
				<td align="right">手 机:</td>
				<td><input type="text" name="mobile" class="text" /></td>
			</tr>
			<tr>
				<td valign="top" align="right">备 注:</td>
				<td><textarea name="remark" rows="6" class="textarea" style="width:260px"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<!-- End of easyui-dialog -->

<%@ include file="../layout/footer.jsp" %>
<script src="<c:url value='/statics/js/user/user.js'/>"></script>
</body>
</html>