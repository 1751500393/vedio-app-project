package com.cen.admins.controller;

import com.cen.admins.entity.Admin;
import com.cen.admins.objects.dtos.AdminDTO;
import com.cen.admins.service.AdminService;
import com.cen.commans.utils.JSONUtils;
import com.cen.admins.utils.global.handler.utils.exception.ControllerRuntimeException;
import com.cen.admins.utils.global.handler.utils.json.JsonPackageMsgAndCode;
import com.cen.admins.utils.global.handler.utils.json.JsonResult;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2023-06-02 20:24:07
 */
@RestController
public class AdminController extends BaseController {
    /**
     * 构筑日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    /**
     * 服务对象
     */
    private AdminService adminService;
    /**
     * 注入Redis对象
     */
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public AdminController(AdminService adminService, RedisTemplate redisTemplate) {
        this.adminService = adminService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 登录
     */
    @RequestMapping("tokens")
    public JsonResult<Map<String, String>> tokens(@RequestBody Admin admin, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        log.info("accept Admin Object:{}", JSONUtils.writeJSON(admin));
        Admin adminDB = adminService.login(admin);
        String token = session.getId();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(token, adminDB, 30, TimeUnit.MINUTES);
        result.put("token", token);
        return new JsonResult<Map<String, String>>(JsonPackageMsgAndCode.SUCCESS, result);
    }

    /**
     * 获取登录用户的信息
     */
    @GetMapping("/admin-user")
    public JsonResult<AdminDTO> admin(@RequestParam String token) {
        log.info("当前用户信息:{}", token);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Admin admin = (Admin) redisTemplate.opsForValue().get(token);
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(admin, adminDTO);
        return new JsonResult<AdminDTO>(JsonPackageMsgAndCode.SUCCESS, adminDTO);
    }

    /**
     * 登出接口
     */
    @DeleteMapping("/tokens/logout/{token}")
    public JsonResult<Void> logout(@PathVariable("token") String token) {
//        设置序列化器(方便String的序列化和反序列化)
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Boolean f = redisTemplate.delete(token);
        if (f) {
            return new JsonResult<Void>(JsonPackageMsgAndCode.SUCCESS, null);
        }
        throw new ControllerRuntimeException(JsonPackageMsgAndCode.FAIL_LOGOUT);
    }
}

