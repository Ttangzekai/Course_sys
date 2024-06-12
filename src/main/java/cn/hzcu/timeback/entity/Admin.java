package cn.hzcu.timeback.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
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
@EqualsAndHashCode(callSuper = false,exclude = {"roleList"})
@TableName("admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    @NotNull(groups = Update.class)
    private Integer id;

    @TableField("username")
    private String name;
    @TableField("PASSWORD")
    private String password;

    @TableField("registration_date")
    private java.sql.Timestamp regdate;

//    @ManyToMany(fetch= FetchType.EAGER)//
//    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
//    /**
//     * 一个用户具有多个角色
//     */
//    private List<SysRole> roleList;//

//    public List<SysRole> getRoleList() {
//        return roleList;
//    }

    public interface Add extends Default {
    }
    public interface Update extends Default {
    }

}
