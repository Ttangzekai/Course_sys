package cn.hzcu.timeback.service;

import cn.hzcu.timeback.entity.SysRole;
import cn.hzcu.timeback.entity.SysRolePermission;
import cn.hzcu.timeback.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
    List<Integer> findPermissionIdsByRoleId(Integer roleId);
}
