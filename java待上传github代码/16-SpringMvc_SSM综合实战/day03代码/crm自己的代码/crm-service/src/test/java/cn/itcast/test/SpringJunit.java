package cn.itcast.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.ssm.mapper.CustomerMapper;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.CustomerExample;
import cn.itcast.ssm.pojo.QueryVo;

@ContextConfiguration(value = "classpath:applicationContext*.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunit {
	
	@Autowired
	private CustomerMapper customerMapper;
	@Test
	public void findPage() {
		QueryVo q=new QueryVo();
		q.setCustName("");
		q.setCustSource("");
		
		CustomerExample e = new CustomerExample();
		e.createCriteria().andCustNameLike("%"+q.getCustName()+"%")

		.andCustSourceEqualTo(q.getCustSource());
		List<Customer> l = customerMapper.selectByExample(e);
		int a = q.getCurrentPage();
		int b = q.getPageSize();
		int toIndex=a*b;
		int fromIndex=(a-1)*b;
		
		System.out.println(l.size()+"\t"+l);
	
	}
}
