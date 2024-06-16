package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.SysPermission;
import cn.hzcu.timeback.service.ISysPermissionService;
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
@RequestMapping("/sysPermission")
@Api
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class SysPermissionController {
    @Autowired
    private ISysPermissionService ISysPermissionService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage sysPermissionPage = ISysPermissionService.page(page);
        return R.success(sysPermissionPage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<SysPermission> updateManager(@RequestParam Integer id){
        return R.success(ISysPermissionService.getById(id));
    }

    @GetMapping("/sysPermissionid")
    @ApiOperation(value = "getBysysPermissionId")
    public R<IPage<SysPermission>> getBysysPermissionId(@RequestParam Integer sysPermissionId) {
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysPermission::getId, sysPermissionId);

        Page<SysPermission> page = new Page<>(1, 10); // 默认查询第一页，每页10条记录
        IPage<SysPermission> sysPermissionPage = ISysPermissionService.page(page, queryWrapper);
        return R.success(sysPermissionPage);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(SysPermission::getPermission, keyword);

        List<SysPermission> searchResult = ISysPermissionService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage sysPermissionPage = ISysPermissionService.page(page,queryWrapper);
        return R.success(sysPermissionPage);
    }


    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(SysPermission.Update.class) SysPermission SysPermission){
        ISysPermissionService.updateById(SysPermission);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(SysPermission.Add.class) SysPermission SysPermission){
        ISysPermissionService.save(SysPermission);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        ISysPermissionService.removeById(id);
        return R.success();
    }

}
