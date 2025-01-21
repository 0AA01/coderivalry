drop table if exists user;
create table user
(
    id            bigint auto_increment comment 'ID'
        primary key,
    username      varchar(256) null comment '用户名',
    password      varchar(512) null comment '密码',
    phone         varchar(128) null comment '手机号',
    mail          varchar(512) null comment '邮箱',
    deletion_time bigint       null comment '注销时间戳',
    competition_score int default 1500 comment '竞赛分数',
    user_type tinyint(1) default 0 comment '用户类型，0：普通用户，1：管理员',
    create_time datetime null comment '创建时间',
    update_time datetime null comment '更新时间',
    del_flag tinyint(1) null comment '删除标识 0：未删除 1：已删除',
    constraint idx_unique_username
        unique (username)
);

drop table if exists algorithmic_problem;
create table algorithmic_problem
(
    id bigint auto_increment comment 'ID' primary key,
    title varchar(64) comment '标题',
    difficulty varchar(20) comment '难度等级',
    author varchar(64) comment '作者',
    time_limit json comment '时间限制',
    space_limit json comment '空间限制',
    item_content varchar(2048) comment '题目内容',
    input_description varchar(512) comment '输入描述',
    output_description varchar(512) comment '输出描述',
    sample_input json comment '示例输入',
    sample_output json comment '示例输出',
    topic_tag json comment '题目标签',
    test_input json comment '测试案列输入',
    test_output json comment '测试案列输出',
    submit_number bigint comment '提交次数',
    pass_number bigint comment '通过次数',
    status tinyint(1) comment '状态 0：待发布 1：已发布 2：已下架',
    create_time datetime null comment '创建时间',
    update_time datetime null comment '更新时间',
    del_flag tinyint(1) null comment '删除标识 0：未删除 1：已删除',
    index(title),
    index(difficulty)
);