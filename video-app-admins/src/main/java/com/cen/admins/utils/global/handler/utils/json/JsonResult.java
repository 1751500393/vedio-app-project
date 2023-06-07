package com.cen.admins.utils.global.handler.utils.json;

import com.cen.admins.utils.global.handler.utils.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult<T> {
    /**
     * 异常信息
     */
    private String msg;
    /**
     * 状态码
     **/
    private String code;
    /**
     * 数据
     */
    private T data;
    /**
     * 状态码和异常信息的封装类
     */
    private JsonPackageMsgAndCode jsonPackageMsgAndCode;

    public JsonResult(BaseException baseException) {
        this.msg = baseException.getJsonPackageMsgAndCode().getMsg();
        this.code = baseException.getJsonPackageMsgAndCode().getCode();
    }

    public JsonResult(String msg) {
        this.msg = msg;
    }


    public JsonResult(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public JsonResult(String msg, String code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public JsonResult(JsonPackageMsgAndCode jsonPackageMsgAndCode, T data) {
        this.msg = jsonPackageMsgAndCode.getMsg();
        this.code = jsonPackageMsgAndCode.getCode();
        this.data = data;
    }


}
