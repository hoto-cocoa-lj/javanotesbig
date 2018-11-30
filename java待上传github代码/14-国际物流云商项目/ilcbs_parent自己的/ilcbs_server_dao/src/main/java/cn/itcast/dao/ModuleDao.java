package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.Module;

public interface ModuleDao extends JpaRepository<Module, String>,JpaSpecificationExecutor<Module>{
//	@Query("from Module where moduleName like %?1%")
//	public List<Module> findModuleByName(String s);
	
//	@Query("from Module where parent.id=?1")
//	public List<Module> findModuleByPid(String  s);
	
//	@Query(value="select * from module_p where DEPT_Name like ?",nativeQuery=true)
//	public List<Module> findModuleByName1(String s);
	
//	public List<Module> findModuleByNameLike(String  s);
	
}
