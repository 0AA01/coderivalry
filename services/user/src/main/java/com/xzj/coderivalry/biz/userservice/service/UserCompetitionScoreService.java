package com.xzj.coderivalry.biz.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzj.coderivalry.biz.userservice.dao.entity.UserCompetitionScoreDO;
import com.xzj.coderivalry.biz.userservice.vo.UserCompetitionScoreVO;

/**
 * 用户竞赛分数接口层
 */
public interface UserCompetitionScoreService extends IService<UserCompetitionScoreDO> {

    /**
     * 查看竞赛分数
     * @return 竞赛分数信息
     */
    UserCompetitionScoreVO cScore(String username);
}
