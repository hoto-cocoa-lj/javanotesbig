package cn.me.web.shiro;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import cn.me.domain.Module;
import cn.me.domain.Role;
import cn.me.domain.User;
import cn.me.service.UserService;

//继承AuthorizingRealm既可以认证又可以授权，下面继承AuthenticatingRealm只能认证
//MyAuthRealm是单实例(因为默认值singtone)
public class MyAuthRealm extends AuthorizingRealm {

	@Autowired
	private UserService us;


	/*  有3种链接可以触发此方法：
		1、<shiro:hasPermission name="item:update">
		2、xml文件里的perms[".."]管理的链接
		3、controller层加了@RequiresPermissions(value = "..")的action对应的链接
	当展示一个jsp页面时，页面中如果遇到<shiro:hasPermissionname="item:update">，shiro调用realm获取数据库中的权限信息，看item:update是否在权限数据中存在，如果不存在就拒绝访问，如果存在就授权通过。查询数据库次数较多，可以考虑放在缓存里 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User) pc.getPrimaryPrincipal();
		Set<Role> roles = user.getRoles();
		for (Role r : roles) {
			Set<Module> modules = r.getModules();
			for (Module m : modules) {
				// 查看源码发现addStringPermission把数据添加到hashset，所以一定不会出现重复值
				info.addStringPermission(m.getCpermission());
			}
		}
		//System.out.println(this + "\tinfo=" + info);
		// cn.me.web.shiro.MyAuthRealm@6c0a24e5
		// info=org.apache.shiro.authz.SimpleAuthorizationInfo@7276823b
		// cn.me.web.shiro.MyAuthRealm@6c0a24e5
		// info=org.apache.shiro.authz.SimpleAuthorizationInfo@6bb3b80f
		//System.out.println("info.getStringPermissions=" + info.getStringPermissions());
		// info.getStringPermissions=[请假单管理, 发票管理, 查看部门
		return info;
	}

	// 拿到token的部分数据，获取相应的认证数据并返回，之后使用credentialsMatcher进行验证
	// 每次登陆时触发此方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) token1;
		final String username = token.getUsername();
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("userName").as(String.class), username);
			}
		};
		List<User> find = us.find(spec);
		System.out.println("find=" + find);
		AuthenticationInfo info = null;
		if (find != null && find.size() > 0) {
			User user = find.get(0);

			// principal指主要对象
			Object principal = user;
			// credentials指凭证，这里是密码
			Object credentials = user.getPassword();
			// realmName指realm的Name，可能有多个，需要指明
			String realmName = getName();
			info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		}
		//System.out.println(this + "\tinfo=" + info + "\ttoken=" + token);
		// cn.me.web.shiro.MyAuthRealm@6c0a24e5
		// info=Use对象
		// token=org.apache.shiro.authc.UsernamePasswordToken - a, rememberMe=false
		return info;
	}
}
/*
 * public class MyAuthRealm extends AuthenticatingRealm{
 * 
 * @Override protected AuthenticationInfo
 * doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken) throws
 * AuthenticationException { // TODO Auto-generated method stub return null; }
 * 
 * }
 */
