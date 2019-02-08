package cn.me.web.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

public class MyPasswordMatcher extends SimpleCredentialsMatcher {

	// 比较token(令牌,包含了登陆信息)和info
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token1, AuthenticationInfo info1) {
		// System.out.println("调用了密码比较器MyPasswordMatcher的比较");
		UsernamePasswordToken token = (UsernamePasswordToken) token1;
		// Object credentials = token1.getCredentials();
		// Object principal = token.getPrincipal();
		// System.out.println("credentials=" + credentials + "\tprincipal=" +
		// principal);
		// credentials=[C@5346f3ae principal=a

		// 获取令牌里的密码
		String pw1 = new String(token.getPassword());
		SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) info1;
		// 获取info里的密码
		String pw2 = (String) info.getCredentials();
		Md5Hash md5Hash = new Md5Hash(pw1, token.getUsername(), 2);
		pw1 = md5Hash.toString();
		// 返回比较的结果
		return pw1.equals(pw2);
	}

}
