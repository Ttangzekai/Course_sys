package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.SysRole;
import cn.hzcu.timeback.entity.SysUserRole;
import cn.hzcu.timeback.mapper.SysRoleMapper;
import cn.hzcu.timeback.mapper.SysRolePermissionMapper;
import cn.hzcu.timeback.mapper.SysUserRoleMapper;
import cn.hzcu.timeback.service.ISysRolePermissionService;
import cn.hzcu.timeback.service.ISysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hzcu.timeback.entity.SysRolePermission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Override
    public List<Integer> findPermissionIdsByRoleId(Integer roleId) {
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<SysRolePermission> rolePermissions = sysRolePermissionMapper.selectList(queryWrapper);
        List<Integer> permissionIds = new ArrayList<>();
        for (SysRolePermission rolePermission : rolePermissions) {
            permissionIds.add(rolePermission.getPermission_id());
        }
        return permissionIds;
    }
}
