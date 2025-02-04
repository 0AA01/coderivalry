package com.xzj.coderivalry.biz.questionbank.dto.req;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

/**
 * 算法题创建请求参数
 */
@Data
public class APCreateReqDTO {

    /**
     * 标题
     */
    private String title;

    /**
     * 难度等级
     */
    private String difficulty;

    /**
     * 作者
     */
    private String author;

    /**
     * 时间限制 JSON
     */
    private String timeLimit;

    /**
     * 空间限制 JSON
     */
    private JsonNode spaceLimit;

    /**
     * 题目内容
     */
    private String itemContent;

    /**
     * 输入描述 JSON
     */
    private String inputDescription;

    /**
     * 输出描述 JSON
     */
    private String outputDescription;

    /**
     * 示例输入 JSON
     */
    private String sampleInput;

    /**
     * 示例输出 JSON
     */
    private String sampleOutput;

    /**
     * 题目标签 JSON
     */
    private String topicTag;

    /**
     * 测试案列输入 JSON
     */
    private String testInput;

    /**
     * 测试案例输出 JSON
     */
    private String testOutput;

    /**
     * 测试点分数 JSON
     */
    private String testPointScore;

    /**
     * 题目总分数
     */
    private Integer totalScore;
}
