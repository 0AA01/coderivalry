package com.xzj.coderivalry.biz.userservice.dao.entiry;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xzj.coderivalry.biz.userservice.dao.enums.UserTypeEnum;
import com.xzj.coderivalry.framework.starter.database.base.BaseDO;
import lombok.Data;

/**
 * 用户实体
 * @author xzj
 */
@TableName("user")
@Data
public class UserDO extends BaseDO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 竞赛分数
     */
    private Integer competitionScore;

    /**
     * 用户类型
     */
    private UserTypeEnum userType;
}
