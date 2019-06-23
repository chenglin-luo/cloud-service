USE cloud_service;

-- https://github.com/wuyouzhuguli/FEBS（参考项目）

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id`               INT(11)    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id`          BIGINT(20) NOT NULL UNIQUE COMMENT '用户唯一标识ID',
  `user_name`        VARCHAR(50)        DEFAULT NULL COMMENT '用户名',
  `password`         VARCHAR(128)       DEFAULT NULL COMMENT '密码',
  `dept_id`          BIGINT(20)         DEFAULT NULL COMMENT '部门ID',
  `email`            VARCHAR(128)       DEFAULT NULL COMMENT '邮箱',
  `mobile`           VARCHAR(20)        DEFAULT NULL COMMENT '联系电话',
  `sex`              INT(11)            DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `is_tab`           INT(11)            DEFAULT NULL COMMENT '是否开启tab，0关闭 1开启',
  `status`           INT(11)            DEFAULT NULL COMMENT '状态 0锁定 1有效',
  `theme`            VARCHAR(20)        DEFAULT NULL COMMENT '主题',
  `avatar`           VARCHAR(128)       DEFAULT NULL COMMENT '头像',
  `description`      VARCHAR(128)       DEFAULT NULL COMMENT '描述',
  `last_login_time`  DATETIME           DEFAULT NULL COMMENT '最近访问时间',
  `create_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id`               INT(11)    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id`          BIGINT(20) NOT NULL UNIQUE COMMENT '角色ID',
  `role_name`        VARCHAR(50)        DEFAULT NULL COMMENT '角色名称',
  `remark`           VARCHAR(128)       DEFAULT NULL COMMENT '角色描述',
  `create_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  
-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id`               INT(11)    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `menu_id`          BIGINT(20) NOT NULL UNIQUE COMMENT '菜单/按钮ID',
  `parent_id`        BIGINT(20)         DEFAULT NULL COMMENT '上级菜单ID',
  `menu_name`        VARCHAR(128)       DEFAULT NULL COMMENT '角色描述',
  `menu_url`         VARCHAR(50)        DEFAULT NULL COMMENT '菜单URL',
  `permission`		 VARCHAR(255)       DEFAULT NULL COMMENT '权限标识',
  `icon`             VARCHAR(50)        DEFAULT NULL COMMENT '图标',
  `menu_type`        INT(11)            DEFAULT NULL COMMENT '类型 0菜单 1按钮',
  `menu_order`       INT(11)            DEFAULT NULL COMMENT '排序',
  `create_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  
  
-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id`          BIGINT(20) NOT NULL COMMENT '角色ID',
  `menu_id`          BIGINT(20) NOT NULL COMMENT '菜单/按钮ID'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  
  
-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id`          BIGINT(20) NOT NULL COMMENT '用户ID',
  `role_id`          BIGINT(20) NOT NULL COMMENT '角色ID'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  
-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id`               INT(11)    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `dept_id`          BIGINT(20) NOT NULL UNIQUE COMMENT '部门ID',
  `parent_id`        BIGINT(20)         DEFAULT NULL COMMENT '上级部门ID',
  `dept_name`        VARCHAR(128)       DEFAULT NULL COMMENT '部门名称',
  `dept_order`       INT(11)            DEFAULT NULL COMMENT '排序',
  `create_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at`        TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  
SET FOREIGN_KEY_CHECKS = 1;

SET global sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
SET session sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
  
  
  
 
  
  
