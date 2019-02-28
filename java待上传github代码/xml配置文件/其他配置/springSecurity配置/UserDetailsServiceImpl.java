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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("经过了UserDetailsServiceImpl");
		// 构建角色列表
		List<GrantedAuthority> grantAuths = new ArrayList();
		grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		// 得到商家对象
		TbSeller seller = sellerService.findOne(username);
		if (seller != null && seller.getStatus().equals("1")) {
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