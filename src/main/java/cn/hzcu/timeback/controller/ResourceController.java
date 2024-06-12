package cn.hzcu.timeback.controller;



import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.Resource;
import cn.hzcu.timeback.service.IResourceService;
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
@RequestMapping("/resource")
@Api
@CrossOrigin
public class ResourceController {
    @Autowired
    private IResourceService ResourceService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage resoursePage = ResourceService.page(page);
        return R.success(resoursePage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<Resource> updateManager(@RequestParam Integer id){
        return R.success(ResourceService.getById(id));
    }

    @GetMapping("/resourceid")
    @ApiOperation(value = "getByResourseId")
    public R<IPage<Resource>> getByResourseId(@RequestParam Integer resourceId) {
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resource::getId, resourceId);

        Page<Resource> page = new Page<>(1, 10); // 默认查询第一页，每页10条记录
        IPage<Resource> resourcePage = ResourceService.page(page, queryWrapper);
        return R.success(resourcePage);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Resource::getName, keyword);

        List<Resource> searchResult = ResourceService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage resourcePage = ResourceService.page(page,queryWrapper);
        return R.success(resourcePage);
    }


    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(Resource.Update.class) Resource resource){
        ResourceService.updateById(resource);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Resource.Add.class) Resource resourse){
        ResourceService.save(resourse);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        ResourceService.removeById(id);
        return R.success();
    }

}
