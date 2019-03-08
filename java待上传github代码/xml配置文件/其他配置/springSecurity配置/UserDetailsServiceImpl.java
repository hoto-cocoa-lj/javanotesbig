package com.pyg.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.me.pojo.TbSeller;
import cn.me.sellergoods.service.SellerService;

/**
 * 认证类
 * 
 * @author Administrator
 *
 */
@Component("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Reference
	private SellerService sellerService;

	//重点：loadUserByUsername使用了两种不同的权限，当username是x且密码正确时拥有
	//ROLE_SELLER1权限，其他账号拥有ROLE_SELLER权限，这样可以和intercept-url一起使用
	//来实现权限细粒度控制
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("经过了UserDetailsServiceImpl");
		// 构建角色列表
		List<GrantedAuthority> grantAuths = new ArrayList();
		grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		List<GrantedAuthority> grantAuths1 = new ArrayList();
		grantAuths1.add(new SimpleGrantedAuthority("ROLE_SELLER1"));
		// 得到商家对象
		TbSeller seller = sellerService.findOne(username);
		if (seller != null && seller.getStatus().equals("1")) {
			if (username.equals("x")) {
				return new User(username, seller.getPassword(), grantAuths1);
			}
			User user = new User(username, seller.getPassword(), grantAuths);
			return user;
		}
		return null;
	}
}

/**
这个是注册账号的代码，需要对密码进行加密
	@Override
	public void add(TbSeller seller) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(seller.getPassword());
		System.out.println(password);
		seller.setPassword(password);
		seller.setStatus("0");
		seller.setCreateTime(new Date());
		sellerMapper.insert(seller);
	}
 */