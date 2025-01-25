package com.xzj.coderivalry.framework.starter.convention.result;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 全局返回对象
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private final long serialVersionUID = 1;

    /**
     * 正确返回码
     */
    public final static String SUCCESS_CODE = "0";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 数据是否返回成功
     * @return True：成功，False：失败
     */
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}