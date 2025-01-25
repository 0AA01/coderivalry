package com.xzj.coderivalry.biz.userservice.dao.enums;

import lombok.Getter;

/**
 * 用户类型枚举类
 * @author xzj
 */
@Getter
public enum UserTypeEnum {
    NORMAL_USER("0", "普通用户"),
    ADMIN_USER("1", "管理员");

    private final String code;
    private final String name;

    UserTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
