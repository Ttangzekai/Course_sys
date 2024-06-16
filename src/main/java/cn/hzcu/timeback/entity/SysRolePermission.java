package cn.hzcu.timeback.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {
    @NotNull
    @TableField("permission_id")
    private Integer permissionid;
    /**
     * 权限名称.
     */
    @NotNull
    @TableField("role_id")
    private Integer roleid;

    public interface Add extends Default {
    }
    public interface Update extends Default {
    }
}