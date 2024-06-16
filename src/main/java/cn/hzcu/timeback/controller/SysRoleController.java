package cn.hzcu.timeback.controller;



import cn.hzcu.timeback.entity.SysRole;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.ISysRoleService;
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
@RequestMapping("/sysrole")
@Api
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class SysRoleController {
    @Autowired
    private ISysRoleService ISysRoleService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage sysrolePage = ISysRoleService.page(page);
        return R.success(sysrolePage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<SysRole> updateManager(@RequestParam Integer id){
        return R.success(ISysRoleService.getById(id));
    }

    @GetMapping("/sysroleid")
    @ApiOperation(value = "getBysysroleId")
    public R<IPage<SysRole>> getBysysroleId(@RequestParam Integer sysroleId) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRole::getId, sysroleId);

        Page<SysRole> page = new Page<>(1, 10); // 默认查询第一页，每页10条记录
        IPage<SysRole> sysrolePage = ISysRoleService.page(page, queryWrapper);
        return R.success(sysrolePage);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(SysRole::getRole, keyword);

        List<SysRole> searchResult = ISysRoleService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage sysrolePage = ISysRoleService.page(page,queryWrapper);
        return R.success(sysrolePage);
    }


    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(SysRole.Update.class) SysRole SysRole){
        ISysRoleService.updateById(SysRole);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(SysRole.Add.class) SysRole SysRole){
        ISysRoleService.save(SysRole);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        ISysRoleService.removeById(id);
        return R.success();
    }

}
