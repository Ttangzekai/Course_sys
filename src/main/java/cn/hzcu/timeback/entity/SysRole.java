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
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole {
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = Resource.Update.class)
    /**
     *  编号
     */
    private Integer id;

    @NotNull
    @TableField("role")
    /**
     * 角色标识程序中判断使用,如"admin",这个是唯一的
     */
    private String role;
    /**
     *  角色描述,UI界面显示使用
     */
    @NotNull
    @TableField("description")
    private String description;
    /**
     *  是否可用,如果不可用将不会添加给用户
     */
    @NotNull
    @TableField("available")
    private Boolean available = Boolean.FALSE;
 /**
  * 角色权限关系：多对多关系;
  */
//    @ManyToMany(fetch= FetchType.EAGER)
//    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
//    private List<SysPermission> permissions;
//  /**
//   * 用户角色关系定义;
//   */
//    @ManyToMany
//    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
   /**
    * 一个角色对应多个用户
    */
//   private List<Admin> admins;
    public interface Add extends Default {
    }
    public interface Update extends Default {
    }
}