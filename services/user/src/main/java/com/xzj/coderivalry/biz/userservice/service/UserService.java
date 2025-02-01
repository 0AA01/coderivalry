package com.xzj.coderivalry.biz.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzj.coderivalry.biz.userservice.dao.entity.UserDO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserLoginReqDTO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserRegisterReqDTO;
import com.xzj.coderivalry.biz.userservice.vo.UserLoginVO;

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

    /**
     * 用户登录
     * @param requestParam 用户登录请求参数
     * @return 用户登录返回结果
     */
    UserLoginVO login(UserLoginReqDTO requestParam);

    /**
     * 用户退出登录
     * @param token 用户Token
     */
    void logout(String token);
}
