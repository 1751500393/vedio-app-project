package com.cen.admins.utils.global.handler.utils.json;

import lombok.Getter;

@Getter
public enum JsonPackageMsgAndCode {
    /**
     * 成功运行
     */
    SUCCESS("200","success");

    final String code;
    final String msg;

    JsonPackageMsgAndCode(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

}
