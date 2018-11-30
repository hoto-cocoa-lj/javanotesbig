<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js""></script>
</head>

<body>
<form name="icform" method="post">
<input type=hidden name=id value=1111>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('packingListAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增装箱单
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	       
	        <tr>
	            <td class="columnTitle">卖方：</td>
	            <td class="tableContent"><input type="text" name="seller" value=""/></td>
	     
	            <td class="columnTitle">买方：</td>
	            <td class="tableContent"><input type="text" name="buyer" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">发票号：</td>
	            <td class="tableContent"><input type="text" name="invoiceNo" value=""/></td>
	      
	            <td class="columnTitle">发票日期：</td>
	            <td class="tableContent">
	            <input type="text" style="width:90px;" name="invoiceDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">唛头：</td>
	            <td class="tableContent"><input type="text" name="marks" value=""/></td>
	      
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="descriptions" value=""/></td>
	        </tr>	


	
	<c:forEach items="${exportList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="exportIds" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.id}</td>
		<td align="center">
			${fn:length(o.exportProducts)}
			/
			<c:set var="extNumber" value="0"></c:set><!-- 设置一个变量，用来累加，初始值0 -->
			<c:forEach items="${o.exportProducts}" var="ep">
			   <c:if test="${fn:length(ep.extEproducts)!=0 }">
					<c:set var="extNumber" value="${extNumber +fn:length(ep.extEproducts)}"/>
				</c:if>
			</c:forEach>
			${extNumber}
		</td>		
		<td>${o.lcno}</td>
		<td>${o.consignee}</td>
		<td>${o.shipmentPort}</td>
		<td>${o.destinationPort}</td>
		<td>${o.transportMode}</td>
		<td>${o.priceCondition}</td>
		<td><fmt:formatDate value="${o.inputDate }" pattern="yyyy-MM-dd"/></td>
		<td>
		   <c:if test="${o.state==0}">草稿</c:if>
		   <c:if test="${o.state==1}">已上报</c:if>
		   <c:if test="${o.state==2}">已报运</c:if>
		</td>
	</tr>
	</c:forEach>
	

	
	      
		</table>
	</div>
 
 
</form>
</body>
</html>

