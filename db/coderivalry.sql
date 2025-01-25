drop table if exists user;
create table user
(
    id                bigint auto_increment comment 'ID'
        primary key,
    username          varchar(256)            not null comment '用户名',
    password          varchar(512)            not null comment '密码',
    phone             varchar(128)            null comment '手机号',
    mail              varchar(512)            null comment '邮箱',
    deletion_time     datetime                null comment '注销时间戳',
    competition_score int        default 1500 not null comment '竞赛分数',
    user_type         tinyint(1) default 0    not null comment '用户类型，0：普通用户，1：管理员',
    create_time       datetime                not null comment '创建时间',
    del_flag          tinyint(1)              not null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

drop table if exists algorithmic_problem;
create table algorithmic_problem
(
    id                 bigint auto_increment comment 'ID'
        primary key,
    title              varchar(64)      not null comment '标题',
    difficulty         varchar(20)      not null comment '难度等级',
    author             varchar(64)      null comment '作者',
    time_limit         json             null comment '时间限制',
    space_limit        json             null comment '空间限制',
    item_content       varchar(2048)    null comment '题目内容',
    input_description  varchar(512)     null comment '输入描述',
    output_description varchar(512)     null comment '输出描述',
    sample_input       json             null comment '示例输入',
    sample_output      json             null comment '示例输出',
    topic_tag          json             null comment '题目标签',
    test_input         json             null comment '测试案列输入',
    test_output        json             null comment '测试案列输出',
    test_point_score   json             null comment '测试点分数',
    total_score        int              not null comment '题目总分数',
    submit_number      bigint default 0 not null comment '提交次数',
    pass_number        bigint default 0 not null comment '通过次数',
    status             tinyint(1)       not null comment '状态 0：待发布 1：已发布 2：已下架',
    create_time        datetime         not null comment '创建时间',
    del_flag           tinyint(1)       not null comment '删除标识 0：未删除 1：已删除',
    index idx_title (title),
    index idx_difficulty (difficulty)
);

drop table if exists algorithmic_problem_log;
create table algorithmic_problem_log
(
    id               bigint auto_increment comment 'ID'
        primary key,
    ap_id            bigint       not null comment '算法表主键',
    score            int          not null comment '分数',
    pass_test_point  int          not null comment '通过测试点数量',
    total_test_point int          not null comment '总测试点数量',
    username         varchar(256) not null comment '用户名',
    log_status       varchar(32)  not null comment '日志状态',
    create_time      datetime     not null comment '创建时间',
    del_flag         tinyint(1)   not null comment '删除标识 0：未删除 1：已删除'
);