package com.pyg.manager.controller;

import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@RequestMapping("/me")
	public void findLoginUser() {
		// 3种方法获取当前登陆的username，如果没有登陆且此访问没被拦截，
		//方法1返回anonymouse，方法2返回null，方法3报错
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(name);
		String name3 = r.getRemoteUser();
		System.out.println(name3);
		String name2 = AssertionHolder.getAssertion().getPrincipal().getName();
		System.out.println(name2);
	}
}