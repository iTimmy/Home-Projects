drop database if exists securitywithdb;
create database securitywithdb;

use securitywithdb;

create table `user`
(
`id` int primary key auto_increment,
`username` varchar
(30) not null unique,
`password` varchar
(100) not null,
`enabled` boolean not null);

create table `role`
(
`id` int primary key auto_increment,
`role` varchar
(30) not null
);

create table `user_role`
(
`user_id` int not null,
`role_id` int not null,
primary key
(`user_id`,`role_id`),
foreign key
(`user_id`) references `user`
(`id`),
foreign key
(`role_id`) references `role`
(`id`));

insert into `user`
    (`id`,`username`,`password`,`enabled`)
    values
(1,"admin", "password", true),
(2,"user","$2a$10$S8nFUMB8YIEioeWyap24/ucX.dC6v9tXCbpHjJVQUkrXlrH1VLaAS", true),
(3,"timmy","tim", true);

insert into `role`(`id`,`role`)
    values
(1,"ROLE_ADMIN"),
(2,"ROLE_USER"),
(3,"ROLE_USER");

insert into `user_role`(`user_id`,`role_id`)
    values
(1,1),
(1,2),
(2,2),
(3,2);



SELECT * FROM USER;