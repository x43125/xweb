drop table if exists blog;
drop table if exists blog_collect;
drop table if exists blog_comment;
drop table if exists blog_forward;
drop table if exists blog_like;
drop table if exists blog_read;
drop table if exists blog_trash;
drop table if exists web_user;
drop table if exists TUser;

/*==============================================================*/
/* Table: blog                                                  */
/*==============================================================*/
create table blog
(
    id            SERIAL       not null,
    name          varchar(40)  not null,
    tag           varchar(20)  null default 'default',
    pic           varchar(255) null,
    type          varchar(10)  null default 'txt',
    read_count    bigint       null default '0',
    like_count    bigint       null default '0',
    comment_count bigint       null default '0',
    forward_count bigint       null default '0',
    collect_count bigint       null default '0',
    trash_count   bigint       null default '0',
    update_time   timestamp    null default CURRENT_TIMESTAMP,
    create_time   timestamp    null default CURRENT_TIMESTAMP,
    constraint PK_BLOG primary key (id)
);

comment on column blog.id is
    '博客id';

comment on column blog.name is
    '博客名';

comment on column blog.tag is
    '博客标签';

comment on column blog.pic is
    '博客图片';

comment on column blog.type is
    '博客本地存储的后缀名';

comment on column blog.read_count is
    '阅读数';

comment on column blog.like_count is
    '点赞数';

comment on column blog.comment_count is
    '评论数';

comment on column blog.forward_count is
    '转发数';

comment on column blog.collect_count is
    '收藏数';

comment on column blog.trash_count is
    '屏蔽数';

comment on column blog.update_time is
    '更新时间';

comment on column blog.create_time is
    '创建时间';

/*==============================================================*/
/* Table: blog_collect                                          */
/*==============================================================*/
create table blog_collect
(
    id                SERIAL      not null,
    blog_name         varchar(40) null,
    blog_id           bigint      null,
    collect_user_id   bigint      null,
    collect_user_name varchar(40) null,
    update_time       timestamp   null default CURRENT_TIMESTAMP,
    create_time       timestamp   null default CURRENT_TIMESTAMP,
    constraint PK_BLOG_COLLECT primary key (id)
);

comment on table blog_collect is
    '博客收藏表';

comment on column blog_collect.blog_name is
    '博客名';

comment on column blog_collect.blog_id is
    '博客id';

comment on column blog_collect.collect_user_id is
    '收藏者id';

comment on column blog_collect.collect_user_name is
    '收藏者名';

/*==============================================================*/
/* Table: blog_comment                                          */
/*==============================================================*/
create table blog_comment
(
    id                SERIAL       not null,
    blog_name         varchar(40)  null,
    blog_id           bigint       null,
    comment_user_id   bigint       null,
    comment_user_name varchar(40)  null,
    comment           varchar(255) not null,
    update_time       timestamp    null default CURRENT_TIMESTAMP,
    create_time       timestamp    null default CURRENT_TIMESTAMP,
    constraint PK_BLOG_COMMENT primary key (id)
);

comment on table blog_comment is
    '博客评论表';

comment on column blog_comment.blog_name is
    '博客名';

comment on column blog_comment.blog_id is
    '博客id';

comment on column blog_comment.comment_user_id is
    '评论者id';

comment on column blog_comment.comment_user_name is
    '评论者名';

comment on column blog_comment.comment is
    '评论内容';

/*==============================================================*/
/* Table: blog_forward                                          */
/*==============================================================*/
create table blog_forward
(
    id                SERIAL      not null,
    blog_name         varchar(40) null,
    blog_id           bigint      null,
    forward_user_id   bigint      null,
    forward_user_name varchar(40) null,
    update_time       timestamp   null default CURRENT_TIMESTAMP,
    create_time       timestamp   null default CURRENT_TIMESTAMP,
    constraint PK_BLOG_FORWARD primary key (id)
);

comment on table blog_forward is
    '博客转发表';

comment on column blog_forward.blog_name is
    '博客名';

