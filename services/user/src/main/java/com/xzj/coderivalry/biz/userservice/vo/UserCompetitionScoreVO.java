package com.xzj.coderivalry.biz.userservice.vo;

import lombok.Data;

/**
 * 竞赛分数信息
 */
@Data
public class UserCompetitionScoreVO {

    /**
     * 分数
     */
    private Integer score;

    /**
     * 最大分数
     */
    private Integer maxScore;
}
