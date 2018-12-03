package cn.itcast.mybatis.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.mapper.UserMapper;
import cn.itcast.mybatis.pojo.QueryVo;
import cn.itcast.mybatis.pojo.User;

public class UserMapperTest {

	SqlSessionFactory sqlSessionFactory = null;
    @Before
	public void init() throws Exception{
    	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    	sqlSessionFactory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
	}
    
    @Test
    public void testFindById(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	User user = mapper.findById(30);
//    	[id=30, username=玉面狐狸, sex=0, birthday=Sat Feb 24 00:00:00 CST 2018, address=芭蕉洞]
    	
//    	[id=30, username=null, sex=null, birthday=Sat Feb 24 00:00:00 CST 2018, address=芭蕉洞]
    	System.out.println(user);
    	sqlSession.close();
    }
    @Test
    public void testFindById2(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	Map map = mapper.findById2(30);
    	System.out.println(map);
    	sqlSession.close();
    }
    
    @Test
    public void testFindById3(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	User user = mapper.findById3(30);
    	System.out.println(user);
    	sqlSession.close();
    }
    @Test
    public void testFindByMap(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	Map<String,Object> map = new HashMap<String,Object>();
//    	  select * from user where username like '%张%'   and sex='1' and address='河南郑州'
//    	  select * from user where username like '%${username}%'   and sex=#{sex} and address=#{address}
    	map.put("username", "张");
//    	map.put("sex", "1");
//    	map.put("address", "河南郑州");
    	List<User> users = mapper.findUserByMap(map);
    	System.out.println(users);
    	sqlSession.close();
    }
    
    @Test
    public void testFindByQueryVo(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	
    	
		QueryVo queryVo = new QueryVo();
		
		User user = new User();
		user.setUsername("张");
		queryVo.setUser(user);
		
    	List<User> users = mapper.findUserByQueryVo(queryVo);
    	System.out.println(users);
    	sqlSession.close();
    }
    @Test
    public void testFindCount(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	int count = mapper.findUserCount();
    	System.out.println(count);
    	sqlSession.close();
    }
    
    @Test
    public void testFindListByUser(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	User user = new User();
    	user.setAddress("河南郑州");
    	user.setSex("1");
    	user.setUsername("张");
    	List<User>  list = mapper.findListByUser(user);
    	System.out.println(list);
    	sqlSession.close();
    }
    @Test
    public void testFindListByArray(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	QueryVo queryVo = new QueryVo();
    	int[] ids = {1,29,30};
    	queryVo.setIds(ids);
    	List<User>  list = mapper.findListByArray(queryVo);
    	System.out.println(list);
    	sqlSession.close();
    }
    
    @Test
    public void testUpdateUser(){
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	User user = new User();
//    	30	玉面狐狸	2018-02-24	0	芭蕉洞
    	user.setId(30);
    	user.setUsername("铁扇公主");
//    	birthday=null;
//    	address=null;
//    	sex=null;
    	mapper.updateUser(user);
    	sqlSession.commit();
    	sqlSession.close();
    }
    
}
