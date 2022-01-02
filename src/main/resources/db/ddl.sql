create database if not exists study;


create table if not exists user
(
    id          bigint auto_increment
        primary key,
    username    varchar(24) not null,
    passwd      varchar(32) not null,
    create_time timestamp   not null,
    update_time timestamp   not null
);

create table if not exists blog
(
    id            bigint auto_increment primary key,
    blog_name     varchar(50)  not null,
    blog_tag      varchar(20),
    blog_content  varchar(100) not null,
    blog_pic      varchar(100),
    read_count    int(10),
    like_count    int(10),
    comment_count int(10),
    forward_count int(10),

    create_time   timestamp default current_timestamp,
    update_time   timestamp default current_timestamp
)


