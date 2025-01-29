package com.xzj.coderivalry.biz.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzj.coderivalry.biz.userservice.dao.entity.UserDO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserRegisterReqDTO;

/**
 * 用户接口层
 *
 * @author xzj
 */
public interface UserService extends IService<UserDO> {

    /**
     * 用户注册
     *
     * @param requestParam 用户注册请求参数
     */
    void register(UserRegisterReqDTO requestParam);
}
