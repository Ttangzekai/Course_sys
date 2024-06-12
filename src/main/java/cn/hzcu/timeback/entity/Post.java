package cn.hzcu.timeback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

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
@TableName("post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = Admin.Update.class)
    private Integer id;
    private LocalDateTime starttime;
    @NotNull
    private Integer userId;
    private Integer ispass;
    private Integer isend;

    private Integer timecoin;

    private Integer volunteers;

    private String content;


}
