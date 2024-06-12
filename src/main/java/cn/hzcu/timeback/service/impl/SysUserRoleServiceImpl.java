package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.mapper.PostMapper;
import cn.hzcu.timeback.service.IPostService;
import cn.hzcu.timeback.service.ISysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hzcu.timeback.mapper.SysUserRoleMapper;
import cn.hzcu.timeback.entity.SysUserRole;

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
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public List<SysUserRole> getByUid(Integer userId) {
        // 在这里编写根据用户ID查询用户角色信息的逻辑
        // 例如：
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return sysUserRoleMapper.selectList(queryWrapper);
    }
}
