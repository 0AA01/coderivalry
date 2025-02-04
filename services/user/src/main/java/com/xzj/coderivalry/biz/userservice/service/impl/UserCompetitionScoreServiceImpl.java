package com.xzj.coderivalry.biz.userservice.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzj.coderivalry.biz.userservice.common.constant.RedisConstant;
import com.xzj.coderivalry.biz.userservice.dao.entity.UserCompetitionScoreDO;
import com.xzj.coderivalry.biz.userservice.dao.mapper.UserCompetitionScoreMapper;
import com.xzj.coderivalry.biz.userservice.service.UserCompetitionScoreService;
import com.xzj.coderivalry.biz.userservice.vo.UserCompetitionScoreVO;
import com.xzj.coderivalry.framework.starter.cache.DistributedCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 用户竞赛分数接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserCompetitionScoreServiceImpl extends ServiceImpl<UserCompetitionScoreMapper, UserCompetitionScoreDO> implements UserCompetitionScoreService {

    private final DistributedCache distributedCache;
    private final UserCompetitionScoreMapper userCompetitionScoreMapper;

    @Override
    public UserCompetitionScoreVO cScore(String username) {
        String lockKey = String.format(RedisConstant.LOCK_USER_COMPETITION_SCORE_KEY, username);
        return distributedCache.safeGet(
                lockKey, UserCompetitionScoreVO.class, () -> {
                    UserCompetitionScoreDO userCSDO = userCompetitionScoreMapper.selectOne(Wrappers.<UserCompetitionScoreDO>lambdaQuery().eq(UserCompetitionScoreDO::getUsername, username));
                    UserCompetitionScoreVO userCSVO = new UserCompetitionScoreVO();
                    if (userCSDO != null) {
                        userCSVO.setScore(userCSDO.getScore());
                        userCSVO.setMaxScore(userCSDO.getMaxScore());
                    }
                    return userCSVO;
                }, 7, TimeUnit.DAYS);
    }
}
