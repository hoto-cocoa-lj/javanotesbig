package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.ContractProduct;

public interface ContractProductDao extends JpaRepository<ContractProduct, String>,JpaSpecificationExecutor<ContractProduct>{
	@Query(countQuery="select * from contractproduct_p where contract_id=?")
	public List<ContractProduct> findByContractId(String i);
	
	@Query(value="from ContractProduct where to_char(contract.shipTime,'yyyy-MM') = ?1")
	public List<ContractProduct> findCpByShipTime(String shipTime);
	
	//根据购销合同id集合一次性查询购销合同货物
	@Query(value="from ContractProduct where contract.id in (?1)")
	public List<ContractProduct> findCpByContractIds(String[] ids);
}
