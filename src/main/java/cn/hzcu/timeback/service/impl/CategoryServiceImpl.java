package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.mapper.CategoryMapper;
import cn.hzcu.timeback.mapper.PostMapper;
import cn.hzcu.timeback.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hzcu.timeback.entity.Category;
import cn.hzcu.timeback.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
