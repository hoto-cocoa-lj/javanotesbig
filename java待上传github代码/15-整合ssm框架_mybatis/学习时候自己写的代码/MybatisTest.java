package jdbc;
//对应user.xml，第一天增删改查的基础代码
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.itcast.mybatis.po.User;

public class MybatisTest {	
	private SqlSessionFactory sqlSessionFactory=null;
	
	@Before
	public void init() throws IOException{
		SqlSessionFactoryBuilder f = new SqlSessionFactoryBuilder();
		InputStream r = Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory = f.build(r);	
	}
	
	@Test
	//这个方法使用了dao层的接口，接口由使用mybatis的xml文件的代理对象实现
	//这个方法下面的方法直接使用sqlSession进行crud操作
	public void t1() throws Exception {
		SqlSession s = sqlSessionFactory.openSession();
		UserMapper m = s.getMapper(UserMapper.class);
		User l = m.findUserById(28);
		System.out.println(l);
		s.close();
	}

	@Test
	public void test1() throws IOException{	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User one = sqlSession.selectOne("findUserById", 1);
		System.out.println(one);
		sqlSession.close();
	}
	
	@Test
	public void test2() throws IOException{	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> l = sqlSession.selectList("findUserByName2","%张%");
		System.out.println(l);
		sqlSession.close();
	}
	@Test
	public void test3() throws IOException{	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> l = sqlSession.selectList("findUserByName2","张");
		System.out.println(l);
		sqlSession.close();
	}
	@Test
	public void test4() throws IOException{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User u = new User();
		u.setUsername("李1");
		u.setBirthday(new Date());
		u.setSex("妖");
		u.setAddress("address");
		sqlSession.insert("insertUser",u);
		sqlSession.commit();
		System.out.println(u);
		sqlSession.close();
	}
	@Test
	public void test5() throws IOException{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User u = sqlSession.selectOne("findUserById", 29);
		u.setUsername("李2");
		u.setBirthday(new Date());
		u.setSex("a");
		u.setAddress("address2");
		sqlSession.update("updateUser", u);
		sqlSession.commit();
		System.out.println(u);
		sqlSession.close();
	}
	@Test
	public void test6() throws IOException{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("deleteUser",28);
		sqlSession.commit();
		sqlSession.close();
	}
}
