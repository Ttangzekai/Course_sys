package cn.hzcu.timeback.config;

import cn.hzcu.timeback.entity.SysPermission;
import cn.hzcu.timeback.entity.SysRole;
import cn.hzcu.timeback.service.*;
import cn.hzcu.timeback.entity.Admin;

import io.swagger.models.auth.In;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import cn.hzcu.timeback.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private IAdminService adminService;
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysRolePermissionService sysRolePermissionService;
    @Resource
    private ISysPermissionService sysPermissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Admin adminInfo = (Admin) principals.getPrimaryPrincipal();


        List<SysUserRole> roleList = sysUserRoleService.getByUid(adminInfo.getId());

        for (SysUserRole userRole : roleList) {
            SysRole role = sysRoleService.getById(userRole.getRole_id());
            info.addRole(role.getRole());
            List<Integer> permissionIds = sysRolePermissionService.findPermissionIdsByRoleId(role.getId());
            for (Integer permissionId : permissionIds) {
                SysPermission permission = sysPermissionService.getById(permissionId);
                info.addStringPermission(permission.getPermission());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号
        System.out.println("正在执行认证");
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //Shiro有时间间隔机制，2分钟内不会重复执行该方法
        //获取用户信息
        Admin adminInfo = adminService.getByName(username);
        System.out.println(ByteSource.Util.bytes("tzk"));
        if (adminInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                adminInfo,
                adminInfo.getPassword(),
                ByteSource.Util.bytes("tzk"),
                getName()
        );
        return info;
    }

}