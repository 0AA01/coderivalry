package com.xzj.coderivalry.biz.userservice.controller;

import com.xzj.coderivalry.biz.userservice.dto.req.UserLoginReqDTO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserRegisterReqDTO;
import com.xzj.coderivalry.biz.userservice.service.UserService;
import com.xzj.coderivalry.biz.userservice.vo.UserLoginVO;
import com.xzj.coderivalry.framework.starter.convention.result.Result;
import com.xzj.coderivalry.framework.starter.web.utils.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 * @author xzj
 */
@RestController
@RequestMapping("/api/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/user/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    /**
     * 用户退出登录
     */
    @GetMapping("/user/logout")
    public Result<Void> logout(@RequestParam String token) {
        userService.logout(token);
        return Results.success();
    }
}
