<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
         <script src="${pageContext.request.contextPath}/js/jquery-1.4.4.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/components/newAmcharts/style.css" type="text/css">
        <script src="${pageContext.request.contextPath}/components/newAmcharts/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/components/newAmcharts/amcharts/pie.js" type="text/javascript"></script>

        <script>
       	$(function(){
        		$.ajax({
        			url:'${pageContext.request.contextPath}/stat/statChartAction_factorysale1',
        			type:'get',
        			dataType:'json',
        			success:function(data){
        		/* 	alert("success") */
        			for(i=0;i<data.length;i++){
        				console.log("data="+data[i].fac+"\t"+data[i].mon);
        				}
        				var chart;
        				  AmCharts.ready(function () {
                // PIE CHART
	                chart = new AmCharts.AmPieChart();
	
	                // title of the chart
	                chart.addTitle("Visitors countries", 16);
	
	                chart.dataProvider = data;
	                chart.titleField = "fac";
	                chart.valueField = "mon";
	                chart.sequencedAnimation = true;
	                chart.startEffect = "elastic";
	                chart.innerRadius = "30%";
	                chart.startDuration = 2;
	                chart.labelRadius = 15;
	                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
	                // the following two lines makes the chart 3D
	                chart.depth3D = 10;
	                chart.angle = 15;

                // WRITE
                chart.write("chartdiv");
            });
        				
        			},
        			error:function(){
        				alert('error');
        			},
        		})
        	})
        
            /*  var chart;
			
            var chartData = [
                {
                    "country": "United States",
                    "visits": "9252"
                },
                {
                    "country": "China",
                    "visits": "1882"
                },
                {
                    "country": "Japan",
                    "visits": "1809"
                },
                {
                    "country": "Germany",
                    "visits": "1322"
                },
                {
                    "country": "United Kingdom",
                    "visits": "1122"
                },
                {
                    "country": "France",
                    "visits": "1114"
                },
                {
                    "country": "India",
                    "visits": "984"
                },
                {
                    "country": "Spain",
                    "visits": "711"
                }
            ];


            AmCharts.ready(function () {
                // PIE CHART
                chart = new AmCharts.AmPieChart();

                // title of the chart
                chart.addTitle("Visitors countries", 16);

                chart.dataProvider = chartData;
                chart.titleField = "country";
                chart.valueField = "visits";
                chart.sequencedAnimation = true;
                chart.startEffect = "elastic";
                chart.innerRadius = "30%";
                chart.startDuration = 2;
                chart.labelRadius = 15;
                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
                // the following two lines makes the chart 3D
                chart.depth3D = 10;
                chart.angle = 15;

                // WRITE
                chart.write("chartdiv");
            });  */
        </script>
    </head>

    <body>
   <a href="${pageContext.request.contextPath}/stat/statChartAction_factorysale1">12345</a>
        <div id="chartdiv" style="width:600px; height:400px;"></div>
    </body>

</html>