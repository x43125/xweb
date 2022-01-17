drop table if exists blog;

drop table if exists blog_collect;

drop table if exists blog_comment;

drop table if exists blog_forward;

drop table if exists blog_like;

drop table if exists blog_trash;

drop table if exists user;

drop table if exists blog_read;

/*==============================================================*/
/* Table: blog                                                  */
/*==============================================================*/
create table if not exists blog
(
    id                   int not null auto_increment comment '博客id',
    name                 varchar(40) not null comment '博客名',
    tag                  varchar(20) default 'default' comment '博客标签',
    pic                  varchar(100) comment '博客图片',
    type                 varchar(10) default 'txt' comment '博客本地存储的后缀名',
    read_count           bigint default 0 comment '阅读数',
    comment_count        bigint default 0 comment '评论数',
    forward_count        bigint default 0 comment '转发数',
    collect_count        bigint default 0 comment '收藏数',
    trash_count          bigint default 0 comment '屏蔽数',
    update_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    create_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
    primary key (id)
);

/*==============================================================*/
/* Table: blog_collect                                          */
/*==============================================================*/
create table if not exists  blog_collect
(
    id                   bigint not null auto_increment,
    blog_name            varchar(40) comment '博客名',
    blog_id              bigint comment '博客id',
    collect_user_id      bigint comment '收藏者id',
    collect_user_name    varchar(40) comment '收藏者名',
    update_time          timestamp default CURRENT_TIMESTAMP,
    create_time          timestamp default CURRENT_TIMESTAMP,
    primary key (id)
);

alter table blog_collect comment '博客收藏表';

/*==============================================================*/
/* Table: blog_comment                                          */
/*==============================================================*/
create table if not exists  blog_comment
(
    id                   bigint not null auto_increment,
    blog_name            varchar(40) comment '博客名',
    blog_id              bigint comment '博客id',
    comment_user_id      bigint comment '评论者id',
    comment_user_name    varchar(40) comment '评论者名',
    comment              varchar(255) not null comment '评论内容',
    update_time          timestamp default CURRENT_TIMESTAMP,
    create_time          timestamp default CURRENT_TIMESTAMP,
    primary key (id)
);

alter table blog_comment comment '博客评论表';

/*==============================================================*/
/* Table: blog_forward                                          */
/*==============================================================*/
create table if not exists  blog_forward
(
    id                   bigint not null auto_increment,
    blog_name            varchar(40) comment '博客名',
    blog_id              bigint comment '博客id',
    forward_user_id      bigint comment '转发者id',
    forward_user_name    varchar(40) comment '转发者名',
    update_time          timestamp default CURRENT_TIMESTAMP,
    create_time          timestamp default CURRENT_TIMESTAMP,
    primary key (id)
);

alter table blog_forward comment '博客转发表';

/*==============================================================*/
/* Table: blog_like                                             */
/*==============================================================*/
create table if not exists  blog_like
(
    id                   bigint not null auto_increment,
    blog_name            varchar(40) comment '博客名',
    blog_id              bigint comment '博客id',
    like_user_id         bigint comment '点赞者id',
    like_user_name       varchar(40) comment '点赞者名',
    update_time          timestamp default CURRENT_TIMESTAMP,
    create_time          timestamp default CURRENT_TIMESTAMP,
    primary key (id)
);

alter table blog_like comment '博客点赞表';

/*==============================================================*/
/* Table: blog_trash                                            */
/*==============================================================*/
create table if not exists  blog_trash
(
    id                   bigint not null auto_increment,
    blog_name            varchar(40) comment '博客名',
    blog_id              bigint comment '博客id',
    trash_user_id        bigint comment '屏蔽者id',
    trash_user_name      varchar(40) comment '屏蔽者名',
    update_time          timestamp default CURRENT_TIMESTAMP,
    create_time          timestamp default CURRENT_TIMESTAMP,
    primary key (id)
);

alter table blog_trash comment '博客屏蔽表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table if not exists  user
(
    id                   bigint not null auto_increment,
    username             varchar(40) not null,
    passwd               varchar(32) not null,
    update_time          timestamp not null default CURRENT_TIMESTAMP,
    create_time          timestamp not null default CURRENT_TIMESTAMP,
    primary key (id)
);

alter table user comment '用户表';

/*==============================================================*/
/* Table: blog_read                                             */
/*==============================================================*/
create table blog_read
(
    id                   bigint not null auto_increment,
    blog_name            varchar(40) comment '博客名',
    blog_id              bigint comment '博客id',
    read_user_id         bigint comment '阅读者id',
    read_user_name       varchar(40) comment '阅读者名',
    update_time          timestamp default CURRENT_TIMESTAMP,
    create_time          timestamp default CURRENT_TIMESTAMP,
    primary key (id)
);

alter table blog_read comment '博客阅读者表';
