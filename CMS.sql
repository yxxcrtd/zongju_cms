/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/11/18 15:24:52                          */
/*==============================================================*/


drop table if exists T_Budget;

drop table if exists T_Category;

drop table if exists T_Channel;

drop table if exists T_Column;

drop table if exists T_Content;

drop table if exists T_Database;

drop table if exists T_Log;

drop table if exists T_Resource;

drop table if exists T_Royalty;

drop table if exists T_TemplateMapping;

drop table if exists T_TemplateNode;

drop table if exists T_User;

drop table if exists T_Watermark;

drop table if exists T_Website;

/*==============================================================*/
/* Table: T_Budget                                              */
/*==============================================================*/
create table T_Budget
(
   BudgetId             int(4) not null auto_increment,
   BudgetItem           varchar(32) not null comment '�������༭�ѡ�У�Էѡ�ӡˢ�ѡ���ѡ��г��ƹ�ѡ�װ���ѵȵ�',
   BudgetPrice          decimal(9,2) not null comment '��λ��Ԫ/ǧ�֡��š�ӡ�š����',
   BudgetQuantity       int not null comment '��λ��ǧ�֡��š�ӡ�š����',
   BudgetMoney          decimal(9,2) comment '��λ��Ԫ��= Ԥ������ X Ԥ�㵥�ۣ���ϵͳ�Զ����㣩',
   BudgetRemarks        varchar(512),
   BudgetCheckMethod    smallint comment '���㷽ʽ��0��������1��ʵ��2���ۿۣ�',
   BudgetCheckMoney     decimal(9,2) comment '����۸�',
   primary key (BudgetId)
);

/*==============================================================*/
/* Table: T_Category                                            */
/*==============================================================*/
create table T_Category
(
   CategoryId           int(4) not null auto_increment,
   CategoryName         varchar(32) not null,
   CategoryParentId     int not null,
   CategoryParentPath   varchar(32) not null,
   CategoryOrderby      smallint,
   primary key (CategoryId)
);

/*==============================================================*/
/* Table: T_Channel                                             */
/*==============================================================*/
create table T_Channel
(
   ChannelId            int(4) not null auto_increment,
   WebsiteId            int(4) not null,
   ChannelTitle         varchar(32) not null,
   ChannelLOGO          varchar(32),
   primary key (ChannelId)
);

/*==============================================================*/
/* Table: T_Column                                              */
/*==============================================================*/
create table T_Column
(
   ColumnId             int(4) not null auto_increment,
   ChannelId            int(4) not null,
   ColumnTitle          varchar(32) not null,
   primary key (ColumnId)
);

/*==============================================================*/
/* Table: T_Content                                             */
/*==============================================================*/
create table T_Content
(
   ContentId            int(4) not null auto_increment,
   ColumnId             int(4) not null,
   ContentTitle         varchar(128) not null,
   ContentContent       text not null,
   ContentViews         int not null,
   ContentCreateTime    timestamp not null,
   primary key (ContentId)
);

/*==============================================================*/
/* Table: T_Database                                            */
/*==============================================================*/
create table T_Database
(
   DatabaseId           int(4) not null auto_increment,
   DatabaseName         varchar(32) not null,
   DatabasePath         varchar(128),
   DatabaseCreateTime   timestamp not null default CURRENT_TIMESTAMP,
   DatabaseCommend      bool not null comment 'Ĭ��0�ǲ��Ƽ���1���Ƽ�',
   primary key (DatabaseId)
);

/*==============================================================*/
/* Table: T_Log                                                 */
/*==============================================================*/
create table T_Log
(
   LogId                int(4) not null auto_increment,
   LogObject            varchar(32) not null,
   LogTitle             varchar(128) not null,
   LogCreateTime        timestamp not null default CURRENT_TIMESTAMP,
   primary key (LogId)
);

