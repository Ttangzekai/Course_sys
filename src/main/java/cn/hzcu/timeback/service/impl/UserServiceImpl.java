package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.User;
import cn.hzcu.timeback.mapper.UserMapper;
import cn.hzcu.timeback.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
