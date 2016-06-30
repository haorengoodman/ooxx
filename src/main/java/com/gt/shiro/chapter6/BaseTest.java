package com.gt.shiro.chapter6;

import com.gt.entity.Permission;
import com.gt.entity.Role;
import com.gt.entity.User;
import com.gt.service.PermissionService;
import com.gt.service.RoleService;
import com.gt.service.UserService;
import com.gt.service.impl.PermissionServiceImpl;
import com.gt.service.impl.RoleServiceImpl;
import com.gt.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public abstract class BaseTest {

    protected PermissionService permissionService = new PermissionServiceImpl();
    protected RoleService roleService = new RoleServiceImpl();
    protected UserService userService = new UserServiceImpl();

    protected String password = "123";

    protected Permission p1;
    protected Permission p2;
    protected Permission p3;
    protected Role r1;
    protected Role r2;
    protected User u1;
    protected User u2;
    protected User u3;
    protected User u4;

    @Before
    public void setUp() {
        /*
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_permissions");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles_permissions");
        */

        //1、新增权限
        p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
        p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
        p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
        permissionService.insert(p1);
        permissionService.insert(p2);
        permissionService.insert(p3);

        //2、新增角色 ,同时关联权限
        r1 = new Role("admin", "管理员", Boolean.TRUE);
        r2 = new Role("user", "用户管理员", Boolean.TRUE);
        Set<Permission> permissionsFor1 = new HashSet<>();
        permissionsFor1.add(p1);
        permissionsFor1.add(p2);
        permissionsFor1.add(p3);

        Set<Permission> permissionsFor2 = new HashSet<>();
        permissionsFor2.add(p1);
        permissionsFor2.add(p2);

        r1.setPermissions(permissionsFor1);
        r2.setPermissions(permissionsFor2);
        roleService.insert(r1);
        roleService.insert(r2);

        /*
        //3、关联角色-权限
        roleService.correlationPermissions(r1.getId(), p1.getId());
        roleService.correlationPermissions(r1.getId(), p2.getId());
        roleService.correlationPermissions(r1.getId(), p3.getId());

        roleService.correlationPermissions(r2.getId(), p1.getId());
        roleService.correlationPermissions(r2.getId(), p2.getId());
        */

        //4、新增用户 用户u1 关联 角色对象r1
        u1 = new User("zhang", password);
        u2 = new User("li", password);
        u3 = new User("wu", password);
        u4 = new User("wang", password);
        u4.setLocked(Boolean.TRUE);

        Set<Role> roleSet = u1.getRoles();
        if(roleSet == null){
            roleSet = new HashSet<>();
        }
        roleSet.add(r1);
        u1.setRoles(roleSet);

        userService.insert(u1);
        userService.insert(u2);
        userService.insert(u3);
        userService.insert(u4);

        /*
        //5、关联用户-角色
        userService.correlationRoles(u1.getId(), r1.getId());
        */

    }




    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    protected void login(String configFile, String username, String password) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }

}
