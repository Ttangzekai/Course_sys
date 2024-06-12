package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.Category;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.ICategoryService;
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
@RequestMapping("/category")
@Api
@CrossOrigin
public class CategoryController {
    @Autowired
    private ICategoryService CategoryService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage catePage = CategoryService.page(page);
        return R.success(catePage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<Category> updateManager(@RequestParam Integer id){
        return R.success(CategoryService.getById(id));
    }

    @GetMapping("/categoryid")
    @ApiOperation(value = "getByCateId")
    public R<IPage<Category>> getByCateId(@RequestParam Integer cateId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId, cateId);

        Page<Category> page = new Page<>(1, 10); // 默认查询第一页，每页10条记录
        IPage<Category> catePage = CategoryService.page(page, queryWrapper);
        return R.success(catePage);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Category::getCategoryname, keyword);

        List<Category> searchResult = CategoryService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage catePage = CategoryService.page(page,queryWrapper);
        return R.success(catePage);
    }


    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(Category.Update.class) Category category){
        CategoryService.updateById(category);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Category.Add.class) Category cate){
        CategoryService.save(cate);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        CategoryService.removeById(id);
        return R.success();
    }

}
