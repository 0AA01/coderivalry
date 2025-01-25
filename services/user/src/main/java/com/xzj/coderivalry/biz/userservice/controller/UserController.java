package com.xzj.coderivalry.biz.userservice.controller;

import com.xzj.coderivalry.biz.userservice.dto.req.UserRegisterReqDTO;
import com.xzj.coderivalry.biz.userservice.service.UserService;
import com.xzj.coderivalry.framework.starter.convention.result.Result;
import com.xzj.coderivalry.framework.starter.web.utils.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 * @author xzj
 */
@RestController
@RequestMapping("/api/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }
}