/*==============================================================*/
/* Table: T_Resource                                            */
/*==============================================================*/
create table T_Resource
(
   ResourceId           int(4) not null auto_increment,
   CategoryId           int(4),
   ResourceName         varchar(128) not null,
   ResourceAuthor       varchar(32) not null,
   ResourceISBN         varchar(32),
   ResourcePrice        decimal(9,2) not null,
   ResourceRoyalty      smallint not null,
   ResourcePublisher    varchar(32),
   ResourcePublishDate  date,
   ResourceEdition      varchar(32),
   ResourcePage         int,
   ResourceFrame        varchar(32),
   ResourceFormat       varchar(32),
   ResourceSheet        smallint,
   ResourceFile         varchar(256) not null,
   ResourceLocation     varchar(32) not null,
   ResourceCover     	varchar(32) not null,
   ResourceCreateTime   timestamp not null default CURRENT_TIMESTAMP,
   primary key (ResourceId)
);

/*==============================================================*/
/* Table: T_Royalty                                             */
/*==============================================================*/
create table T_Royalty
(
   RoyaltyId            int(4) not null auto_increment,
   ResourceId           int(4) not null,
   RoyaltyTotal         int not null comment 'ӡ��������',
   RoyaltyMoney         decimal(9,2) comment '��˰��� = ��Դ�ĵ��� x ��Դ��ӡ�������� x ��˰��',
   primary key (RoyaltyId)
);

/*==============================================================*/
/* Table: T_TemplateMapping                                     */
/*==============================================================*/
create table T_TemplateMapping
(
   TemplateMappingId    int(4) not null auto_increment,
   TemplateMappingUserTemplateId int not null,
   TemplateMappingTargetTemplateId int not null,
   primary key (TemplateMappingId)
);

/*==============================================================*/
/* Table: T_TemplateNode                                        */
/*==============================================================*/
create table T_TemplateNode
(
   TemplateNodeId       int(4) not null auto_increment,
   TemplateNodeCode     varchar(64),
   TemplateNodePath     varchar(256) not null,
   TemplateNodeName     varchar(32) not null,
   TemplateNodeValue    varchar(1024),
   TemplateNodeOriginalName varchar(128),
   templateNodeParent   smallint,
   TemplateNodeTypeId   smallint,
   primary key (TemplateNodeId)
);

/*==============================================================*/
/* Table: T_User                                                */
/*==============================================================*/
create table T_User
(
   UserId               int(4) not null auto_increment comment '�û�ID',
   Username             varchar(32) not null comment '�û���',
   UserPassword         varchar(32) not null comment '�û�����',
   UserStatus           smallint not null comment '�û�״̬��0��δ��ˣ�1������ˣ�',
   UserRole             smallint not null comment '�û���ɫ��0����ͨ�û���1��������',
   UserCreateTime       timestamp not null comment '�û�����ʱ��',
   primary key (UserId)
);

/*==============================================================*/
/* Table: T_Watermark                                           */
/*==============================================================*/
create table T_Watermark
(
   WatermarkId          int(4) not null auto_increment,
   WatermarkName        varchar(32) not null,
   WatermarkFont        smallint not null,
   WatermarkActive      bool not null,
   WatermarkCreateTime  timestamp not null,
   primary key (WatermarkId)
);

/*==============================================================*/
/* Table: T_Website                                             */
/*==============================================================*/
create table T_Website
(
   WebsiteId            int(4) not null auto_increment,
   WebsiteName          varchar(32) not null,
   WebsiteTitle         varchar(32) not null,
   WebsiteLOGO          varchar(32),
   primary key (WebsiteId)
);

alter table T_Channel add constraint FK_WebsiteIncludeChannel foreign key (WebsiteId)
      references T_Website (WebsiteId) on delete restrict on update restrict;

alter table T_Column add constraint FK_ChannelIncludeColumns foreign key (ChannelId)
      references T_Channel (ChannelId) on delete restrict on update restrict;

alter table T_Content add constraint FK_ColumnIncludeContents foreign key (ColumnId)
      references T_Column (ColumnId) on delete restrict on update restrict;

alter table T_Resource add constraint FK_CategoryIncludeResource foreign key (CategoryId)
      references T_Category (CategoryId) on delete restrict on update restrict;

alter table T_Royalty add constraint FK_ResourceOnlyHasRoyalty foreign key (ResourceId)
      references T_Resource (ResourceId) on delete restrict on update restrict;

