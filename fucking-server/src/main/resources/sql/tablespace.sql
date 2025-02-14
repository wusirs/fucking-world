# 1、创建数据库
#指定utf8字符创建
create database blbl character set utf8;
create database common character set utf8;

# 2、创建用户
#创建用户userwordpress，仅限本机访问，密码123456
# create user 'WL'@'localhost' identified by '1234zxcv';
#创建用户userwordpress，仅限192.168.10.52访问，密码123456
# create user 'WL'@'192.168.10.52' identified by '1234zxcv';
#创建用户userwordpress，所有IP可以访问，密码123456
create user 'WL'@'%' identified by '1234zxcv';

# 3、授权用户
#将数据库wordpress下的所有（*）授权给userwordpress
grant all privileges on blbl.* to 'WL'@'%';
grant all privileges on common.* to 'WL'@'%';
grant all privileges on tuling.* to 'WL'@'%';
#最后刷新权限
flush privileges;


# 4、直接一步到位
# 或者 这种方法 ：创建并授权用户，是二和三的合并。
grant all on wl.* to 'WL'@'%' identified by '1234zxcv';
flush privileges;


create database nacos character set utf8;
create user 'nacos'@'%' identified by 'nacos';
grant all privileges on nacos.* to 'nacos'@'%';
flush privileges;



create database ssm character set utf8;
grant all privileges on ssm.* to 'WL'@'%';
