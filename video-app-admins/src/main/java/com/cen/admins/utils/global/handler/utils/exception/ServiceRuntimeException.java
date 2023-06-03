package com.cen.admins.utils.global.handler.utils.exception;


import com.cen.admins.utils.global.handler.utils.json.JsonPackageMsgAndCode;

public class ServiceRuntimeException extends BaseException {
    public ServiceRuntimeException(JsonPackageMsgAndCode jsonPackageMsgAndCode) {

        super(jsonPackageMsgAndCode);
    }
}
