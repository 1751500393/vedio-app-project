package com.cen.admins.utils.global.handler.utils.exception;


import com.cen.admins.utils.global.handler.utils.json.JsonPackageMsgAndCode;

public class ControllerRuntimeException extends BaseException {
    public ControllerRuntimeException(JsonPackageMsgAndCode jsonPackageMsgAndCode) {
        super(jsonPackageMsgAndCode);

    }
}