comment on column blog_forward.blog_id is
    '博客id';

comment on column blog_forward.forward_user_id is
    '转发者id';

comment on column blog_forward.forward_user_name is
    '转发者名';

/*==============================================================*/
/* Table: blog_like                                             */
/*==============================================================*/
create table blog_like
(
    id             SERIAL      not null,
    blog_name      varchar(40) null,
    blog_id        bigint      null,
    like_user_id   bigint      null,
    like_user_name varchar(40) null,
    update_time    timestamp   null default CURRENT_TIMESTAMP,
    create_time    timestamp   null default CURRENT_TIMESTAMP,
    constraint PK_BLOG_LIKE primary key (id)
);

comment on table blog_like is
    '博客点赞表';

comment on column blog_like.blog_name is
    '博客名';

comment on column blog_like.blog_id is
    '博客id';

comment on column blog_like.like_user_id is
    '点赞者id';

comment on column blog_like.like_user_name is
    '点赞者名';

/*==============================================================*/
/* Table: blog_read                                             */
/*==============================================================*/
create table blog_read
(
    id             SERIAL      not null,
    blog_name      varchar(40) null,
    blog_id        bigint      null,
    read_user_id   bigint      null,
    read_user_name varchar(40) null,
    update_time    timestamp   null default CURRENT_TIMESTAMP,
    create_time    timestamp   null default CURRENT_TIMESTAMP,
    constraint PK_BLOG_READ primary key (id)
);

comment on table blog_read is
    '博客阅读者表';

comment on column blog_read.blog_name is
    '博客名';

comment on column blog_read.blog_id is
    '博客id';

comment on column blog_read.read_user_id is
    '阅读者id';

comment on column blog_read.read_user_name is
    '阅读者名';

/*==============================================================*/
/* Table: blog_trash                                            */
/*==============================================================*/
create table blog_trash
(
    id              SERIAL      not null,
    blog_name       varchar(40) null,
    blog_id         bigint      null,
    trash_user_id   bigint      null,
    trash_user_name varchar(40) null,
    update_time     timestamp   null default CURRENT_TIMESTAMP,
    create_time     timestamp   null default CURRENT_TIMESTAMP,
    constraint PK_BLOG_TRASH primary key (id)
);

comment on table blog_trash is
    '博客屏蔽表';

comment on column blog_trash.blog_name is
    '博客名';

comment on column blog_trash.blog_id is
    '博客id';

comment on column blog_trash.trash_user_id is
    '屏蔽者id';

comment on column blog_trash.trash_user_name is
    '屏蔽者名';

/*==============================================================*/
/* Table: web_user                                              */
/*==============================================================*/
create table web_user
(
    id          SERIAL       not null,
    username    varchar(40)  not null,
    passwd      varchar(32)  not null,
    pic         varchar(255) null,
    update_time timestamp    not null default CURRENT_TIMESTAMP,
    create_time timestamp    not null default CURRENT_TIMESTAMP,
    constraint PK_WEB_USER primary key (id)
);

comment on table web_user is
    '用户表';

drop table if exists blog_comment_reply;

/*==============================================================*/
/* Table: blog_comment_reply                                    */
/*==============================================================*/
create table blog_comment_reply (
                                    id                   SERIAL not null,
                                    comment_user_id      bigint               not null,
                                    comment_reply        varchar(255)         not null,
                                    comment_user_name    varchar              null,
                                    blog_id              bigint               not null,
                                    blog_name            varchar              null,
                                    blog_comment_id      bigint               not null,
                                    create_time          timestamp            not null default CURRENT_DATE,
                                    update_time          timestamp            not null default CURRENT_DATE,
                                    constraint PK_BLOG_COMMENT_REPLY primary key (id, comment_user_id, comment_reply, create_time, update_time, blog_id, blog_comment_id)
);

comment on table blog_comment_reply is
    '博客主人回复访问者评论的评论';

comment on column blog_comment_reply.comment_reply is
    '回复内容';

