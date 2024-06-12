package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.User;
import cn.hzcu.timeback.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/user")
@Api
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "login")
    @PostMapping("/login")

    public R<User> login(@RequestBody User user){
        String password = user.getPassword();
        //从数据库中查找用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User man = userService.getOne(queryWrapper);
        //如果没有找到
        if(man == null){
            return R.error("登录失败");
        }
        //找到后匹配密码
        if(!man.getPassword().equals(password)){
            return R.error("密码错误");
        }
        return  R.success(man);
    }


    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage userPage = userService.page(page);
        return R.success(userPage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<User> getAdmin(@RequestParam Integer id){
        return R.success(userService.getById(id));
    }
    @PostMapping
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Admin.Add.class) User user){
        userService.save(user);
        return R.success();
    }


    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUsername, keyword);

        List<User> searchResult = userService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage userPage = userService.page(page,queryWrapper);
        return R.success(userPage);
    }

    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateAdmin(@RequestBody @Validated(Admin.Update.class) User user){
        userService.updateById(user);
        return R.success();
    }

    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> deleteAdmin(@RequestParam Integer id){
        userService.removeById(id);
        return R.success();
    }


}
