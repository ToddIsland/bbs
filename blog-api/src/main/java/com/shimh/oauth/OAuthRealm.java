package com.shimh.oauth;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shimh.common.constant.Base;
import com.shimh.entity.User;
import com.shimh.entity.UserStatus;
import com.shimh.service.UserService;

/**
 * 自定义shiroRealm
 *
 */
public class OAuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * @description 用于授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String account = (String) principals.getPrimaryPrincipal();
        User user = userService.getUserByAccount(account);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();

        //简单处理   只有admin一个角色
        if (user.getAdmin()) {
            roles.add(Base.ROLE_ADMIN);
        }

        authorizationInfo.setRoles(roles);

        return authorizationInfo;
    }

    /**
     * @description 用于认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String account = (String) token.getPrincipal();

        User user = userService.getUserByAccount(account);

        if (user == null) {
            System.out.println("没找到账号");
            throw new UnknownAccountException();//没找到帐号
        }

        if (UserStatus.blocked.equals(user.getStatus())) {
            System.out.println("账号锁定");
            throw new LockedAccountException(); //帐号锁定
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getAccount(),
                user.getPassword(),//加密之后的
                ByteSource.Util.bytes(user.getSalt()),
                getName()
        );

        return authenticationInfo;
    }

}
