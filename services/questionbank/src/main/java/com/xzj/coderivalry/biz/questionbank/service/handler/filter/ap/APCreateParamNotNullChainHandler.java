package com.xzj.coderivalry.biz.questionbank.service.handler.filter.ap;


import com.xzj.coderivalry.biz.questionbank.dto.req.APCreateReqDTO;
import org.springframework.stereotype.Component;

/**
 * 算法题创建参数必填检验
 */
@Component
public class APCreateParamNotNullChainHandler implements APCreateChainFilter<APCreateReqDTO> {

    @Override
    public void handler(APCreateReqDTO requestParam) {
        // todo 参数检验
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
