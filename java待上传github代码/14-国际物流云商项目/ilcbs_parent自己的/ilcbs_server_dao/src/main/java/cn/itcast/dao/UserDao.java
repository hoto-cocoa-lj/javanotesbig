package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.User;

public interface UserDao extends JpaRepository<User, String>,JpaSpecificationExecutor<User>{
	@Query("from User where userName like %?1%")
	public List<User> findUserByName(String s);
	
//	@Query("from User where parent.id=?1")
//	public List<User> findUserByPid(String  s);
	
//	@Query(value="select * from user_p where DEPT_Name like ?",nativeQuery=true)
//	public List<User> findUserByName1(String s);
	
	public List<User> findUserByUserNameLike(String  s);
	
}
