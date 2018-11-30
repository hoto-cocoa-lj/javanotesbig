package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.Dept;

public interface DeptDao extends JpaRepository<Dept, String>,JpaSpecificationExecutor<Dept>{
	@Query("from Dept where deptName like %?1%")
	public List<Dept> findDeptByName(String s);
	
	@Query("from Dept where parent.id=?1")
	public List<Dept> findDeptByPid(String  s);
	
	@Query(value="select * from dept_p where DEPT_Name like ?",nativeQuery=true)
	public List<Dept> findDeptByName1(String s);
	
}
