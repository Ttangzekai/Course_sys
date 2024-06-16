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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
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

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("course_id") Integer courseId,
                                             @RequestParam("uploader_id") Integer uploaderId,
                                             @RequestParam("resource_name") String resourceName) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Resource resource = new Resource();


        try {
            Path uploadPath = Paths.get(uploadDir, courseId.toString());
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            System.out.println("Uploading to: " + filePath.toAbsolutePath().toString());

            resource.setUploader(uploaderId);
            resource.setCourseid(courseId);
            resource.setName(resourceName);
            resource.setUrl(filePath.toAbsolutePath().toString());

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            ResourceService.save(resource);
            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not upload the file: " + fileName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{courseId}/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("courseId") String courseId,
                                                            @PathVariable("fileName") String fileName) {
        String filePath = uploadDir+ "/" + courseId + "/" + fileName;
        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamResource resource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @DeleteMapping()
    @ApiOperation(value = "delete")
    public R<String> save(@RequestParam Integer id){
        ResourceService.removeById(id);
        return R.success();
    }

}
