package com.cen.admins.controller;

import com.cen.admins.entity.Admin;
import com.cen.admins.service.AdminService;
import com.cen.admins.utils.JSONUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2023-06-02 20:24:07
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 构筑日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 登录
     */
    public Map<String, String> tokens(@RequestBody Admin admin) {
        log.info("accept Admin Object:{}", JSONUtils.writeJSON(admin));
        Admin adminDB = adminService.login(admin);
        return null;
    }

}

