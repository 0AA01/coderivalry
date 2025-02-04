package com.xzj.coderivalry.biz.userservice.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xzj.coderivalry.biz.userservice.dao.entity.UserCompetitionScoreDO;
import com.xzj.coderivalry.biz.userservice.dao.mapper.UserCompetitionScoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 用户定时任务
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserJob {

    private static Date updateUserCRTimeStamp = new Date("2025/2/4 19:41:00");
    private static Date deleteUserCRTimeStamp = new Date("2025/2/4 19:41:00");

    private final UserCompetitionScoreMapper userCompetitionScoreMapper;

    @XxlJob("updateUserCompetitionRanKingJob")
    public ReturnT<String> updateUserCompetitionRanKingJob(String date) {
        log.info("---------updateUserCompetitionRanKingJob定时任务执行成功--------");

        Date lastUpdateUserCRTimeStamp = addDateTime(updateUserCRTimeStamp, 30);
        Date now = addDateTime(new Date(), -1);
        if (!lastUpdateUserCRTimeStamp.after(now)) {
            XxlJobHelper.log("---------updateUserCompetitionRanKingJob定时任务执行成功--------{}", updateUserCRTimeStamp);
            return ReturnT.SUCCESS;
        }
        LambdaQueryWrapper<UserCompetitionScoreDO> lambdaQueryWrapper = Wrappers.lambdaQuery(UserCompetitionScoreDO.class)
                .gt(UserCompetitionScoreDO::getUpdateTime, updateUserCRTimeStamp)
                .le(UserCompetitionScoreDO::getUpdateTime, lastUpdateUserCRTimeStamp);

        List<UserCompetitionScoreDO> userCSList = userCompetitionScoreMapper.selectList(lambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(userCSList)) {
            userCSList.sort(Comparator.comparing(UserCompetitionScoreDO::getUpdateTime));
            // todo 执行lua脚本使用双指针更新ZSET RANKING.0-31
            updateUserCRTimeStamp = lastUpdateUserCRTimeStamp;
        }
        XxlJobHelper.log("---------updateUserCompetitionRanKingJob定时任务执行成功--------{}", updateUserCRTimeStamp);
        return ReturnT.SUCCESS;
    }

    @XxlJob("deleteUserCompetitionRanKingJob")
    public ReturnT<String> deleteUserCompetitionRanKingJob(String date) {
        log.info("---------deleteUserCompetitionRanKingJob定时任务执行成功--------");

        Date lastDeleteUserCRTimeStamp = addDateTime(deleteUserCRTimeStamp, 60 * 12);
        Date now = addDateTime(new Date(), -60 * 24 * 30);
        if (!lastDeleteUserCRTimeStamp.after(now)) {
            XxlJobHelper.log("---------deleteUserCompetitionRanKingJob定时任务执行成功--------{}", deleteUserCRTimeStamp);
            return ReturnT.SUCCESS;
        }
        LambdaQueryWrapper<UserCompetitionScoreDO> lambdaQueryWrapper = Wrappers.lambdaQuery(UserCompetitionScoreDO.class)
                .gt(UserCompetitionScoreDO::getUpdateTime, deleteUserCRTimeStamp)
                .le(UserCompetitionScoreDO::getUpdateTime, lastDeleteUserCRTimeStamp);

        List<UserCompetitionScoreDO> userCSList = userCompetitionScoreMapper.selectList(lambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(userCSList)) {
            userCSList.sort(Comparator.comparing(UserCompetitionScoreDO::getUpdateTime));
            // todo 执行lua脚本使用双指针删除ZSET RANKING.0-31
            deleteUserCRTimeStamp = lastDeleteUserCRTimeStamp;
        }
        XxlJobHelper.log("---------deleteUserCompetitionRanKingJob定时任务执行成功--------{}", deleteUserCRTimeStamp);
        return ReturnT.SUCCESS;
    }
    private Date addDateTime(Date date, int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, time); // 增加 30 分钟
        return calendar.getTime();
    }
}
