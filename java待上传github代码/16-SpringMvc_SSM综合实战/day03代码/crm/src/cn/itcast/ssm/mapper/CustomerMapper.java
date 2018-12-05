package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.QueryVo;

public interface CustomerMapper {

//	select * from customer c where  c.cust_name like '%黑马%' and  cust_source='6'  and cust_industry='1' and cust_level='22'
//			 LIMIT 0,5
	public List<Customer> findByPage(QueryVo queryVo);
	
	
//	select count(*) from customer  where  c.cust_name like '%黑马%' and  cust_source='6'  and cust_industry='1' and cust_level='22'
    public int findTotalCount(QueryVo queryVo);


	public Customer findById(long id);


	public void update(Customer customer);


	public void delete(long id);
}
