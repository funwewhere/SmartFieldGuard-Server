/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/8/19 23:46:12                           */
/*==============================================================*/


drop table if exists t_crop_info;

drop table if exists t_crop_period;

drop table if exists t_crop_type;

drop table if exists t_crop_variable;

drop table if exists t_field_history;

drop table if exists t_field_info;

drop table if exists t_message_record;

drop index Index_1 on t_post_info;

drop table if exists t_post_info;

drop index Index_1 on t_reply_info;

drop table if exists t_reply_info;

drop table if exists t_sys_table_key;

drop table if exists t_task_info;

drop table if exists t_task_trigger;

drop table if exists t_user_info;

drop table if exists t_waiting_for_inform;

/*==============================================================*/
/* Table: t_crop_info                                           */
/*==============================================================*/
create table t_crop_info
(
   crop_no              varchar(20) not null,
   crop_type_no         varchar(20) not null,
   crop_name            varchar(40) not null,
   image_urls           varchar(300),
   intro                varchar(400),
   temperature          varchar(400),
   light                varchar(400),
   water                varchar(400),
   soil                 varchar(400),
   grow_days            int,
   primary key (crop_no)
);

/*==============================================================*/
/* Table: t_crop_period                                         */
/*==============================================================*/
create table t_crop_period
(
   period_no            varchar(20) not null,
   crop_no              varchar(20) not null,
   period_name          varchar(25) not null,
   primary key (period_no, crop_no),
   key AK_Key_2 (period_name)
);

/*==============================================================*/
/* Table: t_crop_type                                           */
/*==============================================================*/
create table t_crop_type
(
   crop_type_no         varchar(20) not null,
   crop_type_name       varchar(25) not null,
   parent_type          varchar(20) not null,
   is_last              bool not null default true,
   image_url            varchar(120),
   primary key (crop_type_no)
);

/*==============================================================*/
/* Table: t_crop_variable                                       */
/*==============================================================*/
create table t_crop_variable
(
   crop_no              varchar(20) not null,
   variable_name        varchar(25) not null,
   period_no            varchar(20) not null default 'all',
   variable_value       varchar(50) not null,
   primary key (crop_no, variable_name, period_no)
);

/*==============================================================*/
/* Table: t_field_history                                       */
/*==============================================================*/
create table t_field_history
(
   field_history_no     int not null auto_increment,
   field_no             varchar(20) not null,
   crop_no              varchar(20),
   record_time          datetime not null,
   soil_t               double,
   soil_w               double,
   air_t                double,
   air_w                double,
   light                double,
   CO2                  double,
   primary key (field_history_no)
);

/*==============================================================*/
/* Table: t_field_info                                          */
/*==============================================================*/
create table t_field_info
(
   field_no             varchar(20) not null,
   user_id              varchar(20) not null,
   field_name           varchar(20),
   field_area           double,
   device_mac           varchar(25) not null,
   longitude            double,
   latitude             double,
   crop_no              varchar(20),
   start_time           datetime,
   soil_t               double,
   soil_w               double,
   air_t                double,
   air_w                double,
   CO2                  double,
   light                double,
   last_time            datetime,
   status               varchar(2) not null default '0',
   primary key (field_no)
);

/*==============================================================*/
/* Table: t_message_record                                      */
/*==============================================================*/
create table t_message_record
(
   message_no           varchar(20) not null,
   send_user            varchar(20) not null,
   receive_user         varchar(20) not null,
   type                 varchar(2) not null,
   content              text not null,
   is_read              char(1) not null default 'N',
   send_date            datetime not null,
   primary key (message_no)
);

/*==============================================================*/
/* Table: t_post_info                                           */
/*==============================================================*/
create table t_post_info
(
   post_no              varchar(20) not null,
   user_id              varchar(20) not null,
   type                 varchar(2),
   parent_area          varchar(20),
   title                varchar(35),
   content              text not null,
   images               varchar(200),
   best_reply_no        varchar(20),
   reply_num            int not null default 0,
   status               varchar(2) not null default '0',
   create_date          datetime not null,
   last_reply_date      datetime not null,
   primary key (post_no)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on t_post_info
(
   user_id
);

/*==============================================================*/
/* Table: t_reply_info                                          */
/*==============================================================*/
create table t_reply_info
(
   post_no              varchar(20) not null,
   reply_sn             int not null,
   user_id              varchar(20) not null,
   content              text not null,
   images               varchar(200),
   agree_num            int not null default 0,
   agree_users          text,
   reply_date           datetime not null,
   primary key (post_no, reply_sn)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on t_reply_info
(
   post_no
);

/*==============================================================*/
/* Table: t_sys_table_key                                       */
/*==============================================================*/
create table t_sys_table_key
(
   table_name           varchar(20) not null,
   table_key            varchar(20),
   memo                 varchar(100),
   primary key (table_name)
);

alter table t_sys_table_key comment '记录数据库各个表的主键最大值';

/*==============================================================*/
/* Table: t_task_info                                           */
/*==============================================================*/
create table t_task_info
(
   task_no              varchar(20) not null,
   crop_no              varchar(20) not null,
   task_name            varchar(30) not null,
   task_formula         varchar(700) not null,
   last_task            varchar(20) not null,
   task_type            varchar(2) not null,
   start                int not null,
   use_days             int not null,
   period_no            varchar(20),
   attention            varchar(300),
   primary key (task_no)
);

/*==============================================================*/
/* Table: t_task_trigger                                        */
/*==============================================================*/
create table t_task_trigger
(
   task_no              varchar(20) not null,
   trigger_formula      varchar(30) not null,
   trigger_than         varchar(20) not null,
   trigger_value        varchar(50) not null,
   primary key (task_no, trigger_formula)
);

/*==============================================================*/
/* Table: t_user_info                                           */
/*==============================================================*/
create table t_user_info
(
   user_id              varchar(20) not null,
   username             varchar(30) not null,
   tel                  varchar(20) not null,
   head_image           varchar(120),
   password             varchar(255) not null,
   salt                 varchar(10),
   birthday             date,
   role                 varchar(2) not null default '0',
   status               varchar(2) not null default '1',
   register_date        datetime not null,
   check_code           varchar(60),
   token                varchar(40),
   create_token_date    datetime,
   last_active_date     datetime not null,
   primary key (user_id)
);

/*==============================================================*/
/* Table: t_waiting_for_inform                                  */
/*==============================================================*/
create table t_waiting_for_inform
(
   inform_flow_no       int not null auto_increment,
   inform_user          varchar(20) not null,
   type                 varchar(2) not null,
   no                   varchar(20),
   sno                  varchar(20),
   create_date          datetime not null,
   memo                 varchar(100),
   primary key (inform_flow_no)
);

