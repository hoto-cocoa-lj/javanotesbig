<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - checkbox</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../components/zTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="../components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="../components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="../components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
	<!--
	<script type="text/javascript" src="../../../js/jquery.ztree.exedit-3.5.js"></script>
	-->
	<SCRIPT type="text/javascript">
	
		

/* 		var zNodes =[
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"系统管理", checked:true, open:true},
			{ id:21, pId:2, name:"部门管理"},
			{ id:22, pId:2, name:"角色管理", open:true,checked:true},
			{ id:221, pId:22, name:"角色新增", checked:true},
			{ id:222, pId:22, name:"角色修改"},
			{ id:23, pId:2, name:"随意勾选 2-3"},
			{ id:1, pId:0, name:"系统首页", open:true}
		]; */
		
		
		var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
		
		$(document).ready(function(){
			$.ajax({
				url:'roleAction_genzTreeNodes?id=${id}',
				dataType:'json',
				type:'get',
				success:function(value){
					$.fn.zTree.init($("#treeDemo"), setting, value);
				}
			})
		});
		
	</SCRIPT>
</HEAD>
<BODY>
<ul id="treeDemo" class="ztree"></ul>
</div>
</BODY>
</HTML>