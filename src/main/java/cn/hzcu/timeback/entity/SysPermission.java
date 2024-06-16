package cn.hzcu.timeback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("sys_permission")
public class SysPermission implements Serializable {
    @TableId(value = "id")
    @NotNull(groups = Resource.Update.class)
    private Integer id;
    /**
     * 权限名称.
     */
    @NotNull
    @TableField("name")
    private String name;


    /**
     * 资源类型，[menu|button]
     */
    @NotNull
    @TableField("resource_type")
    private String resourceType;
    /**
     * 资源路径
     */
    @NotNull
    @TableField("url")
    private String url;
    /**
     * 权限字符串
     */
    @NotNull
    @TableField("permission")
    private String permission;
    // menu例子：role:*，
    // button例子：role:create,role:update,role:delete,role:view
    /**
     * 父编号
     */
    @NotNull
    @TableField("parent_id")
    private Long parentId;
    /**
     * 父编号列表
     */
    @NotNull
    @TableField("parent_ids")
    private String parentIds;
    @NotNull
    @TableField("available")
    private Boolean available = Boolean.FALSE;
//    @ManyToMany
//    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
//    private List<SysPermission> permissions;

    public interface Add extends Default {
    }
    public interface Update extends Default {
    }
}