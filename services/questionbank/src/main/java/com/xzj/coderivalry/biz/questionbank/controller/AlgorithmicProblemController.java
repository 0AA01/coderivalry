package com.xzj.coderivalry.biz.questionbank.controller;

import com.xzj.coderivalry.biz.questionbank.dto.req.APCreateReqDTO;
import com.xzj.coderivalry.biz.questionbank.service.AlgorithmicProblemLogService;
import com.xzj.coderivalry.biz.questionbank.service.AlgorithmicProblemService;
import com.xzj.coderivalry.framework.starter.convention.result.Result;
import com.xzj.coderivalry.framework.starter.web.utils.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 算法题控制层
 */
@RestController
@RequestMapping("/api/ap-service")
@RequiredArgsConstructor
public class AlgorithmicProblemController {

    private final AlgorithmicProblemService aPService;
    private final AlgorithmicProblemLogService aPLogService;

    /**
     * 创建算法题
     */
    @PostMapping("/ap")
    public Result<Void> createAlgorithmicProblem(@RequestBody APCreateReqDTO requestParam) {
        aPService.createAlgorithmicProblem(requestParam);
        return Results.success();
    }
}
