/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月14日 下午4:53:25
 */
package com.ruomm.springcloud.authserver.shiro.realm;

import com.ruomm.springcloud.authserver.shiro.model.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserAuthorizingRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		// 实际中可能会像上面注释的那样从数据库取得
		if (null != currentUsername && "papio".equals(currentUsername)) {
			// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
			simpleAuthorInfo.addRole("admin");
			// 添加权限
			simpleAuthorInfo.addStringPermission("admin:manage");
			System.out.println("已为用户[papio]赋予了[admin]角色和[admin:manage]权限");
			return simpleAuthorInfo;
		}
		else if (null != currentUsername && "big".equals(currentUsername)) {
			System.out.println("当前用户[big]无授权");
			return simpleAuthorInfo;
		}
		// 若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
		// 详见applicationContext.xml中的<bean id="shiroFilter">的配置
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("权限认证方法：doGetAuthenticationInfo()");
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		// 两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(
				"验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		User user = new User();
		user.setUserId(token.getUsername());
		user.setToken(String.valueOf(token.getPassword()));
		return new SimpleAuthenticationInfo(user, user.getToken(), user.getUserId());
	}

}
