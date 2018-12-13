package cn.itcast.ssm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.ssm.mapper.BaseDictMapper;
import cn.itcast.ssm.mapper.CustomerMapper;
import cn.itcast.ssm.pojo.BaseDict;
import cn.itcast.ssm.pojo.BaseDictExample;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.CustomerExample;
import cn.itcast.ssm.pojo.CustomerExample.Criteria;
import cn.itcast.ssm.service.BaseDictService;
import cn.itcast.ssm.service.CustomerService;
import cn.itcast.ssm.pojo.QueryVo;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerMapper customerMapper;

	@Override 
	public List<Customer> findPage(QueryVo q) {
		CustomerExample e = new CustomerExample();
		Criteria c = e.createCriteria();
		
		if(q.getCustName()!=null && q.getCustName()!=""){
			c.andCustNameLike("%"+q.getCustName()+"%");
		}
		if(q.getCustIndustry()!=null&&q.getCustIndustry()!=""){
			c.andCustIndustryEqualTo(q.getCustIndustry());
		}
		if(q.getCustLevel()!=null && q.getCustLevel()!=""){
			c.andCustLevelEqualTo(q.getCustLevel());
		}
		if(q.getCustSource()!=null && q.getCustSource()!=""){
			c.andCustSourceEqualTo(q.getCustSource());
		}
		
		List<Customer> l = customerMapper.selectByExample(e);
		List<Customer> ll=new ArrayList<Customer>();
		int a = q.getCurrentPage();
		int b = q.getPageSize();
		int toIndex=a*b+b;
		int fromIndex=a*b;
		for(int i=fromIndex;i<toIndex;i++){			
			try {
				ll.add(l.get(i));
			} catch (Exception e1) {
				System.out.println("长度少于pagesize");
	//				e1.printStackTrace();
				break;
			}
		}
		return ll;		
	}

	@Override
	public int findTotal(QueryVo q){
		CustomerExample e = new CustomerExample();
		Criteria c = e.createCriteria();
		
		if(q.getCustName()!=null && q.getCustName()!=""){
			c.andCustNameLike("%"+q.getCustName()+"%");
		}
		if(q.getCustIndustry()!=null&&q.getCustIndustry()!=""){
			c.andCustIndustryEqualTo(q.getCustIndustry());
		}
		if(q.getCustLevel()!=null && q.getCustLevel()!=""){
			c.andCustLevelEqualTo(q.getCustLevel());
		}
		if(q.getCustSource()!=null && q.getCustSource()!=""){
			c.andCustSourceEqualTo(q.getCustSource());
		}
		
		return customerMapper.countByExample(e);
	}

	@Override
	public Customer getById(long id) {
		return customerMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Customer c) {
		// TODO Auto-generated method stub
		customerMapper.updateByPrimaryKeySelective(c);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		customerMapper.deleteByPrimaryKey(id);
		
	}


}
