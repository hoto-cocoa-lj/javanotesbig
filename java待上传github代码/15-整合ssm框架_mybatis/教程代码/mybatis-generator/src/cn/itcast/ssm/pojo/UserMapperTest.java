package cn.itcast.ssm.pojo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.ssm.mapper.UserMapper;

public class UserMapperTest {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserMapper userMapper = app.getBean(UserMapper.class);
//		User selectByPrimaryKey = userMapper.selectByPrimaryKey(30);
//		System.out.println(selectByPrimaryKey);
		UserExample example = new UserExample();
		example.createCriteria().andSexEqualTo("1").andAddressLike("%河南%");
		List<User> list = userMapper.selectByExample(example);
		System.out.println(list.size());
	}

}
