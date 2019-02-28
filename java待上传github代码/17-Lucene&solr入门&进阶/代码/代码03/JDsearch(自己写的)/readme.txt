自己在看视频前写的，主要有这几个收获：
1_spring整合junit测试，classpath那里不能用通配符*，当初配置时在这里找bug找了一个多小时；

2···参数里写Integer page,String price即可(页面上该参数必须在表单里，且有name)，
如果需要回填参数，需要在对应参数属性里有value，比如page上有value=${fuckpage}，
然后在方法里写model.addAttribute("fuckpage",值)即可。

3_尽量减少web/controller和dao/mapper层的代码，放到service层。

4_使用spring管理solr的HttpSolrServer，原来的代码是SolrServer solrServer=new HttpSolrServer(url)，
将鼠标放在HttpSolrServer上可以看到url的形参是baseUrl，这说明有setBaseUrl的set方法，
当然也可以去源码看。所以如下配置
	 <bean id="httpSolrServe" class="org.apache.solr.client.solrj.impl.HttpSolrServer">
	 	<property name="baseUrl" value="http://localhost:10080/solr/collection1"> </property>
	 </bean>，
然后一直报错No constructor with 0 arguments defined in class，提示没有构造参数，
看源码(不看也能发现)发现new HttpSolrServer(url)是构造方法，修改配置如下可以解决：
	 <bean id="httpSolrServe" class="org.apache.solr.client.solrj.impl.HttpSolrServer">
	 	<constructor-arg index="0" value="http://localhost:10080/solr/collection1"> </constructor-arg>
	 </bean> 

5_报错java.lang.NumberFormatException: For input string: "60.0"，原来的代码是：
		double pc1 = (l.getNumFound()*1.0/60);
		Integer pageCount1=Integer.parseInt(Math.ceil(pp)+"");
最后会Integer.parseInt(60.0)，无法转成int报错。修改后的代码是：
		Double pc = Math.ceil((l.getNumFound()*1.0/60));
		Integer pageCount=pc.intValue();
