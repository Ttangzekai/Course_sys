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
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "course_id", type = IdType.AUTO)
    @NotNull(groups = Course.Update.class)
    private Integer id;


    @NotNull
    @TableField("course_name")
    private String coursename;

    @TableField("course_description")
    private String description;

    @TableField("course_category_id")
    private Integer category;

    @TableField("teacher_id")
    private Integer teacher;

    public interface Add extends Default {
    }
    public interface Update extends Default {
    }

}
