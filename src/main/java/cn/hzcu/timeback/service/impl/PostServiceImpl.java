package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.mapper.PostMapper;
import cn.hzcu.timeback.service.IPostService;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
