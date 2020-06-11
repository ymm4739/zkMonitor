drop table  if exists history;
create table if not exists history (
id  bigint default 0 auto_increment primary key,
change_user varchar(100),
create_time timestamp,
change_summary varchar(500),
change_ip varchar(500),
change_time timestamp
);