package com.xzj.coderivalry.biz.questionbank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzj.coderivalry.biz.questionbank.dao.entiry.AlgorithmicProblemDO;
import com.xzj.coderivalry.biz.questionbank.dto.req.APCreateReqDTO;

/**
 * 算法题接口层
 */
public interface AlgorithmicProblemService extends IService<AlgorithmicProblemDO> {

    /**
     * 算法题创建
     * @param requestParam 算法题创建请求参数
     */
    void createAlgorithmicProblem(APCreateReqDTO requestParam);
}
