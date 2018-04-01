-- 创建user表
CREATE TABLE USER(
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	username VARCHAR(100) DEFAULT '',
	NAME VARCHAR(100) DEFAULT '',
	age INT,
	balance DECIMAL(10,2)
); 

insert into user VALUES(1,'zhangsan','张三',24,100.00);
insert into user VALUES(2,'lisi','李四',20,100.00);
insert into user VALUES(3,'wangwu','王五',28,100.00);
insert into user VALUES(4,'zhaoliu','赵六',14,100.00);

select * from user;

----------------------------------------------------
CREATE TABLE movie(
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	moviename varchar(200) default '',
	screendate DATETIME
);

insert into movie (moviename,screendate) values ('白雪公主',NOW());

------------------------------------------------
create table movie_order(
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	userid bigint not null,
	movieid bigint not null,
	cinemaid bigint not null,
	num int not null
);
select * from movie_order;
--------------------------------------------------
create table cinema(
	id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name varchar(200) default ''
)

select * from CINEMA;
insert into CINEMA (NAME) values ('ab影院');
-------------------------------------------------
create table cinema_movie_stock(
	stockid BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	cinemaid bigint not null,	
	movieid bigint not null,
	quantity int
);
select * from cinema_movie_stock;
insert into cinema_movie_stock (cinemaid,movieid,quantity) values (2,1,2);

/*
 * 一、用户买票(userid, cinemaid, movieid, num)
 * 	1. 修改库存 
 * 	2. 生成订单
 */
---------------------------------------------------------------

