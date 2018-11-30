<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Highcharts Example</title>
		<script src="${pageContext.request.contextPath}/js/jquery-1.4.4.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/components/hcjs/highcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/components/hcjs/modules/exporting.js" type="text/javascript"></script>
		
		<style type="text/css">
${demo.css}
		</style>
		<script type="text/javascript">
$(function () {
	$.ajax({
		url:'statChartAction_line1',
		type:'get',
		dataType:'json',
		success:function(data){
/* 			alert('good'); */
			console.log(JSON.stringify(data));
			 $('#container').highcharts({
			        title: {
			            text: '访问人数',
			            x: -20 //center
			        },
			        subtitle: {
			            text: 'Source: my horse',
			            x: -20
			        },
			        xAxis: {
			            categories: data.time
			        },
			        yAxis: {
			            title: {
			                text: '人数'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '头'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [
			        {
			            name: '单位',
			            data:  data.count
			        }, 
			
			        ]
			    });
		},
		error:function(){
			alert('error');
		},
	})

   
});
		</script>
	</head>
	<body>


<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

	</body>
</html>
