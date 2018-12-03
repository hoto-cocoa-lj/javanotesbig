package cn.itcast.mybatis.mapper;

import java.util.List;
import java.util.Map;

import cn.itcast.mybatis.pojo.QueryVo;
import cn.itcast.mybatis.pojo.User;

public interface UserMapper {
    
	public User findById(int id);
	
	public Map findById2(int id);
	
	public User findById3(int id);
	
	public void insertUser(User user);
	
	public List<User> findUserByMap(Map<String, Object> map);
	
	public List<User> findUserByQueryVo(QueryVo queryVo);
	
	public List<User> findListByUser(User user);
	
	
	public int findUserCount();
	
	
	public List<User> findListByArray(QueryVo queryVo);

	
	public void updateUser(User user);
}
