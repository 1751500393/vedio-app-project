package com.cen.admins.utils.global.handler.utils.json;

import lombok.Getter;

@Getter
public enum JsonPackageMsgAndCode {
    /**
     * 成功运行
     */
    SUCCESS("200", "success"),

    USERNAME_IS_WRONG("5001", "用户名错误"), PASSWORD_IS_WRONG("5002", "密码错误")
    ,FAIL_LOGOUT("6001","不存在，该账户");
    final String code;
    final String msg;

    JsonPackageMsgAndCode(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

}
