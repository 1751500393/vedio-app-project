package com.cen.admins.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cen.admins.entity.Admin;
import com.cen.admins.mapper.AdminMapper;
import com.cen.admins.service.AdminService;
import com.cen.admins.utils.global.handler.utils.exception.ServiceRuntimeException;
import com.cen.admins.utils.global.handler.utils.json.JsonPackageMsgAndCode;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2023-06-02 20:24:16
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Integer id) {
        return this.adminMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param admin       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Admin> queryByPage(Admin admin, PageRequest pageRequest) {
        long total = this.adminMapper.count(admin);
        return new PageImpl<>(this.adminMapper.queryAllByLimit(admin, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminMapper.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminMapper.update(admin);
        return this.queryById(admin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.adminMapper.deleteById(id) > 0;
    }

    /**
     * 通过admin对象,登录
     *
     * @param admin
     * @return 实例对象
     */
    @Override
    public Admin login(Admin admin) {
        Admin adminDB = adminMapper.findByUserName(admin.getUsername());
//         判断用户名是否存在
        if (ObjectUtils.isEmpty(adminDB)) throw new ServiceRuntimeException(JsonPackageMsgAndCode.USERNAME_IS_WRONG);

//        判断密码
        String password=DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8));
        if (!StringUtils.equals(password,adminDB.getPassword())) throw new ServiceRuntimeException(JsonPackageMsgAndCode.PASSWORD_IS_WRONG);
        return adminDB;
    }
}
