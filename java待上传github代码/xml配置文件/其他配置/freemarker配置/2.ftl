
<#-- assign指令:此指令用于在页面上定义一个变量  -->

<#assign x='x1'>
<#assign y={'x1':'y1','x2':'y2'}>
<#assign z=[1,2,3]>
${x}<br>	${y.x1}<br>	${z[1]}<br>

<#assign ls=[{'id':'i2','name':'n2'},{'id':'i4','name':'n4'},{'id':'i6','name':'n6'}]>


<#-- list指令:如果想在循环中得到索引，使用循环变量+_index就可以得到-->
<#-- 内建函数语法格式: 变量+?+函数名称 -->
	共有 ${ls?size}条记录：
	<#list ls as l> 
		${l_index} id:${l.id} name:${l.name}
	</#list><br>


<#-- 转换JSON字符串为对象,不转会报错 freemarker.core.NonHashException -->
    <#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
	<#assign data=text?eval />
	开户行：${data.bank}  账号：${data.account}


<#-- 日期格式化,直接输出${today}会报错Can't convert the date-like value to string -->
	当前日期：${today?date} <br>
	当前时间：${today?time} <br>   
	当前日期+时间：${today?datetime} <br>        
	日期格式化：  ${today?string("yyyy年MM月")}
<#--输出如下:
	当前日期：2019-3-6 <br>
	当前时间：17:07:04 <br>   
	当前日期+时间：2019-3-6 17:07:04 <br>        
	日期格式化：  2019年03月 -->
	

<#-- 数字转换为字符串,下面的代码输出如下 1,234,567,890--1234567890   -->
	<#assign point=1234567890 />
	${point}--${point?c}
	
	
<#--判断某变量是否存在:??,用法为:variable??,如果该变量存在,返回true,否则返回false  -->
	<#if aaa??>
	  aaa变量存在,${aaa}
	<#else>
	  aaa变量不存在
	</#if>
		
	<#-- !对输出的空值做处理，只输出无返回值
		输出name的值：${name}。如果name为null，就会报错。
		输出name的值：${name!}。如果name为null，就不会报错，什么也没输出。（重点）
		输出name的值：${name!"默认值"}。如果name为null，就输出”默认值”字符串。（重点）
		输出name的值：${name!100}。如果name为null，就输出100。
		输出u.n的值：${(u.n)!"默认值"},如果u或n为null，就输出默认值。（重点）
		输出u.n的值：${u.n!"默认值"},如果u为null会报错，如果n为null，就输出默认值。		
		使用default内建函数来处理：${user.name?default('vakin')}  （较繁琐）  -->
	<#-- 下面输出	aaa变量不存在	  aaa变量存在,sss	 -->
	<#assign aaa=aaa!'sss' />		
		 
	<#if aaa??>
	  aaa变量存在,${aaa}
	<#else>
	  aaa变量不存在
	</#if>
	
	
<#--  运算符

算数运算符
	FreeMarker表达式中完全支持算术运算,FreeMarker支持的算术运算符包括:+, - , * , / , %
	
逻辑运算符
	逻辑与:&& 
	逻辑或:|| 
	逻辑非:! 
	逻辑运算符只能作用于布尔值,否则将产生错误 

比较运算符
	表达式中支持的比较运算符有如下几个: 
	1  =或者==:判断两个值是否相等. 
	2  !=:判断两个值是否不等. 
	3  >或者gt:判断左边值是否大于右边值 
	4  >=或者gte:判断左边值是否大于等于右边值 
	5  <或者lt:判断左边值是否小于右边值 
	6  <=或者lte:判断左边值是否小于等于右边值 
	注意:  =和!=可以用于字符串,数值和日期来比较是否相等,但=和!=两边必须是相同类型的值,
	否则会产生错误,而且FreeMarker是精确比较,"x","x ","X"是不等的.
	其它的运行符可以作用于数字和日期,但不能作用于字符串,大部分的时候,
	使用gt等字母运算符代替>会有更好的效果,因为 FreeMarker会把>解释成FTL标签的结束字符,
	当然,也可以使用括号来避免这种情况,如:<#if (x>y)>  -->

	

<#--  在html页面里把java对象变成js对象，注意"${item.id!"null_id"}"的双引号不能省略，
	这时个字符串，如果省略前端会报错找不到变量id1 -->
<#assign itemList=[{'id':'id1','name':'name1'},{'id':'id2'},
					{'name':'name3'},{'id':'id4','name':'name4'}]>
<script>
	   var skuList=[    	    
	    	    <#list itemList as item>  	    	    	
		    		{
		    		"id":"${item.id!"null_id"}",
		    		"name":"${item.name!"null_name"}",	
		    		} ,     		
	    		</#list>
	   ];  
	   console.log(skuList);
</script>