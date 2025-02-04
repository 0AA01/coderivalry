# CodeRivalry

### 项目介绍

easy 介绍：OJ

hard 介绍：CodeRivalry的目标是做一个高效率OJ，帮助用户在刷题时，集中于刷题本身的知识思维，不受冗余信息干扰，在平台比赛时，保证较高的效率，减少不必要的卡顿，弱化平台的存在，使比赛选手专心于算法题进行思维大战。

### 项目模块

#### 网关模块

#### 用户模块

#### 题库模块

#### 比赛模块

#### 测评模块

### 数据存储

todo 日志表数据点情况，算发表数据点得分情况，比赛时允许管理员停止测评机专攻给比赛用户

#### user 用户表
参考：codeforces（500w）leetcode（500w） Luogo（90w）<br>
前两个平台是社交+OJ所以人数可能较多，该预估最大数据量300w，所以暂时不考虑分表。 

### user_competition_scores 用户竞赛分数表
参考：codeforces(16w) leetcode（14w）
只考虑一个月内有比赛记录的用户，预估最大数据量30w，不需要考虑分表。

#### algorithmic_problem 算法表
参考：codeforces（10200）leetcode（3900）Luogo(11157)<br>
预估最大数据量5w，所以暂时不考虑分表。

#### algorithmic_problem_log 算法日志表
数据量=人数*提交次数*题目数量
预估最大数据量 90b=300w*3*1000，所以需要考虑分表。
考虑分表数量256，单个表数据保证在3515w<br>
考虑表的使用场景：<br>
1.根据用户编号查询单个用户提交记录<br>
2.根据题目编号查询单个题目提交记录<br>
发现需要根据一张总表同时满足两种情况，是不合理的。（每道题的日志在同一个分表下&&每个人的日志在同一个分表下==只有一张分表）<br>
解决方案：<br>
1.仅需要满足1，2其中一条。<br>
2.按照时间去分表，只允许查询分表周期内的记录。<br>
3.建立两张总表，（用户编号分表，题目编号分表）（是否可以两张总表一张详情表减少额外字段的冗余）。<br>


###### 项目借鉴：马丁-12306项目结构