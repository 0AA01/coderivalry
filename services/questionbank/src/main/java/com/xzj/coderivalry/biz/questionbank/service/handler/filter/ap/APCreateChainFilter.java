package com.xzj.coderivalry.biz.questionbank.service.handler.filter.ap;

import com.xzj.coderivalry.biz.questionbank.common.enums.APChainMarkEnum;
import com.xzj.coderivalry.biz.questionbank.dto.req.APCreateReqDTO;
import com.xzj.coderivalry.framework.starter.designpattern.chain.AbstractChainHandler;

/**
 * 算法题创建责任链过滤器
 */
public interface APCreateChainFilter<T extends APCreateReqDTO> extends AbstractChainHandler<APCreateReqDTO> {

    @Override
    default String mark() {
        return APChainMarkEnum.AP_CREATE_FILTER.name();
    }
}