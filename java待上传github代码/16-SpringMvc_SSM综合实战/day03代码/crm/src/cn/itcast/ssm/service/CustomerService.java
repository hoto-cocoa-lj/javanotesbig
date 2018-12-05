package cn.itcast.ssm.service;

import cn.itcast.common.utils.PageBean;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.QueryVo;

public interface CustomerService {

	public PageBean  findByPage(QueryVo queryVo);

	public Customer findById(long id);

	public void update(Customer customer);

	public void delete(long id);
}
