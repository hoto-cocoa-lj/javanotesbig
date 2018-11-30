package cn.itcast.web.action.shiro;

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
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.Module;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class AuthRealm extends AuthorizingRealm{
@Autowired
private UserService us;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("授权");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addStringPermission("系统首页");
		User user=(User)arg0.getPrimaryPrincipal();
		Set<Role> roles = user.getRoles();
		for(Role role:roles){
			Set<Module> modules = role.getModules();
			for(Module module:modules){
				info.addStringPermission(module.getCpermission());
			}
		}
		Set<String> sos = info.getStringPermissions();
		System.out.println("---授权的SOS="+sos);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("认证");
		System.out.println("authrealm arg0="+arg0);
		UsernamePasswordToken up=(UsernamePasswordToken)arg0;
		final String ss=up.getUsername();
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("userName").as(String.class), ss);
			}
		};
		List<User> lu=us.find(spec);
		System.out.println(ss+"\tauthrealm lu="+lu);
		if(lu.size()>0){
			User u1 = lu.get(0);
			return new SimpleAuthenticationInfo(u1,u1.getPassword(), getName());
		}
		return null;
	}

}
