package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.mapper.AdminMapper;
import cn.hzcu.timeback.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin getByName(String name){
        return adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getName, name));
    }


}
