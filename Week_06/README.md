### 第11课作业实践
#### <font color=red>1.(选做):尝试使用Lambda/Stream/Guava优化之前作业的代码。</font>
#### <font color=red>2.(选做):尝试使用Lambda/Stream/Guava优化工作中编码的代码。</font>
#### <font color=red>3.(选做):根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。</font>
#### <font color=red>4.(选做):根据课上提供的材料，深入了解Google和Alibaba编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。</font>

### 第12节课作业实践
#### <font color=red>1.(选做):基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化。</font>
#### <font color=red>2.(必做):基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交DDL的SQL文件到Github（后面2周的作业依然要是用到这个表结构）。</font>
- 会员表
```sql
CREATE TABLE `member_info` (
  `id` bigint NOT NULL,
  `user_code` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '用户编码-全局唯一标识',
  `user_nick_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '联系方式',
  `acitvity` tinyint NOT NULL DEFAULT '1' COMMENT '是否激活',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  `add_time` bigint NOT NULL DEFAULT '0' COMMENT '会员添加时间',
  `last_login_time` bigint DEFAULT '0' COMMENT '最后登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
```
- 用户表
```sql
CREATE TABLE `user_info` (
  `id` bigint NOT NULL,
  `member_info_id` bigint NOT NULL COMMENT '会员ID',
  `validate_type` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '验证类型：账号密码、邮箱、微信、支付宝',
  `validate_info` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '验证信息',
  `validate_pwd` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '验证密码',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  `activity` tinyint DEFAULT '1' COMMENT '是否激活',
  `bind_time` bigint DEFAULT '0' COMMENT '绑定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
```
- 订单表
```sql
CREATE TABLE `order_info` (
  `id` int NOT NULL,
  `order_no` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '订单唯一编码',
  `child_order` tinyint NOT NULL COMMENT '是否是子订单',
  `paraent_order_no` varchar(128) COLLATE utf8_bin DEFAULT '0000' COMMENT '父订单编码',
  `member_info_id` bigint NOT NULL COMMENT '会员信息',
  `money` decimal(20,5) NOT NULL COMMENT '订单有效金额',
  `state` varchar(8) COLLATE utf8_bin NOT NULL COMMENT '状态：110:待付款  115: 系统校验 120:卖家待发货 130：待签收 140：签收完成 \\n200：正常完结  \\n400：异常完结',
  `addtime` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
```
- 订单明细表
```sql
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL,
  `order_info_id` bigint NOT NULL COMMENT '订单ID',
  `product_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '商品Id',
  `product_name` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(20,5) NOT NULL COMMENT '商品价格',
  `product_count` int NOT NULL COMMENT '商品数量',
  `product_discount_price` decimal(20,5) DEFAULT NULL COMMENT '商品优惠金额',
  `state` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
```
#### <font color=red>3.(选做):尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的SQL测试简单的增删改查。</font>
#### <font color=red>4.(选做):基于上一题，尝试对各个数据库测试100万订单数据的增删改查性能。</font>
#### <font color=red>5.(选做):尝试对MySQL不同引擎下测试100万订单数据的增删改查性能。</font>
#### <font color=red>6.(选做):模拟1000万订单数据，测试不同方式下导入导出（数据备份还原）MySQL的速度，包括jdbc程序处理和命令行处理。思考和实践，如何提升处理效率。</font>
#### <font color=red>7.(选做):对MySQL配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查100万次，对比性能，生成报告。</font>