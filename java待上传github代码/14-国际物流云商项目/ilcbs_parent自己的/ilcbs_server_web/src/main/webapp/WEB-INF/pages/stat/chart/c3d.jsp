<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
         <script src="${pageContext.request.contextPath}/js/jquery-1.4.4.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/components/newAmcharts/style.css" type="text/css">
        <script src="${pageContext.request.contextPath}/components/newAmcharts/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/components/newAmcharts/amcharts/serial.js" type="text/javascript"></script>

       <script>
      /*  	$(function(){
        		$.ajax({
        			url:'${pageContext.request.contextPath}/stat/statChartAction_factorysale1',
        			type:'get',
        			dataType:'json',
        			success:function(data){
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
        	}) */
        
            var chart;
			
        			
        		
            /* var chartData = [
                {
                    "country": "USA",
                    "year2004": 3.5,
                    "year2005": 4.2
                },
                {
                    "country": "UK",
                    "year2004": 1.7,
                    "year2005": 3.1
                },
                {
                    "country": "Canada",
                    "year2004": 2.8,
                    "year2005": 2.9
                },
                {
                    "country": "Japan",
                    "year2004": 2.6,
                    "year2005": 2.3
                },
                {
                    "country": "France",
                    "year2004": 1.4,
                    "year2005": 2.1
                },
                {
                    "country": "Brazil",
                    "year2004": 2.6,
                    "year2005": 4.9
                },
                {
                    "country": "Russia",
                    "year2004": 6.4,
                    "year2005": 7.2
                },
                {
                    "country": "India",
                    "year2004": 8,
                    "year2005": 7.1
                },
                {
                    "country": "China",
                    "year2004": 9.9,
                    "year2005": 10.1
                }
            ]; */


            AmCharts.ready(function(){
                // SERIAL CHART
                $.ajax({
        			url:'${pageContext.request.contextPath}/stat/statChartAction_c3d1',
        			type:'get',
        			dataType:'json',
        			success:function(data){
        			for(i=0;i<data.length;i++){
        				console.log("data="+data[i].fac+"\t"+data[i].mon);
        				};
	                chart = new AmCharts.AmSerialChart();
	                chart.dataProvider = data;
	                chart.categoryField = "fac";
	                chart.color = "#FFFFFF";
	                chart.fontSize = 14;
	                chart.startDuration = 1;
	                chart.plotAreaFillAlphas = 0.2;
	                // the following two lines makes chart 3D
	                chart.angle = 30;
	                chart.depth3D = 60;
	
	                // AXES
	                // category
	                var categoryAxis = chart.categoryAxis;
	                categoryAxis.gridAlpha = 0.2;
	                categoryAxis.gridPosition = "start";
	                categoryAxis.gridColor = "#FFFFFF";
	                categoryAxis.axisColor = "#FFFFFF";
	                categoryAxis.axisAlpha = 0.5;
	                categoryAxis.dashLength = 5;
	
	                // value
	                var valueAxis = new AmCharts.ValueAxis();
	                valueAxis.stackType = "3d"; // This line makes chart 3D stacked (columns are placed one behind another)
	                valueAxis.gridAlpha = 0.2;
	                valueAxis.gridColor = "#FFFFFF";
	                valueAxis.axisColor = "#FFFFFF";
	                valueAxis.axisAlpha = 0.5;
	                valueAxis.dashLength = 5;
	                valueAxis.title = "GDP growth rate";
	                valueAxis.titleColor = "#FFFFFF";
	                valueAxis.unit = "%";
	                chart.addValueAxis(valueAxis);
	
	                // GRAPHS
	                // first graph
	                var graph1 = new AmCharts.AmGraph();
	                graph1.title = "mon";
	                graph1.valueField = "mon";
	                graph1.type = "column";
	                graph1.lineAlpha = 0;
	                graph1.lineColor = "#D2CB00";
	                graph1.fillAlphas = 1;
	                graph1.balloonText = "GDP grow in [[category]] (2004): <b>[[value]]</b>";
	                chart.addGraph(graph1);
	
	                chart.write("chartdiv");
           		},
                error:function(){
        			alert('error');
        			},
        		})
        	})
        </script>
    </head>

    <body>
   <a href="${pageContext.request.contextPath}/stat/statChartAction_factorysale1">12345</a>
        <div id="chartdiv" style="width:600px; height:400px;"></div>
    </body>

</html>