//package com.gt.realm;
//
//import com.gt.entity.Permission;
//import com.gt.entity.Role;
//import com.gt.entity.User;
//import com.gt.service.UserService;
//import com.gt.service.impl.UserServiceImpl;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class UserRealm extends AuthorizingRealm {
//    private UserService userService = new UserServiceImpl();
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = (String)principals.getPrimaryPrincipal();
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Set<Role> roleSet = userService.findRoles(username);
//        Set<String> roleNameSet = new HashSet<>();
//        Set<String> stringPermissionSet = new HashSet<>();
//        for (Role role:roleSet){
//            roleNameSet.add(role.getRole());
//            Set<Permission> permissionSet = role.getPermissions();
//            for(Permission p : permissionSet){
//                stringPermissionSet.add(p.getPermission());
//            }
//        }
//        authorizationInfo.setRoles(roleNameSet);
//        authorizationInfo.setStringPermissions(stringPermissionSet);
//        return authorizationInfo;
//    }
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String username = (String)token.getPrincipal();
//        User user = userService.findByUsername(username);
//        if(user == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }
//        if(Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
//        );
//        return authenticationInfo;
//    }
//}