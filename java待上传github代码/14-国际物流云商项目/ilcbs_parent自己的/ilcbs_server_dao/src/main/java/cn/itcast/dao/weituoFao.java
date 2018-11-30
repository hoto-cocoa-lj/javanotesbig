package cn.itcast.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.itcast.domain.PackingList;
import cn.itcast.domain.weituo;

public interface weituoFao extends JpaRepository<weituo, String>,JpaSpecificationExecutor<weituo>{
/*	@Query("from PackingList where deptName like %?1%")
	public List<PackingList> findPackingListByName(String s);
	
	@Query("from PackingList where parent.id=?1")
	public List<PackingList> findPackingListByPid(String  s);
	
	@Query(value="select * from dept_p where DEPT_Name like ?",nativeQuery=true)
	public List<PackingList> findPackingListByName1(String s);*/
	
}
