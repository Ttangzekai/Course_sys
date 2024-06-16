package cn.hzcu.timeback.service.impl;

import cn.hzcu.timeback.entity.Course;
import cn.hzcu.timeback.entity.CourseRegistration;
import cn.hzcu.timeback.mapper.CourseMapper;
import cn.hzcu.timeback.service.ICourseRegService;
import cn.hzcu.timeback.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cn.hzcu.timeback.mapper.CourseRegMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@Service
public class CourseRegServiceImpl extends ServiceImpl<CourseRegMapper, CourseRegistration> implements ICourseRegService {

}
