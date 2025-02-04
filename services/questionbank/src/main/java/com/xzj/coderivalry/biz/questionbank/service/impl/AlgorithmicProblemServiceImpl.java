package com.xzj.coderivalry.biz.questionbank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzj.coderivalry.biz.questionbank.dao.entiry.AlgorithmicProblemDO;
import com.xzj.coderivalry.biz.questionbank.dao.mapper.AlgorithmicProblemMapper;
import com.xzj.coderivalry.biz.questionbank.dto.req.APCreateReqDTO;
import com.xzj.coderivalry.biz.questionbank.service.AlgorithmicProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 算法题实现层
 */
@Service
@RequiredArgsConstructor
public class AlgorithmicProblemServiceImpl extends ServiceImpl<AlgorithmicProblemMapper, AlgorithmicProblemDO> implements AlgorithmicProblemService {

    private final AlgorithmicProblemMapper aPMapper;

    @Override
    public void createAlgorithmicProblem(APCreateReqDTO requestParam) {



        AlgorithmicProblemDO algorithmicProblemDO = new AlgorithmicProblemDO();
        algorithmicProblemDO.setAuthor(requestParam.getAuthor());

    }
}
