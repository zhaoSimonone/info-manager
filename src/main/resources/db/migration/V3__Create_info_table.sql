-- 用户提交的内容
create table info
(
    id            int auto_increment
        primary key,
    creator       int           null,
    title         varchar(50)   null,
    description   text          null,
    gmt_create    bigint        null,
    gmt_modified  bigint        null,
    comment_count int           null,
    view_count    int default 0 null,
    like_count    int default 0 null,
    tag           varchar(50)   null
);