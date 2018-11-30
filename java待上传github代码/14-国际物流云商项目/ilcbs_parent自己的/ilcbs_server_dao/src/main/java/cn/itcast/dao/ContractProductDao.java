package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.Contract;
import cn.itcast.domain.ContractProduct;

public interface ContractProductDao extends JpaRepository<ContractProduct, String>,JpaSpecificationExecutor<ContractProduct>{
	@Query(countQuery="select * from contractproduct_p where contract_id=?")
	public List<ContractProduct> findByContractId(String i);
	
	@Query(value="from ContractProduct where to_char(contract.shipTime,'yyyy-MM') = ?1")
	public List<ContractProduct> findCpByShipTime(String shipTime);
	
	//根据购销合同id集合一次性查询购销合同货物
	@Query(value="from ContractProduct where contract.id in (?1)")
	public List<ContractProduct> findCpByContractIds(String[] ids);
	
	@Query(value="from ContractProduct where contract.id=?1 order by factoryName")
	public List<ContractProduct> findCpList(String id);
	
	@Query(value="select factory_name,sum(amount) from contract_product_C t group by t.factory_name"
			,nativeQuery=true)
	public List<Object[]> getgb();
	
	@Query(value="select * from  (select product_no,amount from CONTRACT_PRODUCT_C t order by amount desc nulls last) w where rownum<16"
			,nativeQuery=true)
	public List<Object[]> getline();
	
	@Query(value="select o.a1,nvl(p.c,0) from online_info_t o left join "+
				"(select to_char(t.login_time,'HH24') z,count(*) c "+
				"from LOGIN_LOG_P t group by to_char(t.login_time,'HH24')) p "+
				"on o.a1=p.z order by o.a1",nativeQuery=true)
	public List<Object[]> getip();
}
