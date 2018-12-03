package cn.itcast.mybatis.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.itcast.mybatis.dao.UserDao;
import cn.itcast.mybatis.pojo.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	
	
	@Override
	public User findById(int id) {
		 SqlSession sqlSession = this.getSqlSession();
		 User user = sqlSession.selectOne("findById", id);
//		 sqlSession.close(); //现在由spring容器管理
		 return user;
	}

	@Override
	public void insertUser(User user) {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("insertUser", user);
//		sqlSession.commit();//现在由spring容器管理
//		sqlSession.close();//现在由spring容器管理
	}

}
