package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.ExportProduct;

public interface ExportProductDao extends JpaRepository<ExportProduct, String>,JpaSpecificationExecutor<ExportProduct>{
//	@Query("from ExportProduct where ExportProductName like %?1%")
//	public List<ExportProduct> findExportProductByName(String s);
//	
//	@Query("from ExportProduct where parent.id=?1")
//	public List<ExportProduct> findExportProductByPid(String  s);
//	
//	@Query(value="select * from ExportProduct_p where ExportProduct_Name like ?",nativeQuery=true)
//	public List<ExportProduct> findExportProductByName1(String s);
	
}
