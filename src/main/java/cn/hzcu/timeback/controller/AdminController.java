package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.entity.User;
import cn.hzcu.timeback.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/admin")
@Api
@CrossOrigin
public class AdminController {
    @Autowired
    private IAdminService AdminService;
//    @PostMapping("/login")
    @RequestMapping("/login")
    @ApiOperation(value = "login")
//    public R<Admin> login(@RequestBody Admin admin){
//    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
//        String password = admin.getPassword();
//        //从数据库中查找用户
//        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Admin::getName,admin.getName());
//        Admin man = AdminService.getOne(queryWrapper);
//        //如果没有找到
//        if(man == null){
//            return R.error("登录失败");
//        }
//        //找到后匹配密码
//        if(!man.getPassword().equals(password)){
//            return R.error("密码错误");
//        }
//        return  R.success(man);
        public void doLogin(@RequestBody Admin admin) {
        System.out.println(admin.getName());
        System.out.println(admin.getPassword());
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token1=new UsernamePasswordToken(admin.getName(),admin.getPassword());
            try {
                subject.login(new UsernamePasswordToken(admin.getName(), admin.getPassword()));
                System.out.println("登录成功!");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                System.out.println("登录失败!");
            }
        }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "please login!";
    }


//        return "login";
//    }

    @RequiresPermissions("admin:view")
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage adminPage = AdminService.page(page);
        return R.success(adminPage);
    }
    @GetMapping()
    @ApiOperation(value = "getById")
    public R<Admin> getAdmin(@RequestParam Integer id){
        return R.success(AdminService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Admin.Add.class) Admin Admin){
        int hashIterations = 2;
        Object salt = "tzk";
        Object credentials = Admin.getPassword();
        String hashAlgorithmName = "MD5";
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        Admin.setPassword(simpleHash.toString());
        AdminService.save(Admin);
        return R.success();
    }

    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateAdmin(@RequestBody @Validated(Admin.Update.class) Admin Admin){
        AdminService.updateById(Admin);
        return R.success();
    }

    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> deleteAdmin(@RequestParam Integer id){
        AdminService.removeById(id);
        return R.success();
    }

}
