<html>
<head>
	<meta charset="utf-8">
	<title>Freemarker入门小DEMO </title>
</head>
<body>

<#--我只是一个注释，我不会有任何输出,这里name和message来自后台  -->
${name},你好。${message}
<#assign a=false>

<#--if指令:在freemarker的判断中，可以使用= 也可以使用== -->
<#if !a>
	<#--include指令:此指令用于模板文件的嵌套-->
	<#include '2.ftl'>
<#else>
	nonono
</#if>

</body>
</html>