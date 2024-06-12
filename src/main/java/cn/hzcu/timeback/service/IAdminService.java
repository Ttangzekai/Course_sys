package cn.hzcu.timeback.service;

import cn.hzcu.timeback.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-03-22
 */
public interface IAdminService extends IService<Admin> {
    Admin getByName(String name);

}
