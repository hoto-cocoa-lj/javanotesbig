package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.Export;

public interface ExportDao extends JpaRepository<Export, String>,JpaSpecificationExecutor<Export>{
//	@Query("from Export where ExportName like %?1%")
//	public List<Export> findExportByName(String s);
//	
//	@Query("from Export where parent.id=?1")
//	public List<Export> findExportByPid(String  s);
//	
//	@Query(value="select * from Export_p where Export_Name like ?",nativeQuery=true)
//	public List<Export> findExportByName1(String s);
	
}
