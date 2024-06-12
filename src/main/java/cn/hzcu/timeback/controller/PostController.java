package cn.hzcu.timeback.controller;


import cn.hzcu.timeback.entity.Admin;
import cn.hzcu.timeback.entity.Post;
import cn.hzcu.timeback.entity.R;
import cn.hzcu.timeback.service.IPostService;
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
@RequestMapping("/post")
@Api
@CrossOrigin
public class PostController {
    @Autowired
    private IPostService postService;
    @GetMapping("/list")
    @ApiOperation(value = "list")
    public R<IPage> list(@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size){
        Page page = new Page(current,size);
        IPage postPage = postService.page(page);
        return R.success(postPage);
    }

    @GetMapping("/ispasslist")
    @ApiOperation(value = "list")
    public R<IPage> list1(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") int size) {
        Page<Post> page = new Page<>(current, size);
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getIspass, 1); // 过滤已通过审核的帖子
        IPage<Post> postPage = postService.page(page, queryWrapper);
        return R.success(postPage);
    }

    @GetMapping("/ispasssearch")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent1(String keyword, @RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Post::getContent, keyword)
                .eq(Post::getIspass, 1); // 过滤已通过审核的帖子

        Page<Post> page = new Page<>(current, size);
        IPage<Post> postPage = postService.page(page, queryWrapper);
        return R.success(postPage);
    }

    @GetMapping()
    @ApiOperation(value = "getById")
    public R<Post> updateManager(@RequestParam Integer id){
        return R.success(postService.getById(id));
    }

    @GetMapping("/userid")
    @ApiOperation(value = "getByUserId")
    public R<IPage<Post>> getByUserId(@RequestParam Integer userId) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getUserId, userId);

        Page<Post> page = new Page<>(1, 10); // 默认查询第一页，每页10条记录
        IPage<Post> postPage = postService.page(page, queryWrapper);
        return R.success(postPage);
    }

    @GetMapping("/search")
    @ApiOperation(value = "search")
    public R<IPage> searchByContent(String keyword,@RequestParam(defaultValue = "1") Integer current,@RequestParam(defaultValue = "10") int size) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Post::getContent, keyword);

        List<Post> searchResult = postService.list(queryWrapper);
        Page page = new Page(current,size);
        IPage postPage = postService.page(page,queryWrapper);
        return R.success(postPage);
    }


    @PutMapping()
    @ApiOperation(value = "update")
    public R<String> updateManager(@RequestBody @Validated(Admin.Update.class) Post post){
        postService.updateById(post);
        return R.success();
    }
    @PostMapping()
    @ApiOperation(value = "save")
    public R<String> save(@RequestBody @Validated(Admin.Add.class) Post post){
        postService.save(post);
        return R.success();
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        postService.removeById(id);
        return R.success();
    }

}
