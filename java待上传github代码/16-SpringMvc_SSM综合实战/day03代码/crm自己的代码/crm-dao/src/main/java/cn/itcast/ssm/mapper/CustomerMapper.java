package cn.itcast.ssm.mapper;

import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.CustomerExample;
import cn.itcast.ssm.pojo.QueryVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


public interface CustomerMapper {
	int countByExample(CustomerExample example);
	
	int countByExample(QueryVo q);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Long custId);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> findPage(QueryVo q);
    
    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Long custId);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}