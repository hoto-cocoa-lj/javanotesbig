ssh整合的代码，使用spring创建hibernate的sessionfactory，所以没有hibernate.cfg.xml


customerdaoimpl里查看分页的代码，用hql会很麻烦
	@Override
	public List<Customer> findAllByQBC() {
		DetachedCriteria d = DetachedCriteria.forClass(Customer.class);
		return (List<Customer>) getHibernateTemplate().findByCriteria(d, 1, 2);
	}