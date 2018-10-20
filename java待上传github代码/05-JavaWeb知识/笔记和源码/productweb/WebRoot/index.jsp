<%@ page language="java" import="java.util.*" import="bean.product" 
isELIgnored="false"
import="bean.Pagebean" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.3.min.js" ></script>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
	.div{text-align: center;}
		td{border:1px solid red;width:20%;}
		th{border:1px solid red;width:20%;height:40px;}
		table{border:1px solid yellow;width:100%;text-align:center;}
.hidden{display:none;}
	</style>
  </head>
  
  <body>
  <form action="${pageContext.request.contextPath}/pro" method="post">
  <input type=hidden name=me value="search"/>
   <input type=text name=search />
<input type=submit value="search"/><hr>
  </form>

<a href=${pageContext.request.contextPath}/pro?me=getall >getall</a><hr>
<a  id=add >add</a><hr>


<form action="${pageContext.request.contextPath}/pro" method=post class=hidden id=form1>
	名字<textarea rows="" cols="" name=name></textarea><br>
	<input type=hidden name=me value=add />
	价格<input type=text name=price><br>
	日期<input type=date name=date><br>
	图片<input type=text name=img><br>
	<input type=submit value=增加商品>

</form>

<form action="${pageContext.request.contextPath}/pro" method=post >
<input type=hidden name="me" value="delete">
<table>
<%-- <%System.out.println("request.getAttribute(addres)="+request.getAttribute("addres"));%> --%>
<%if(request.getAttribute("addres")!=null){%>
<tr><td colspan="5">${addres}</td></tr>
<%}%>
<%if(pageContext.findAttribute("deleteres")!=null){%>
<tr><td colspan="5">${deleteres}</td></tr>
<%}%>

<%
	if(request.getAttribute("pb")!=null){
	Pagebean pb=(Pagebean)request.getAttribute("pb");
	request.getSession().setAttribute("searchurl",	request.getContextPath()+"/pro?me=search&search="
				+request.getSession().getAttribute("searchword")+"&cpage=");
	List<product> all=pb.getList();	if(all!=null&&all.size()==0){%>
		<tr><td>"没有找到任何商品"</td></tr>
		
<%} else if(all!=null&&all.size()>0){%>
	<tr>
	<th><input type=checkbox id="checkboxall" /><input type=submit value="删除" ></th>
	<th>图片</th>
	<th>名字</th>
	<th>价格</th>
	<th>日期</th>
	</tr>
	<%for(product p:all){%>
	<tr>
	<td><input type=checkbox name=checkbox value="<%=p.getUid()%>" class="checkbox"><%=p.getImg()%></td>
		<td><img height=75px src="${pageContext.request.contextPath}/<%=p.getImg()%>" /></td>
		<td><%=p.getName() %></td>
		<td><%=p.getPrice()%></td>
		<td><%=p.getDate() %></td>
	</tr>
	<%}}%>
</table>

<div class="div">
	<%if(pb.getCpage()!=1){ %>
		<a href="${searchurl}${pb.cpage-1}">上一页</a>
	<%}else{%>
		上一页
	<%}%>
	<%for(int i=1;i<pb.getPagenum()+1;i++){ %>
		<%if(i!=pb.getCpage()){ %>
		 	<a href="${searchurl}<%=i%>"><%=i%></a>
		 <%}else{ %>
		 	<%=i%>
	<%}}if(pb.getCpage()<pb.getPagenum()){ %>
		<a href="${searchurl}${pb.cpage+1}">下一页</a>
	<%}else{%>
		下一页
	<%}%>
	第
	<select id="select">
		<%for(int i=1;i<pb.getPagenum()+1;i++){ 
			if(i!=pb.getCpage()){%>
				<option value="${searchurl}<%=i%>"><%=i %></option>
			<% }else{%>
				<option selected=true value="${searchurl}<%=i%>"><%=i %></option>
			<%}}%>
	</select>
	
	页/共${pb.pagenum}页

</div>
	<%}%>
</form>

  </body>
  
  <script type="text/javascript">
  	window.onload=function(){
  		document.getElementById("add").onclick=function(){
  			document.getElementById("form1").classList.remove("hidden");
  		}
  		 document.getElementById("checkboxall").onchange=function(){
  			checkboxes=document.getElementsByClassName("checkbox");
  			for(i=0;i<checkboxes.length;i++){
  					checkboxes[i].checked=this.checked;
  						
  			}
  		}
  	}
  	$(function(){
	$("td:nth-child(3)").click(function(){
  			$("#form1").removeClass("hidden");
  			tr=$(this).parent("tr");
  			console.log(tr);
  			$("#form1 textarea[name=name]").val(tr.find('td').eq(2).text());
  			$("#form1 input[name=price]").val(tr.find('td').eq(3).text());
  			$("#form1 input[name=date]").val(tr.find('td').eq(4).text());
  			$("#form1 input[name=img]").val(tr.find('td').eq(0).text()); 		
  	})
  	 $("#select").change(function(){
  	 	$(location).attr("href",$(this).val());
  	 })
 
})

  
  </script>
</html>
