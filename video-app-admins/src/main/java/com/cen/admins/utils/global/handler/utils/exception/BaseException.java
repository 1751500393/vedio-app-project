package com.cen.admins.utils.global.handler.utils.exception;

import com.cen.admins.utils.global.handler.utils.json.JsonPackageMsgAndCode;

public class BaseException extends RuntimeException {
    private final JsonPackageMsgAndCode jsonPackageMsgAndCode;

    /**
     * 主要调用
     */
    public BaseException(JsonPackageMsgAndCode jsonPackageMsgAndCode) {
        super(jsonPackageMsgAndCode.getMsg());
        this.jsonPackageMsgAndCode = jsonPackageMsgAndCode;
    }

    public JsonPackageMsgAndCode getJsonPackageMsgAndCode() {
        return jsonPackageMsgAndCode;
    }
}