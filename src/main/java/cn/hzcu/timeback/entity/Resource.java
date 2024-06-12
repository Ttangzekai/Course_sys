package cn.hzcu.timeback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "resource_id", type = IdType.AUTO)
    @NotNull(groups = Resource.Update.class)
    private Integer id;


    @NotNull
    @TableField("course_id")
    private Integer courseid;

    @TableField("resource_name")
    private String name;

    @TableField("resource_url")
    private String url;

    @TableField("upload_time")
    private Timestamp uploadtime;

    @TableField("uploader_id")
    private Integer uploader;

    public interface Add extends Default {
    }
    public interface Update extends Default {
    }

}