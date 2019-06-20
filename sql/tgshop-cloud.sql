/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/6/20 14:51:15                           */
/*==============================================================*/


drop table if exists item_attribute;

drop table if exists item_attribute_attrvalue_link;

drop table if exists item_attribute_value;

drop table if exists item_brand;

drop table if exists item_category;

drop table if exists item_category_attr_value;

drop table if exists item_category_attribute;

drop table if exists item_category_brand;

drop table if exists item_freight_charge_template;

drop table if exists item_goods;

drop table if exists item_goods_attribute;

drop table if exists item_goods_attribute_value;

drop table if exists item_goods_group;

drop table if exists item_goods_sku;

drop table if exists item_goods_sku_attribute;

drop table if exists item_goods_sku_attribute_value;

drop table if exists item_goods_sku_inventory;

drop table if exists item_goods_sku_inventory_log;

drop table if exists item_goods_sku_picture;

drop table if exists item_goods_sku_price;

drop table if exists item_goods_sku_price_log;

drop table if exists item_goods_upload_picture;

drop table if exists item_member;

drop table if exists item_message_queue;

drop table if exists item_message_queue_log;

drop table if exists item_spec_group;

drop table if exists platform_Admin;

drop table if exists platform_menu;

drop table if exists platform_role;

drop table if exists platform_role_menu;

drop table if exists shop_attr_value;

drop table if exists shop_attribute;

drop table if exists shop_brand;

drop table if exists shop_category;

drop table if exists shop_category_attribute;

drop table if exists shop_category_attribute_value;

drop table if exists shop_seller;

drop table if exists shop_store;

drop table if exists stock_inventory_manager;

drop table if exists stock_item_in;

drop table if exists stock_item_out;

drop table if exists stock_trade_log;

drop table if exists stock_warehouse;

drop table if exists stock_warehouse_sku;

drop table if exists stock_warehouse_supply;

drop table if exists stock_warehouse_supply_detail;

drop table if exists stock_warehouse_supply_log;

drop table if exists store_menu;

drop table if exists store_role;

drop table if exists store_role_menu;

drop table if exists trade_cart;

drop table if exists trade_order;

drop table if exists trade_order_address;

drop table if exists trade_order_items;

drop table if exists trade_order_log;

drop table if exists trade_user_receive_address;

/*==============================================================*/
/* Table: item_attribute                                        */
/*==============================================================*/
create table item_attribute
(
   attr_id              varchar(32) not null comment '属性ID',
   attr_name            varchar(100) not null comment '属性名',
   show_name            varchar(100) not null comment '显示名称',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (attr_id)
);

alter table item_attribute comment '属性';

/*==============================================================*/
/* Table: item_attribute_attrvalue_link                         */
/*==============================================================*/
create table item_attribute_attrvalue_link
(
   attribute_value_id   varchar(32) not null comment 'id',
   attribute_id         varchar(32) not null comment '属性id',
   value_id             varchar(32) not null comment '属性值id',
   sort_num             int not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (attribute_value_id)
);

alter table item_attribute_attrvalue_link comment '属性--属性值关系';

/*==============================================================*/
/* Table: item_attribute_value                                  */
/*==============================================================*/
create table item_attribute_value
(
   value_id             varchar(32) not null comment '属性值ID',
   attribute_id         varchar(32) not null comment '属性id',
   value_name           varchar(100) not null comment '属性值名称',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (value_id)
);

alter table item_attribute_value comment '属性值';

/*==============================================================*/
/* Table: item_brand                                            */
/*==============================================================*/
create table item_brand
(
   brand_id             varchar(32) not null comment '品牌ID',
   brand_name           varchar(100) not null comment '品牌名',
   brand_name_en        varchar(100) comment '英文名',
   logo_url             varchar(256) comment 'logoUrl',
   log_back_url         varchar(256) comment '背景图片',
   initial              varchar(1) comment '首字母',
   weight               int default 0 comment '排序权重',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (brand_id)
);

alter table item_brand comment '品牌';

/*==============================================================*/
/* Table: item_category                                         */
/*==============================================================*/
create table item_category
(
   category_id          varchar(32) not null comment '类目ID',
   category_name        varchar(200) not null comment '类目名称',
   has_leaf             int not null default 1 comment '是否包含叶子结点,0 否 1 是',
   lev                  int not null default 1 comment '1:一级类目;2:二级类目;3:三级类目',
   parent_category_id   varchar(32) not null default '0' comment '父类目ID',
   sort_number          int not null default 0 comment '排序 排序号越小越靠前',
   home_show            int not null default 1 comment '首页显示 1:是;2:否',
   root_path            varchar(256) not null default '' comment '到跟节点路径id,分隔',
   root_path_json       varchar(1000) not null default '' comment '路径值JSON',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (category_id)
);

alter table item_category comment '类目';

/*==============================================================*/
/* Table: item_category_attr_value                              */
/*==============================================================*/
create table item_category_attr_value
(
   category_attr_val_id varchar(32) not null comment '类目属性值id',
   category_attribute_id varchar(32) not null comment '类目属性ID',
   attribute_id         varchar(32) not null comment '属性ID',
   category_id          varchar(32) not null comment '类目ID',
   value_id             varchar(32) not null comment '属性值ID',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (category_attr_val_id)
);

alter table item_category_attr_value comment '商品类别属性值关系';

