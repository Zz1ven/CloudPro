/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/2/10 12:10:10                           */
/*==============================================================*/


drop table if exists tb_role;

drop table if exists tb_user;

drop table if exists tb_user_role;

/*==============================================================*/
/* Table: tb_role                                               */
/*==============================================================*/
create table tb_role
(
   id                   int not null auto_increment,
   name                 varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   id                   int not null auto_increment,
   username             varchar(50),
   passwrod             varchar(100),
   locked               bool,
   enable               bool,
   time                 datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_user_role                                          */
/*==============================================================*/
create table tb_user_role
(
   id                   int not null auto_increment,
   fk_tb_user_id        int,
   fk_tb_role_id        int,
   primary key (id)
);

alter table tb_user_role add constraint FK_Relationship_1 foreign key (fk_tb_user_id)
      references tb_user (id) on delete restrict on update restrict;

alter table tb_user_role add constraint FK_Relationship_2 foreign key (fk_tb_role_id)
      references tb_role (id) on delete restrict on update restrict;

