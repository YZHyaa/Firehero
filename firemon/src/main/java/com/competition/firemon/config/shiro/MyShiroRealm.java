package com.competition.firemon.config.shiro;

import com.competition.firemon.bean.User;
import com.competition.firemon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description 自定义权限匹配和账号密码匹配
 * @Author iterge
 * @Date 2019/6/12 14:02
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
//    @Autowired
//    private SysRoleService sysRoleService;
//    @Autowired
//    private SysPermissionService sysPermissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
//        System.out.println("权限认证");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        User user = (User) principal.getPrimaryPrincipal();
//        System.out.println("**********"+user.toString());
//        try {
//            List<SysRole> roles = sysRoleService.selectByUid(user.getUid());
//            for (SysRole enum : roles) {
//                authorizationInfo.addRole(enum.getRolename());
//            }
//            List<SysPermission> permissions = sysPermissionService.selectByUid(user.getUid());
//            for(SysPermission permission : permissions){
//                authorizationInfo.addStringPermission(permission.getPername());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return authorizationInfo;

        //1、从principalCollection中获取用户登录信息
        Object principals = principal.getPrimaryPrincipal();//根据多realm的加载，有先后顺序返回principals

        //2、利用登录用户信息来获取角色或权限(可能查询数据库)
        Set<String> roles = new HashSet<>();
        roles.add("user");//默认存放user角色

//        if("admin".equals(principals)){
//            roles.add("admin");
//        }

        //3、创建SimpleAuthorizationInfo，并设置其roles属性
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);

        //4、返回SimpleAuthorizationInfo对象
        return simpleAuthorizationInfo;
    }

    //认证
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户账号和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.login(token.getUsername(), String.valueOf(token.getPassword()));
        //待修改,存入session中
        SecurityUtils.getSubject().getSession().setAttribute("profile",user);

        if(user == null)return null;

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,token.getCredentials(),getName());

        return info;
    }
}
