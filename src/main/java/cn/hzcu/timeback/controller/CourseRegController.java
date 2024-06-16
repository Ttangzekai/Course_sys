package cn.hzcu.timeback.controller;



import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.Course;
import cn.hzcu.timeback.entity.CourseRegistration;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.ICourseRegService;
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
@RequestMapping("/coursereg")
@Api
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class CourseRegController {
    @Autowired
    private ICourseRegService ICourseRegService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage coursePage = ICourseRegService.page(page);
        return R.success(coursePage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<CourseRegistration> updateManager(@RequestParam Integer id){
        return R.success(ICourseRegService.getById(id));
    }

    @GetMapping("/courseid")
    @ApiOperation(value = "getByCourseId")
    public R<IPage<CourseRegistration>> getByCourseId(@RequestParam Integer courseId) {
        LambdaQueryWrapper<CourseRegistration> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseRegistration::getId, courseId);

        Page<CourseRegistration> page = new Page<>(1, 10); // 默认查询第一页，每页10条记录
        IPage<CourseRegistration> coursePage = ICourseRegService.page(page, queryWrapper);
        return R.success(coursePage);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<CourseRegistration> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(CourseRegistration::getCourseid, keyword);

        List<CourseRegistration> searchResult = ICourseRegService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage coursePage = ICourseRegService.page(page,queryWrapper);
        return R.success(coursePage);
    }


    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(CourseRegistration.Update.class) CourseRegistration courseRegistration){
        ICourseRegService.updateById(courseRegistration);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(CourseRegistration.Add.class) CourseRegistration courseRegistration){
        ICourseRegService.save(courseRegistration);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        ICourseRegService.removeById(id);
        return R.success();
    }

}
