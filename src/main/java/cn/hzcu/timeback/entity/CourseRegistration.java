package cn.hzcu.timeback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

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
@TableName("courseregistration")
public class CourseRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "registration_id", type = IdType.AUTO)
    @NotNull(groups = CourseRegistration.Update.class)
    private Integer id;


    @NotNull
    @TableField("course_id")
    private Integer courseid;

    @NotNull
    @TableField("student_id")
    private Integer stuid;

    public interface Add extends Default {
    }
    public interface Update extends Default {
    }

}
