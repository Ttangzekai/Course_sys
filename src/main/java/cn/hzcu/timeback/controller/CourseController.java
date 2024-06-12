package cn.hzcu.timeback.controller;



import cn.hzcu.timeback.entity.Course;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.ICourseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/course")
@Api
@CrossOrigin
public class CourseController {
    @Autowired
    private ICourseService CourseService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage coursePage = CourseService.page(page);
        return R.success(coursePage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<Course> updateManager(@RequestParam Integer id){
        return R.success(CourseService.getById(id));
    }

    @GetMapping("/courseid")
    @ApiOperation(value = "getByCourseId")
    public R<IPage<Course>> getByCourseId(@RequestParam Integer courseId) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getId, courseId);

        Page<Course> page = new Page<>(1, 10); // 默认查询第一页，每页10条记录
        IPage<Course> coursePage = CourseService.page(page, queryWrapper);
        return R.success(coursePage);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Course::getCoursename, keyword);

        List<Course> searchResult = CourseService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage coursePage = CourseService.page(page,queryWrapper);
        return R.success(coursePage);
    }


    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(Course.Update.class) Course course){
        CourseService.updateById(course);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Course.Add.class) Course course){
        CourseService.save(course);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        CourseService.removeById(id);
        return R.success();
    }

}
