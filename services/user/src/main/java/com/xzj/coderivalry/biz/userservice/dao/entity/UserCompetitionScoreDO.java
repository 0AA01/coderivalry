package com.xzj.coderivalry.biz.userservice.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xzj.coderivalry.framework.starter.database.base.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 用户竞赛分数实体
 */
@TableName("user_competition_score")
@Data
public class UserCompetitionScoreDO extends BaseDO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 竞赛分数
     */
    private Integer score;

    /**
     * 最大竞赛分数
     */
    private Integer maxScore;

    /**
     * 更新时间
     */
    private Date updateTime;
}
