package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.Contract;

public interface ContractDao extends JpaRepository<Contract, String>,JpaSpecificationExecutor<Contract>{
	@Query(value="select * from CONTRACT_C where to_char(ship_time,'yyyy-mm')=?",nativeQuery=true)
	public List<Contract> getbyship(String s);
	

	
}
