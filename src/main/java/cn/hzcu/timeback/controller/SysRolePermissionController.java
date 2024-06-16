package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.SysRolePermission;
import cn.hzcu.timeback.service.ISysRolePermissionService;
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
@RequestMapping("/sysRolePermission")
@Api
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class SysRolePermissionController {
    @Autowired
    private ISysRolePermissionService ISysRolePermissionService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage sysRolePermissionPage = ISysRolePermissionService.page(page);
        return R.success(sysRolePermissionPage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<SysRolePermission> updateManager(@RequestParam Integer id){
        return R.success(ISysRolePermissionService.getById(id));
    }




    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(SysRolePermission.Update.class) SysRolePermission SysRolePermission){
        ISysRolePermissionService.updateById(SysRolePermission);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(SysRolePermission.Add.class) SysRolePermission SysRolePermission){
        ISysRolePermissionService.save(SysRolePermission);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        ISysRolePermissionService.removeById(id);
        return R.success();
    }

}
