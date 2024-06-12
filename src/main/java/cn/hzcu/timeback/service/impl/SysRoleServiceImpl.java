package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.SysRole;
import cn.hzcu.timeback.entity.SysUserRole;
import cn.hzcu.timeback.mapper.SysRoleMapper;
import cn.hzcu.timeback.mapper.SysUserRoleMapper;
import cn.hzcu.timeback.service.ISysRoleService;
import cn.hzcu.timeback.service.ISysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}
