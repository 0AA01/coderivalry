package com.xzj.coderivalry.biz.userservice.controller;

import com.xzj.coderivalry.biz.userservice.dto.req.UserCompetitionRankingReqDTO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserLoginReqDTO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserRegisterReqDTO;
import com.xzj.coderivalry.biz.userservice.service.UserCompetitionScoreService;
import com.xzj.coderivalry.biz.userservice.service.UserService;
import com.xzj.coderivalry.biz.userservice.vo.UserCompetitionRankingVO;
import com.xzj.coderivalry.biz.userservice.vo.UserCompetitionScoreVO;
import com.xzj.coderivalry.biz.userservice.vo.UserLoginVO;
import com.xzj.coderivalry.framework.starter.convention.page.PageResponse;
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
    private final UserCompetitionScoreService userCompetitionScoreService;

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
    public Result<Void> logout(@RequestParam(name = "token") String token) {
        userService.logout(token);
        return Results.success();
    }

    /**
     * 查看竞赛分数
     */
    @GetMapping("/user/cScore")
    public Result<UserCompetitionScoreVO> cScore(@RequestParam(name = "username") String username) {
        return Results.success(userCompetitionScoreService.cScore(username));
    }

    /**
     * 查看竞赛排行榜
     */
    @GetMapping("/user/ranking")
    public Result<PageResponse<UserCompetitionRankingVO>> cRanking(@RequestBody UserCompetitionRankingReqDTO requestParam) {
        return Results.success(userCompetitionScoreService.cRanking(requestParam));
    }
}
