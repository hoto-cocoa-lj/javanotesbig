package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.ExtEproduct;

public interface ExtEproductDao extends JpaRepository<ExtEproduct, String>,JpaSpecificationExecutor<ExtEproduct>{
//	@Query("from ExtEproduct where ExtEproductName like %?1%")
//	public List<ExtEproduct> findExtEproductByName(String s);
//	
//	@Query("from ExtEproduct where parent.id=?1")
//	public List<ExtEproduct> findExtEproductByPid(String  s);
//	
//	@Query(value="select * from ExtEproduct_p where ExtEproduct_Name like ?",nativeQuery=true)
//	public List<ExtEproduct> findExtEproductByName1(String s);
	
}
