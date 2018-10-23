ssh整合的代码，使用注解，删除了struts.xml。
web.xml里面要如下配置struts的信息。




  
  <!--下面这一段要剪切到web.xml	 配置前端控制器  拦截所有的浏览器请求 
  千万注意：两个init-param里面的内容是用来配置注解加载struts的，这样可以删除struts.xml，不用的话必须注释掉。
  而且struts.convention.package.locators的值action，表示src下处理action的java文件所在文件夹的名字！！！不然报错no mapping-->
 
  
  <filter>
  	<filter-name>strust2_3</filter-name>
  	<!-- 在整个项目下搜资源:ctrl+shift+t -->
  	<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  	<init-param>
  			  <param-name>struts.objectFactory</param-name>
	  		<param-value>spring</param-value>
  	</init-param>
	<init-param>
	  		<param-name>struts.convention.package.locators</param-name>
	  		<param-value>action</param-value>
	 </init-param>  
  
  </filter>
  
  <filter-mapping>
  	<filter-name>strust2_3</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
    <!-- 配置前端控制器 完毕--> 