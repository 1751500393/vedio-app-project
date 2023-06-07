package com.cen.admins.controller;

import com.cen.admins.utils.global.handler.utils.exception.BaseException;
import com.cen.admins.utils.global.handler.utils.exception.ControllerRuntimeException;
import com.cen.admins.utils.global.handler.utils.exception.ServiceRuntimeException;
import com.cen.admins.utils.global.handler.utils.json.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    @ExceptionHandler({ServiceRuntimeException.class, ControllerRuntimeException.class})
    public JsonResult<Void> handlerException(BaseException baseException) {
        JsonResult<Void> result = new JsonResult<>(baseException);
        return result;
    }
}
