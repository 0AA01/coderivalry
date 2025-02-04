package com.xzj.coderivalry.biz.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzj.coderivalry.biz.userservice.dao.entity.UserCompetitionScoreDO;
import com.xzj.coderivalry.biz.userservice.dto.req.UserCompetitionRankingReqDTO;
import com.xzj.coderivalry.biz.userservice.vo.UserCompetitionRankingVO;
import com.xzj.coderivalry.biz.userservice.vo.UserCompetitionScoreVO;
import com.xzj.coderivalry.framework.starter.convention.page.PageResponse;

/**
 * 用户竞赛分数接口层
 */
public interface UserCompetitionScoreService extends IService<UserCompetitionScoreDO> {

    /**
     * 查看竞赛分数
     *
     * @return 竞赛分数信息
     */
    UserCompetitionScoreVO cScore(String username);

    /**
     * 分页查看竞赛排行榜
     *
     * @param requestParam 分页查看竞赛排行榜请求参数
     * @return 竞赛排行榜信息
     */
    PageResponse<UserCompetitionRankingVO> cRanking(UserCompetitionRankingReqDTO requestParam);
}