/*==============================================================*/
/* Table: item_category_attribute                               */
/*==============================================================*/
create table item_category_attribute
(
   tb_id                varchar(32) not null comment 'ID',
   category_id          varchar(32) not null comment '类目ID',
   attribute_id         varchar(32) not null comment '属性ID',
   attr_type            int not null default 0 comment '属性类型:1:销售属性;0:非销售属性',
   option_type          int not null default 1 comment '是否必填。1：必填；2：非必填',
   select_type          int not null comment '是否多选。1：单选；2：多选',
   sort_number          int not null default 0 comment '排序号。越小越靠前',
   json_value           varchar(3000) default '' comment ' json属性值',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_category_attribute comment '商品类别属性关系';

/*==============================================================*/
/* Table: item_category_brand                                   */
/*==============================================================*/
create table item_category_brand
(
   category_brand_id    varchar(32) not null comment 'id',
   brand_id             varchar(32) not null comment '品牌id',
   category_lev2_id     varchar(32) not null comment '二级类目id',
   category_lev3_id     varchar(32) comment '三级类目id',
   sort_num             int not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (category_brand_id)
);

alter table item_category_brand comment '类目——品牌关系';

/*==============================================================*/
/* Table: item_freight_charge_template                          */
/*==============================================================*/
create table item_freight_charge_template
(
   template_id          varchar(32) not null comment '运费id',
   store_id             varchar(32) not null default '0' comment '店铺id',
   template_name        varchar(100) not null comment '运费名称',
   template_type        int not null default 1 comment '类型 1 免邮 2 满额包邮 3 支付免邮 4 不包邮',
   fulfil_price         decimal(14,2) not null default 0 comment '满额',
   freight_price        decimal(14,2) not null default 0 comment '运费',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (template_id)
);

alter table item_freight_charge_template comment '运费模板';

/*==============================================================*/
/* Table: item_goods                                            */
/*==============================================================*/
create table item_goods
(
   goods_id             varchar(32) not null comment '商品ID',
   goods_name           varchar(200) not null comment '商品名',
   keywords             varchar(200) comment '关键字',
   goods_status         int(4) not null default 1 comment '商品状态,1: 未发布，2：待审核，3：审核驳回，4：待上架，5：在售，6：已下架，7：锁定， 8： 申请解锁',
   area_id              varchar(32) not null default '0' comment '区域ID',
   brand_id             varchar(32) not null default '0' comment '品牌',
   category_id          varchar(32) not null default '0' comment '类目',
   brand_name           varchar(100) not null default '其他' comment '品牌名',
   category_name        varchar(100) not null default '其他' comment '类目名',
   goods_sn             varchar(100) not null comment '商品货号',
   listing_time         datetime comment '上架时间',
   off_show_time        datetime comment '下架时间',
   describe_url         varchar(3000) comment '商品描述',
   seller_id            varchar(32) not null comment '商家ID',
   store_id             varchar(32) not null comment '店铺Id',
   shop_name            varchar(200) not null comment '店铺名称',
   product_origin       varchar(200) comment '原产地',
   item_attribute_json_value varchar(3500) comment '商品属性值JSON',
   item_sale_attribute_json_value varchar(2500) comment '商品销售属性值',
   spec_open            int not null default 0 comment '开启规格 1开启',
   spec_size_open       int not null default 0 comment '开启尺码属性 1开启',
   default_sku_id       varchar(32) comment '默认商品sku',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (goods_id)
);

alter table item_goods comment '商品信息表SPU';

/*==============================================================*/
/* Table: item_goods_attribute                                  */
/*==============================================================*/
create table item_goods_attribute
(
   tb_id                varchar(32) not null comment '表id',
   attr_id              varchar(32) not null comment '属性ID 表 item_attribute',
   attr_name            varchar(50) not null comment '属性名称',
   goods_id             varchar(32) not null comment '商品ID',
   attr_type            int(11) not null default 1 comment '属性类型:1:销售属性;2:非销售属性',
   sort_number          int(11) not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_goods_attribute comment '商品属性 spu 所有的 属性';

/*==============================================================*/
/* Table: item_goods_attribute_value                            */
/*==============================================================*/
create table item_goods_attribute_value
(
   tb_id                varchar(32) not null comment '表id',
   attr_id              varchar(32) not null comment '属性ID 表 item_attribute',
   value_id             varchar(32) not null comment '属性值ID 表 shop_attr_value',
   goods_id             varchar(32) not null comment '商品ID',
   attr_name            varchar(50) not null comment '属性名称',
   attr_value_name      varchar(50) not null comment '属性值名称',
   attr_type            int(11) not null default 1 comment '属性类型:1:销售属性;2:非销售属性',
   sort_number          int(11) not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_goods_attribute_value comment '商品属性值 spu 所有的属性值';

/*==============================================================*/
/* Table: item_goods_group                                      */
/*==============================================================*/
create table item_goods_group
(
   group_id             varchar(32) not null comment '分组id',
   group_name           varchar(100) not null comment '分组名称',
   store_id             varchar(32) not null comment '店铺id',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (group_id)
);

alter table item_goods_group comment '商品分组';

/*==============================================================*/
/* Table: item_goods_sku                                        */
/*==============================================================*/
create table item_goods_sku
(
   sku_id               varchar(32) not null comment '表id',
   goods_id             varchar(32) not null comment '商品ID',
   seller_id            varchar(32) not null comment '卖家ID',
   store_id             varchar(32) not null comment '店铺ID',
   area_id              varchar(32) not null default '0' comment '区域id',
   sku_goods_name       varchar(200) not null comment 'SKU商品名称',
   sku_no               varchar(32) not null comment '商品编号',
   attr_value_json      varchar(2000) comment '销售属性JSON',
   color_attr_val_id    varchar(32) comment '颜色属性id',
   color_attr_val_name  varchar(50) comment '颜色属性值名称',
   size_attr_val_id     varchar(32) comment '尺码属性值id',
   size_attr_val_name   varchar(50) comment '尺码属性值名称',
   sku_type             int(11) not null default 1 comment 'sku类型 1:主sku,2:非主sku',
   sku_status           int(11) not null default 1 comment 'SKU状态  1有效   2无效',
   spec_face_pictures   varchar(300) comment '封面图url',
   picture_main         varchar(3200) comment '主图',
   picture_description  varchar(3200) comment '详情图 商品描述',
   template_id          varchar(32) not null default '0' comment '运费模板',
   volume               decimal(14,2) not null default 0 comment '体积',
   weight               decimal(14,2) not null default 0 comment '重量',
   item_size            varchar(200) comment '尺寸',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (sku_id)
);

alter table item_goods_sku comment '商品规格';

/*==============================================================*/
/* Table: item_goods_sku_attribute                              */
/*==============================================================*/
create table item_goods_sku_attribute
(
   tb_id                varchar(32) not null comment '表id',
   attr_id              varchar(32) not null comment '属性ID 表 item_attribute',
   attr_name            varchar(50) not null comment '属性名称',
   goods_id             varchar(32) not null comment '商品id',
   sku_id               varchar(32) not null comment 'SKU ID',
   attr_type            int(11) not null default 1 comment '属性类型:1:销售属性;2:非销售属性',
   sort_number          int(11) not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_goods_sku_attribute comment '商品规格属性 sku的属性';

/*==============================================================*/
/* Table: item_goods_sku_attribute_value                        */
/*==============================================================*/
create table item_goods_sku_attribute_value
(
   tb_id                varchar(32) not null comment '表id',
   attr_id              varchar(32) not null comment '属性ID 表 item_attribute',
   value_id             varchar(32) not null comment '属性值ID 表 shop_attr_value',
   attr_name            varchar(50) not null comment '属性名称',
   attr_value           varchar(50) not null comment '属性值名称',
   goods_id             varchar(32) not null comment '商品id',
   sku_id               varchar(32) not null comment 'SKU ID',
   attr_type            int(11) not null default 1 comment '属性类型:1:销售属性;2:非销售属性',
   sort_number          int(11) not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_goods_sku_attribute_value comment '商品规格属性值 sku 的属性值';

/*==============================================================*/
/* Table: item_goods_sku_inventory                              */
/*==============================================================*/
create table item_goods_sku_inventory
(
   sku_id               varchar(32) not null comment '库存id skuId',
   area_id              varchar(32) not null default '0' comment '区域id',
   store_id             varchar(32) not null comment '店铺',
   seller_id            varchar(32) not null comment '卖家id',
   goods_id             varchar(32) not null comment '商品id',
   sku_no               varchar(100) not null comment '商品编号',
   inventory_num        int not null default 0 comment '库存数量',
   lock_num             int not null default 0 comment '锁定数量',
   left_num             int not null default 0 comment '可用数量',
   remark               varchar(200) comment '备注',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (sku_id)
);

alter table item_goods_sku_inventory comment '商品SKU库存';

/*==============================================================*/
/* Table: item_goods_sku_inventory_log                          */
/*==============================================================*/
create table item_goods_sku_inventory_log
(
   tb_id                varchar(32) not null comment '表id',
   sku_id               varchar(32) not null comment 'skuId',
   area_id              varchar(32) not null default '0' comment '区域id',
   store_id             varchar(32) not null comment '店铺',
   seller_id            varchar(32) not null comment '卖家id',
   goods_id             varchar(32) not null comment '商品id',
   sku_no               varchar(100) not null comment '商品编号',
   inventory_num        int not null comment '库存数量',
   lock_num             int not null comment '锁定数量',
   left_num             int not null comment '可用数量',
   remark               varchar(200) comment '备注',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_goods_sku_inventory_log comment '商品SKU库存日志';

/*==============================================================*/
/* Table: item_goods_sku_picture                                */
/*==============================================================*/
create table item_goods_sku_picture
(
   tb_id                varchar(32) not null comment '表id',
   goods_id             varchar(32) not null comment '商品id',
   sku_id               varchar(32) not null comment 'sku_id',
   picture_id           varchar(32) not null comment '图片id',
   picture_description  varchar(300) not null comment '图片url',
   picture_type         int not null default 1 comment '类型  1封面图 2 主图 3 详情图',
   sort_num             int not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_goods_sku_picture comment '商品SKU图';

/*==============================================================*/
/* Table: item_goods_sku_price                                  */
/*==============================================================*/
create table item_goods_sku_price
(
   sku_id               varchar(32) not null comment '表id 即skuid',
   area_id              varchar(32) not null default '0' comment '区域id',
   store_id             varchar(32) not null comment '店铺',
   seller_id            varchar(32) not null comment '卖家id',
   goods_id             varchar(32) not null comment '商品id',
   sku_no               varchar(100) not null comment '商品编号',
   cost_price           decimal(14,2) not null default 0 comment '成本价格',
   market_price         decimal(14,2) not null default 0 comment '市场价',
   sale_price           decimal(14,2) not null default 0 comment '销售价格',
   plus_price_open      int not null default 0 comment '开启plus价格 0 否 1是',
   plus_price           decimal(14,2) not null default 0 comment 'plus价格',
   super_vip_price_open int not null default 0 comment '开启超级会员价 0 否 1是',
   super_vip_price      decimal(14,2) not null default 0 comment '超级会员价',
   remark               varchar(200) comment '备注',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (sku_id)
);

alter table item_goods_sku_price comment '商品SKU价格';

/*==============================================================*/
/* Table: item_goods_sku_price_log                              */
/*==============================================================*/
create table item_goods_sku_price_log
(
   tb_id                varchar(32) not null comment '表id',
   price_id             varchar(32) not null comment '价格id',
   area_id              varchar(32) not null default '0' comment '区域id',
   store_id             varchar(32) not null comment '店铺',
   seller_id            varchar(32) not null comment '卖家id',
   sku_id               varchar(32) not null comment '商品id',
   sku_no               varchar(100) not null comment '商品编号',
   cost_price           decimal(14,2) not null comment '成本价格',
   market_price         decimal(14,2) not null comment '市场价',
   sale_price           decimal(14,2) not null comment '销售价格',
   plus_price_open      int not null default 0 comment '开启plus价格 0 否 1是',
   plus_price           decimal(14,2) not null default 0 comment 'plus价格',
   super_vip_price_open int not null default 0 comment '开启超级会员价 0 否 1是',
   super_vip_price      decimal(14,2) not null default 0 comment '超级会员价',
   remark               varchar(200) comment '备注',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_goods_sku_price_log comment '商品SKU价格日志';

/*==============================================================*/
/* Table: item_goods_upload_picture                             */
/*==============================================================*/
create table item_goods_upload_picture
(
   picture_id           varchar(32) not null comment '商品图片id 主键',
   store_id             varchar(32) not null comment '店铺id',
   goods_id             varchar(32) not null comment '商品id',
   picture_name         varchar(100) not null comment '图片名称',
   picture_url          varchar(200) not null comment '图片url',
   src_md5              varchar(50) comment '源文件md5',
   pic_md5              varchar(50) comment '图片MD5',
   file_size            int comment '图片大小',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (picture_id)
);

alter table item_goods_upload_picture comment '商品上传的图片';

/*==============================================================*/
/* Table: item_member                                           */
/*==============================================================*/
create table item_member
(
   member_id            varchar(32) not null comment '会员id 主键',
   login_name           varchar(20) not null comment '会员名称',
   password             varchar(50) not null comment '密码',
   nick_name            varchar(20) not null comment '昵称',
   sex                  int not null default 2 comment '性别',
   birthday             date comment '生日',
   phone                varchar(20) comment '手机',
   mail                 varchar(50) comment '邮箱',
   email_valid          int default 0 comment '邮箱验证 0未验证 1已验证',
   head_img             varchar(100) comment '头像',
   grade                int not null default 0 comment '会员等级 0 普通 1: vip1 2:vip2 10:plus',
   plus                 int default 0 comment 'plus会员 0 不是 1 是',
   weichat              varchar(100) comment '绑定微信',
   id_card              varchar(20) comment '身份证',
   id_card_valid        int default 0 comment '身份认证 0未验证 1已验证',
   status               int not null default 0 comment ' 状态 0 正常 1 禁止登录',
   last_login_time      datetime comment '最后登录时间',
   last_login_ip        varchar(16) comment '最后登录ip',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (member_id)
);

alter table item_member comment '会员';

/*==============================================================*/
/* Table: item_message_queue                                    */
/*==============================================================*/
create table item_message_queue
(
   msg_id               varchar(32) not null comment '消息id 主键',
   msg_type             int not null default 0 comment '消息类型',
   ref_key              varchar(100) not null comment '关联键',
   delivery_tag         bigint not null default 0 comment '消息投递号',
   msg_state            int not null default 0 comment '消息状态',
   message_data         varchar(2000) comment '消息数据json',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (msg_id)
);

alter table item_message_queue comment '消息队列';

/*==============================================================*/
/* Table: item_message_queue_log                                */
/*==============================================================*/
create table item_message_queue_log
(
   tb_id                varchar(32) not null comment '主键',
   msg_id               varchar(32) not null comment '消息id ',
   msg_type             int not null default 0 comment '消息类型',
   ref_key              varchar(100) not null comment '关联键',
   delivery_tag         bigint not null default 0 comment '消息投递号',
   msg_state            int not null default 0 comment '消息状态',
   message_data         varchar(2000) comment '消息数据json',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table item_message_queue_log comment '消息队列日志';

/*==============================================================*/
/* Table: item_spec_group                                       */
/*==============================================================*/
create table item_spec_group
(
   t_id                 varchar(32) not null comment 'id 主键',
   item_group_id        varchar(32) not null comment '商品分组id',
   goods_id             varchar(32) not null comment '商品id',
   item_sku_id          varchar(32) not null comment '商品sku id',
   store_id             varchar(32) not null comment '店铺id',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (t_id)
);

alter table item_spec_group comment '商品sku分组 单规格商品选规格时用';

/*==============================================================*/
/* Table: platform_Admin                                        */
/*==============================================================*/
create table platform_Admin
(
   admin_id             varchar(32) not null,
   login_name           varchar(20) not null comment '会员名称',
   password             varchar(50) not null comment '密码',
   super_admin          int not null default 0 comment '超级管理员 1是 0 否',
   role_id              varchar(32) comment '角色id',
   nick_name            varchar(20) not null comment '昵称',
   sex                  int not null default 2 comment '性别',
   birthday             date comment '生日',
   phone                varchar(20) comment '手机',
   mail                 varchar(50) comment '邮箱',
   email_valid          int default 0 comment '邮箱验证 0未验证 1已验证',
   head_img             varchar(100) comment '头像',
   grade                int not null default 0 comment '会员等级 0 普通 1: vip1 2:vip2 10:plus',
   plus                 int default 0 comment 'plus会员 0 不是 1 是',
   weichat              varchar(100) comment '绑定微信',
   id_card              varchar(20) comment '身份证',
   id_card_valid        int default 0 comment '身份认证 0未验证 1已验证',
   status               int not null default 0 comment ' 状态 0 正常 1 禁止登录',
   last_login_time      datetime comment '最后登录时间',
   last_login_ip        varchar(16) comment '最后登录ip',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (admin_id)
);

alter table platform_Admin comment '平台管理员';

/*==============================================================*/
/* Table: platform_menu                                         */
/*==============================================================*/
create table platform_menu
(
   menu_id              varchar(32) not null,
   menu_name            varchar(100) not null,
   url                  varchar(200) not null,
   parent_id            varchar(32) not null,
   sort                 int not null default 0 comment '排序',
   memu_level           int not null default 0 comment '等级',
   id_path              varchar(200) not null comment '路径',
   description          varchar(200) comment '描述',
   permission           varchar(100) comment '权限',
   is_show              int not null default 0 comment '是否显示：0,否;1,是',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (menu_id)
);

alter table platform_menu comment '平台菜单';

/*==============================================================*/
/* Table: platform_role                                         */
/*==============================================================*/
create table platform_role
(
   role_id              varchar(32) not null,
   rule_name            varchar(100) comment '角色名称',
   description          varchar(200) comment '角色描述',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (role_id)
);

alter table platform_role comment '平台权限角色';

/*==============================================================*/
/* Table: platform_role_menu                                    */
/*==============================================================*/
create table platform_role_menu
(
   role_menu_id         varchar(32) not null comment 'role_menu_id',
   role_id              varchar(32) not null comment '角色Id',
   menu_id              varchar(32) not null comment '权限id',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (role_menu_id)
);

alter table platform_role_menu comment '平台角色--权限';

/*==============================================================*/
/* Table: shop_attr_value                                       */
/*==============================================================*/
create table shop_attr_value
(
   attr_value_id        varchar(32) not null comment '店铺属性值id',
   store_id             varchar(32) not null comment '店铺id',
   attribute_id         varchar(32) not null comment '属性id  表 item_attribute',
   attribute_name       varchar(50) not null comment '属性名称',
   attribute_value      varchar(50) not null comment '属性值',
   sort_num             int(11) not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态 1 删除',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (attr_value_id)
);

alter table shop_attr_value comment '商家--属性值';

/*==============================================================*/
/* Table: shop_attribute                                        */
/*==============================================================*/
create table shop_attribute
(
   shop_attribute_id    varchar(32) not null comment '商家属性id',
   store_id             varchar(32) not null comment '店铺id',
   attribute_id         varchar(32) not null comment '属性id',
   sort_num             int(11) not null default 0 comment '排序',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (shop_attribute_id)
);

alter table shop_attribute comment '商家--属性';

/*==============================================================*/
/* Table: shop_brand                                            */
/*==============================================================*/
create table shop_brand
(
   shop_brand_id        varchar(32) not null comment '店铺品牌id',
   brand_id             varchar(32) not null comment '品牌id',
   store_id             varchar(32) not null comment '店铺id',
   seller_id            varchar(32) not null comment '卖家id',
   category_id          varchar(32) not null comment '三级类目id',
   status               int(11) not null default 1 comment '状态：1为申请，2为通过，3为驳回，0删除',
   operator_id          varchar(100) comment '审核人id',
   comment              varchar(200) comment '备注',
   is_del               int not null default 0 comment '删除状态 1 删除',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (shop_brand_id)
);

alter table shop_brand comment '店铺--品牌';

/*==============================================================*/
/* Table: shop_category                                         */
/*==============================================================*/
create table shop_category
(
   shop_category_id     varchar(32) not null comment ' 店铺类目id',
   category_id          varchar(32) not null comment '类目id',
   store_id             varchar(32) not null comment '店铺id',
   seller_id            varchar(32) not null comment '卖家id',
   sort_num             int(11) not null default 0 comment '排序',
   status               int(11) not null default 1 comment '状态：1为申请，2为通过，3为驳回，0 卖家删除',
   operator_id          varchar(100) comment '审核人',
   comment              varchar(200) comment '备注',
   is_del               int not null default 0 comment '删除状态 1 删除',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (shop_category_id)
);

alter table shop_category comment '店铺--类目关系';

/*==============================================================*/
/* Table: shop_category_attribute                               */
/*==============================================================*/
create table shop_category_attribute
(
   shop_category_attribute_id varchar(32) not null comment '商家类目属性id',
   store_id             varchar(32) not null comment '店铺id',
   seller_id            varchar(32) not null comment '卖家id',
   category_id          varchar(32) not null comment '类目id',
   attribute_id         varchar(32) not null comment '属性id',
   option_type          int(11) not null default 1 comment '是否必填。1：必填；2：非必填',
   attr_type            int(11) not null default 0 comment '属性类型:1:销售属性;0:非销售属性',
   select_type          int(11) not null comment '是否多选。1：单选；2：多选',
   sort_num             int(11) not null default 0 comment '属性排序',
   status               int(11) not null default 1 comment '状态 1，0',
   json_value           varchar(2000) default '' comment ' json属性值',
   is_del               int not null default 0 comment '删除状态 1 删除',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (shop_category_attribute_id)
);

alter table shop_category_attribute comment '商家--类目--属性';

/*==============================================================*/
/* Table: shop_category_attribute_value                         */
/*==============================================================*/
create table shop_category_attribute_value
(
   shop_category_value_id varchar(32) not null comment 'id',
   attribute_id         varchar(32) not null comment '属性ID',
   category_id          varchar(32) not null comment '类目ID',
   store_id             varchar(32) not null default '0' comment '店铺id',
   seller_id            varchar(32) not null default '0' comment '卖家id',
   value_id             varchar(32) not null comment '属性值ID',
   sort_number          int(11) not null default 0 comment '排序号',
   is_del               int not null default 0 comment '删除状态 1 删除',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (shop_category_value_id)
);

alter table shop_category_attribute_value comment '商家--类目--属性值';

/*==============================================================*/
/* Table: shop_seller                                           */
/*==============================================================*/
create table shop_seller
(
   seller_id            varchar(32) not null comment '主键',
   store_id             varchar(32) not null comment '店铺id',
   role_id              varchar(32) not null default '0' comment '店铺角色id',
   login_name           varchar(50) not null comment '登录名',
   seller_name          varchar(20) not null comment '卖家名称',
   password             varchar(50) not null comment '密码',
   nick_name            varchar(20) not null comment '昵称',
   is_seller_admin      int default 1 comment '是否主账号 1是 拥有最高权限',
   sex                  int not null default 0 comment '性别 0 未设置 1男 2女',
   birthday             date comment '生日',
   phone                varchar(20) comment '手机',
   mail                 varchar(50) comment '邮箱',
   email_valid          int(1) default 0 comment '邮箱验证 0未验证 1已验证',
   head_img             varchar(100) comment '头像',
   weichat              varchar(100) comment '绑定微信',
   id_card              varchar(20) comment '身份证',
   id_card_valid        int default 0 comment '身份认证 0未验证 1已验证',
   is_del               int not null default 0 comment '删除状态 1 删除',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (seller_id)
);

alter table shop_seller comment '卖家';

/*==============================================================*/
/* Table: shop_store                                            */
/*==============================================================*/
create table shop_store
(
   store_id             varchar(32) not null comment '店铺ID',
   seller_id            varchar(32) not null comment '卖家id',
   store_name           varchar(200) not null comment '店铺名称',
   store_code           varchar(50) not null comment '店铺编码',
   tel                  varchar(50) comment '电话',
   link_man_name        varchar(20) comment '联系人',
   link_man_tel         varchar(20) comment '联系人电话',
   remark               varchar(256) comment '备注',
   shop_status          int(11) not null default 1 comment '店铺建新状态;1是申请，2是通过，3是驳回， 4是平台关闭，5是开通',
   run_status           int(11) default 2 comment '店铺运行状态（只能允许卖家操作，默认为不开启），1表示卖家开启铺店，2表示卖家关闭店铺',
   province_code        varchar(20) comment '省份代码',
   province_name        varchar(50) comment '省份名字',
   city_code            varchar(20) comment '市的代码',
   city_name            varchar(50) comment '市的名字',
   street_name          varchar(100) comment '街道名字',
   address_detail       varchar(100) comment '详细地址',
   longitude            double comment '经度',
   Latitude             double comment '维度',
   modify_status        int(11) default 1 comment '店铺信息修改状态，1为待审核，2为驳回，3为修改通过',
   is_del               int not null default 0 comment '删除状态 1 删除',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (store_id)
);

alter table shop_store comment '商家信息表';

/*==============================================================*/
/* Table: stock_inventory_manager                               */
/*==============================================================*/
create table stock_inventory_manager
(
   inventory_id         varchar(32) not null comment '存货id',
   area_id              varchar(32) not null default '0' comment '区域id',
   sku_no               varchar(100) not null comment '商品编号',
   inventory_num        int not null comment '库存数量',
   lock_num             int not null comment '锁定数量',
   left_num             int not null comment '可用数量',
   cost_price_avg       decimal(14,2) comment '平均成本价格',
   cost_price_min       decimal(14,2) comment '最小成本价格',
   cost_price_max       decimal(14,2) comment '最大成本价格',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (inventory_id)
);

alter table stock_inventory_manager comment '库存存货 所有仓库统一区域的和';

/*==============================================================*/
/* Table: stock_item_in                                         */
/*==============================================================*/
create table stock_item_in
(
   tb_id                varchar(32) not null comment '表id',
   primary key (tb_id)
);

alter table stock_item_in comment '入库记录';

/*==============================================================*/
/* Table: stock_item_out                                        */
/*==============================================================*/
create table stock_item_out
(
   tb_id                varchar(32) not null comment '表id',
   primary key (tb_id)
);

alter table stock_item_out comment '出库记录';

/*==============================================================*/
/* Table: stock_trade_log                                       */
/*==============================================================*/
create table stock_trade_log
(
   log_id               varchar(32) not null comment '日志id',
   order_sn             varchar(50) not null comment '订单sn',
   area_id              varchar(32) not null default '0' comment '区域id',
   warehouse_id         varchar(32) not null comment '仓库id',
   sku_no               varchar(50) not null comment '商品编号',
   spu                  varchar(50) not null comment '货号',
   inventory_num        int not null comment '库存数量',
   lock_num             int not null comment '锁定数量',
   left_num             int not null comment '可用数量',
   change_num           int not null comment '改变数量',
   change_type          int not null default 1 comment '改变类型 1 扣减 2 恢复',
   remark               varchar(300) comment '备注',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (log_id)
);

alter table stock_trade_log comment '库存日志';

/*==============================================================*/
/* Table: stock_warehouse                                       */
/*==============================================================*/
create table stock_warehouse
(
   warehouse_id         varchar(32) not null comment '仓库id',
   area_id              varchar(32) not null default '0' comment '区域id',
   warehouse_code       varchar(50) not null comment '仓库编码',
   warehouse_name       varchar(200) not null comment '仓库名称',
   address              varchar(200) comment '仓库地址',
   longitude            double comment '经度',
   latitude             double comment '维度',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (warehouse_id)
);

alter table stock_warehouse comment '仓库';

/*==============================================================*/
/* Table: stock_warehouse_sku                                   */
/*==============================================================*/
create table stock_warehouse_sku
(
   tb_id                varchar(32) not null comment '表id',
   area_id              varchar(32) not null default '0' comment '区域id',
   warehouse_id         varchar(32) not null comment '仓库id',
   sku_no               varchar(100) not null comment '商品编号',
   inventory_num        int not null comment '库存数量',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table stock_warehouse_sku comment 'sku库存 数量 同一仓库供货明细的和';

/*==============================================================*/
/* Table: stock_warehouse_supply                                */
/*==============================================================*/
create table stock_warehouse_supply
(
   tb_id                varchar(32) not null comment '表id',
   warehouse_id         varchar(32) not null comment '仓库id',
   area_id              varchar(32) not null default '0' comment '区域id',
   spu                  varchar(50) not null comment '货号',
   sku_no               varchar(50) not null comment '商品编号',
   inventory_num        int not null comment '库存数量',
   cost_price           decimal(14,2) not null comment '成本价格',
   supplier_code        varchar(50) not null comment '供货商编码',
   supplier_name        varchar(200) not null comment '供货商',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table stock_warehouse_supply comment '商品供货库存明细';

/*==============================================================*/
/* Table: stock_warehouse_supply_detail                         */
/*==============================================================*/
create table stock_warehouse_supply_detail
(
   tb_id                varchar(32) not null comment '表id',
   warehouse_id         varchar(32) not null comment '仓库id',
   area_id              varchar(32) not null default '0' comment '区域id',
   spu                  varchar(50) not null comment '货号',
   sku_no               varchar(50) not null comment '商品编号',
   supply_num           int not null comment '供货数量',
   cost_price           decimal(14,2) not null comment '成本价格',
   supplier_code        varchar(50) not null comment '供货商编码',
   supplier_name        varchar(200) not null comment '供货商',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   Column_18            char(10),
   primary key (tb_id)
);

alter table stock_warehouse_supply_detail comment '商品供货库存明细';

/*==============================================================*/
/* Table: stock_warehouse_supply_log                            */
/*==============================================================*/
create table stock_warehouse_supply_log
(
   tb_id                varchar(32) not null comment '表id',
   warehouse_id         varchar(32) not null comment '仓库id',
   area_id              varchar(32) not null default '0' comment '区域id',
   spu                  varchar(50) not null comment '货号',
   sku_no               varchar(50) not null comment '商品编号',
   inventory_num        int not null comment '库存数量',
   cost_price           decimal(14,2) not null comment '成本价格',
   supplier_code        varchar(50) not null comment '供货商编码',
   supplier_name        varchar(200) not null comment '供货商',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (tb_id)
);

alter table stock_warehouse_supply_log comment '商品供货库存明细';

/*==============================================================*/
/* Table: store_menu                                            */
/*==============================================================*/
create table store_menu
(
   menu_id              varchar(32) not null,
   store_id             varchar(32) not null,
   menu_name            varchar(100) not null,
   url                  varchar(200) not null,
   parent_id            varchar(32) not null,
   sort                 int not null default 0 comment '排序',
   memu_level           int not null default 0 comment '等级',
   id_path              varchar(200) not null comment '路径',
   description          varchar(200) comment '描述',
   permission           varchar(100) comment '权限',
   is_show              int not null default 0 comment '是否显示：0,否;1,是',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (menu_id)
);

alter table store_menu comment '店铺菜单';

/*==============================================================*/
/* Table: store_role                                            */
/*==============================================================*/
create table store_role
(
   role_id              varchar(32) not null,
   rule_name            varchar(100) not null comment '角色名称',
   description          varchar(200) comment '角色描述',
   store_id             varchar(32) not null comment '店铺id',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (role_id)
);

alter table store_role comment '店铺权限角色';

/*==============================================================*/
/* Table: store_role_menu                                       */
/*==============================================================*/
create table store_role_menu
(
   role_menu_id         varchar(32) not null,
   store_id             varchar(32) not null,
   role_id              varchar(32) not null,
   menu_id              varchar(32) not null,
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (role_menu_id)
);

alter table store_role_menu comment '店铺--角色--权限菜单';

/*==============================================================*/
/* Table: trade_cart                                            */
/*==============================================================*/
create table trade_cart
(
   cart_id              varchar(32) not null comment '表id',
   member_id            varchar(32) not null comment '会员',
   goods_id             varchar(32) not null comment '商品id',
   sku_id               varchar(32) not null comment 'sku_id',
   goods_name           varchar(100) not null comment '商品名称',
   store_id             varchar(32) not null comment '店铺id',
   store_name           varchar(100) not null comment '店铺名称',
   spec_info            varchar(200) not null default '' comment 'spec_info',
   goods_num            int not null comment '商品数量',
   goods_price          decimal(14,2) not null comment '商品价格',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (cart_id)
);

alter table trade_cart comment '购物车';

/*==============================================================*/
/* Table: trade_order                                           */
/*==============================================================*/
create table trade_order
(
   order_id             varchar(32) not null comment '订单id',
   order_sn             varchar(50) not null comment '订单编号',
   parent_id            varchar(32) not null default '0' comment '父订单id  0未拆单，1为父订单，其它为父订单ID',
   parent_order_sn      varchar(50) not null default '0' comment '父订单编号',
   origin_order_sn      varchar(50) not null default '0' comment '订单来源编码',
   buyer_id             varchar(32) not null comment '买家id',
   buyer_name           varchar(50) not null comment '买家姓名',
   buyer_mobile         varchar(20) not null comment '买家电话',
   seller_id            varchar(32) not null comment '卖家id',
   store_id             varchar(32) not null comment '店铺id',
   order_state          int not null default 1 comment '订单状态 1 待付款 2. 待发货 3 待收货  6 已完成 0 已取消 ',
   goods_amount         decimal(14,2) not null comment '商品总金额',
   freight_charge       decimal(14,2) comment '运费',
   promo_id             varchar(32) comment '优惠券id',
   promo_discount       decimal(14,2) comment '优惠金额',
   integral_num         int comment '积分数量',
   integral_discount    decimal(14,2) comment '积分优惠金额',
   adjust_amount        decimal(14,2) comment '调价金额',
   change_payment_price_time datetime comment '调价时间',
   change_payment_price_operator varchar(20) comment '调价操作员',
   total_discount       decimal(14,2) comment '总优惠价',
   total_price          decimal(14,2) comment '订单总价',
   trade_out_sn         varchar(50) comment '交易对外编号',
   trade_sn             varchar(50) comment '交易流水号',
   payment_price        decimal(14,2) not null comment '实际支付金额',
   pay_recive           int not null default 0 comment '货到付款 0 否 1 是',
   payment_state        int default 0 comment '支付状态 0 未付款 1 已付款',
   payment_type         int comment '支付类型 1 支付宝 2 微信 3 积分 4 银联',
   payment_time         datetime comment '支付时间',
   invoice_need         int comment '是否有发票 0 否 1是',
   invoice_title        varchar(200) comment '发票标题',
   invoice_corporation_tax varchar(100) comment '发票税号',
   address_reciver_name varchar(30) comment '收货人',
   address_reciver_mobile varchar(30) comment '收货电话',
   address_zip_code     varchar(10) comment '邮编',
   address_province     varchar(30) comment '省份',
   address_city         varchar(30) comment '城市',
   address_county       varchar(30) comment '县市区',
   address_detail_address varchar(200) comment '详细收货地址',
   logistics_company    varchar(50) comment '物流公司',
   logistics_company_code varchar(30) comment '物流公司编号',
   logistics_no         varchar(100) comment '物流单号',
   logistics_remark     varchar(200) comment '物流备注',
   order_remark         varchar(200) comment '订单备注',
   seller_remark        varchar(200) comment '卖家备注',
   cancle_time          datetime comment '取消时间',
   deliver_time         datetime comment '发货时间',
   confirm_receipt_time datetime comment '确认收货时间',
   order_finish_time    datetime comment '订单完成时间',
   buyer_evaluate       int comment '买家评论 0 无 1 是',
   seller_evaluate      int comment '卖家评论  0 无 1 是',
   refund               int comment '退款 0 无 1 是',
   is_del               int not null default 0 comment '删除状态',
   refund_time          datetime comment '退款时间',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (order_id)
);

alter table trade_order comment '订单';

/*==============================================================*/
/* Table: trade_order_address                                   */
/*==============================================================*/
create table trade_order_address
(
   order_address_id     varchar(32) not null comment '订单收货地址id',
   alise_name           varchar(20) comment '收货地址别名',
   member_id            varchar(32) not null comment '会员id',
   reciver_name         varchar(30) not null comment '收货人',
   mobile               varchar(30) not null comment '收货电话',
   zip_code             varchar(10) not null default '000000' comment '邮编',
   province             varchar(30) not null comment '省份',
   city                 varchar(30) not null comment '城市',
   county               varchar(30) not null comment '县市区',
   detail_address       varchar(200) not null comment '详细地址',
   use_default          int not null default 0 comment '是否默认 0 否 1 是',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (order_address_id)
);

alter table trade_order_address comment '订单收货地址 订单对应的收货地址';

/*==============================================================*/
/* Table: trade_order_items                                     */
/*==============================================================*/
create table trade_order_items
(
   order_item_id        varchar(32) not null comment '表 id',
   order_id             varchar(32) not null comment '订单id',
   area_id              varchar(32) not null default '0' comment '区域id',
   goods_id             varchar(32) not null comment '商品id',
   goods_seriral        varchar(100) not null comment '货号',
   goods_name           varchar(200) not null comment '商品名称',
   spec_id              varchar(32) not null comment '商品规格id',
   sku_no               varchar(100) not null comment '商品编号',
   spec_info            varchar(200) not null comment '商品规格属性',
   spec_name            varchar(300) not null comment 'SKU名称',
   category_id          varchar(32) not null comment '类目id',
   category_name        varchar(50) not null comment '类目名称',
   brand_id             varchar(32) not null comment '品牌id',
   brand_name           varchar(100) not null comment '品牌名称',
   num                  int not null comment '数量',
   goods_price          decimal(14,2) not null comment '商品单价',
   goods_price_total    decimal(14,2) not null comment '商品总价',
   promotion_id         varchar(32) not null comment '促销id',
   promotion_type       int comment '促销类型(1直降，2满减)',
   promotion_discount   decimal(14,2) comment '促销优惠金额',
   pay_price            decimal(14,2) not null comment '实际支付单价',
   pay_price_total      decimal(14,2) not null comment '实际支付总价',
   shop_freight_template_id varchar(32) not null default '0' comment '运费模版ID',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (order_item_id)
);

alter table trade_order_items comment '订单商品';

/*==============================================================*/
/* Table: trade_order_log                                       */
/*==============================================================*/
create table trade_order_log
(
   order_log_id         varchar(32) not null comment '日志id',
   order_id             varchar(32) not null comment '订单id',
   order_sn             varchar(50) not null comment '订单编号',
   order_state          varchar(100) not null comment '订单状态',
   remark               varchar(300) not null comment '描述',
   operator             varchar(50) not null comment '操作人名称',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   primary key (order_log_id)
);

alter table trade_order_log comment '订单日志';

/*==============================================================*/
/* Table: trade_user_receive_address                            */
/*==============================================================*/
create table trade_user_receive_address
(
   address_id           varchar(32) not null comment '收货地址id',
   alise_name           varchar(20) comment '收货地址别名',
   member_id            varchar(32) not null comment '会员id',
   reciver_name         varchar(30) not null comment '收货人',
   mobile               varchar(30) not null comment '收货电话',
   zip_code             varchar(10) not null default '000000' comment '邮编',
   province             varchar(30) not null comment '省份',
   city                 varchar(30) not null comment '城市',
   county               varchar(30) not null comment '县市区',
   detail_address       varchar(200) not null comment '详细地址',
   use_default          int not null default 0 comment '是否默认 0 否 1 是',
   is_del               int not null default 0 comment '删除状态',
   creator              varchar(32) not null comment '创建人',
   create_time          datetime not null comment '创建时间',
   modifier             varchar(32) comment '修改人',
   modify_time          datetime comment '修改时间',
   version              int not null default 0 comment '版本号',
   primary key (address_id)
);

alter table trade_user_receive_address comment '用户收货地址';

