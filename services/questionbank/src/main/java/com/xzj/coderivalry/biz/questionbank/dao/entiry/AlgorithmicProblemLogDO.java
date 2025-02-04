package com.xzj.coderivalry.biz.questionbank.dao.entiry;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xzj.coderivalry.framework.starter.database.base.BaseDO;
import lombok.Data;

/**
 * 算法题日志实体
 */
@Data
@TableName("algorithmic_problem_log")
public class AlgorithmicProblemLogDO extends BaseDO {

    /**
     * ID
     */
    private Long id;

    /**
     * 算法表主键
     */
    private Long apId;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 通过测试点数量
     */
    private Integer passTestPoint;

    /**
     * 总测试点数量
     */
    private Integer totalTestPoint;

    /**
     * 用户名
     */
    private String username;

    /**
     * 判决
     */
    private String verdict;

    /**
     * 提交语言
     */
    private String lang;

    /**
     * 运行时间
     */
    private Integer time;

    /**
     * 运行内存
     */
    private Integer memory;
}
