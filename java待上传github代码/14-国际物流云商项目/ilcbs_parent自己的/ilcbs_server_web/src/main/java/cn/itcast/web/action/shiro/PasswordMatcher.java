package cn.itcast.web.action.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordMatcher extends SimpleCredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		System.out.println("密码比较器");
		UsernamePasswordToken utoken=(UsernamePasswordToken)token;
		String p1 = new String(utoken.getPassword());		
		Md5Hash hash = new Md5Hash(p1, utoken.getUsername(), 2);
		System.out.println("hash="+hash.toString());
		return equals(hash.toString(),info.getCredentials());
	}
}
