package com.shimh.controller;

import javax.servlet.http.HttpServletRequest;

import com.shimh.common.annotation.LogAnnotation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.User;
import com.shimh.oauth.OAuthSessionManager;
import com.shimh.service.UserService;


/**
 * 登录
 *
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @LogAnnotation(module = "登录", operation = "登录")
    public Result login(@RequestBody User user) {
        Result r = new Result();
        executeLogin(user.getAccount(), user.getPassword(), r);
        return r;
    }

    @PostMapping("/register")
    //@RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "注册", operation = "注册")
    public Result register(@RequestBody User user) {

        Result r = new Result();

        User temp = userService.getUserByAccount(user.getAccount());
        if (null != temp) {
            r.setResultCode(ResultCode.USER_HAS_EXISTED);
            return r;
        }

        String account = user.getAccount();
        String password = user.getPassword();//这一步的密码没有加密

        Long userId = userService.saveUser(user);//saveUser中会对密码加密，存储到数据库中

        if (userId > 0) {
            executeLogin(account, password, r);//密码还是明文
        } else {
            r.setResultCode(ResultCode.USER_Register_ERROR);
        }
        return r;
    }


    private void executeLogin(String account, String password, Result r) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);

        try {
            subject.login(token);
            System.out.println("登录成功");
            User currentUser = userService.getUserByAccount(account);
            subject.getSession().setAttribute(Base.CURRENT_USER, currentUser);

            r.setResultCode(ResultCode.SUCCESS);
            r.simple().put(OAuthSessionManager.OAUTH_TOKEN, subject.getSession().getId());
            //map结构

        } catch (UnknownAccountException e) {
            r.setResultCode(ResultCode.USER_NOT_EXIST);
        } catch (LockedAccountException e) {
            r.setResultCode(ResultCode.USER_ACCOUNT_FORBIDDEN);
        } catch (AuthenticationException e) {
            r.setResultCode(ResultCode.USER_LOGIN_ERROR);
        } catch (Exception e) {
            r.setResultCode(ResultCode.ERROR);
        }

    }

    @RequestMapping(value = "/handleLogin")
    public Result handleLogin(HttpServletRequest request) {
        String id = request.getHeader(OAuthSessionManager.OAUTH_TOKEN);
        System.out.println("超时登录。。。:" + id);
        return Result.error(ResultCode.SESSION_TIME_OUT);
    }


    @GetMapping("/logout")
    @LogAnnotation(module = "退出", operation = "退出")
    public Result logout() {

        Result r = new Result();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }

    @PostMapping("/change")
    @LogAnnotation(module="用户",operation="修改密码")
    public Result changeUserPassword(@RequestBody User user){
        Result r = new Result();

        User olduser = userService.getUserByAccount(user.getAccount());

        userService.deleteUserById(olduser.getId());

        r = register(user);

        String account = user.getAccount();
        String password = user.getPassword();


        System.out.println("account:"+account);
        System.out.println("password:"+password);

        return r;
    }
}
