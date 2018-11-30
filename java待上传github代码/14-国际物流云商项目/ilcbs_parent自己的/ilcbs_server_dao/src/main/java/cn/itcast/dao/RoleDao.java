package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.Role;

public interface RoleDao extends JpaRepository<Role, String>,JpaSpecificationExecutor<Role>{
//	@Query("from Role where roleName like %?1%")
//	public List<Role> findRoleByName(String s);
	
//	@Query("from Role where parent.id=?1")
//	public List<Role> findRoleByPid(String  s);
	
//	@Query(value="select * from role_p where DEPT_Name like ?",nativeQuery=true)
//	public List<Role> findRoleByName1(String s);
	
//	public List<Role> findRoleByNameLike(String  s);
	
}
