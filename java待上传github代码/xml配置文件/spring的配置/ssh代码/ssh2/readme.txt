ssh整合的代码，使用spring创建hibernate的sessionfactory，所以没有hibernate.cfg.xml


customerdaoimpl里查看分页的代码，用hql会很麻烦
	@Override
	public List<Customer> findAllByQBC() {
		DetachedCriteria d = DetachedCriteria.forClass(Customer.class);
		return (List<Customer>) getHibernateTemplate().findByCriteria(d, 1, 2);
	}
	
	
	
在web.xml的struts的过滤器前面加上如下代码，可以解决延迟加载的问题。
延迟加载是指a类1对多个b类，那么获取b类时a的引用是延迟加载的，而service层使用了事务，
dao层获取b，传递到service层，service层传给web层前会关闭数据库的session，
所以web层展示b时再次加载a就会报no-session的错误

  <filter>
  	<filter-name>OpenSessionInViewFilter</filter-name>
  	<filter-class>org.springframework.orm.hibernate5.support.OpenSessionInViewFilter</filter-class>
  </filter> 
  
  <filter-mapping>
  	<filter-name>OpenSessionInViewFilter</filter-name>
  	<url-pattern>*.action</url-pattern>
  </filter-mapping>