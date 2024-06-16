package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.SysUserRole;
import cn.hzcu.timeback.service.ISysUserRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/sysUserRole")
@Api
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class SysUserRoleController {
    @Autowired
    private ISysUserRoleService ISysUserRoleService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage sysUserRolePermissionPage = ISysUserRoleService.page(page);
        return R.success(sysUserRolePermissionPage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<SysUserRole> updateManager(@RequestParam Integer id){
        return R.success(ISysUserRoleService.getById(id));
    }




    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(SysUserRole.Update.class) SysUserRole SysUserRolePermission){
        ISysUserRoleService.updateById(SysUserRolePermission);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(SysUserRole.Add.class) SysUserRole SysUserRolePermission){
        ISysUserRoleService.save(SysUserRolePermission);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        ISysUserRoleService.removeById(id);
        return R.success();
    }

}
