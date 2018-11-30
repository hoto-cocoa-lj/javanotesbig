package cn.itcast.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.itcast.client.domain.UserClient;

public interface UserClientDao extends JpaRepository<UserClient, String> {
	//啊
	UserClient findByUserNameAndPassword(String userName,String password);
//123
	UserClient findByEmail(String email);
}
