-- 创建数据库名称为VoiceMonitor，设置默认编码类型为utf8
CREATE SCHEMA `VoiceMonitor` DEFAULT CHARACTER SET utf8 ;

show variables like 'character_set_%';

/**
 * TABLE NAME:usr_info      用户仅仅的账号密码信息
 * 
 * Colums:    usr_account:  用户账号
 * 		      usr_password: 用户密码
 * 		      usr_id:       用户id号（主键、自动生成）
 */
CREATE TABLE `VoiceMonitor`.`usr_info` (
  `usr_account` VARCHAR(45) NOT NULL,
  `usr_password` VARCHAR(45) NULL,
  `usr_id` INT NOT NULL auto_increment,
  PRIMARY KEY (`usr_id`));
-- DROP TABLE `VoiceMonitor`.`usr_info`;

  
/**
 * TABLE NAME:record_db      用户单次记录分贝的所有信息
 * 
 * Colums:    record_db_id:  记录的id号（主键、自动生成）
 * 		      usr_id:        用户id号
 * 		      times:         记录的次数，仅针对此id号的用户
 *            year:          记录时的年份
 *            month:         记录时的月份
 *            day:           记录时的天数
 *            db:            分贝串，以"|"分割
 *            latitude:      纬度串，以"|"分割
 *            longitude:     经度串，以"|"分割
 *            time:          实际事件串，以"|"分割
 * 			  timekeeper:    当前次数记录的事件点串，以"|"分割
 */
CREATE TABLE `VoiceMonitor`.`record_db` (
  `record_db_id` INT NOT NULL auto_increment,
  `usr_id` INT NOT NULL,
  `times` INT NULL,
  `year` INT NULL,
  `month` INT NULL,
  `day` INT NULL,
  `db` MEDIUMTEXT NOT NULL,
  `latitude` MEDIUMTEXT NULL,
  `longitude` MEDIUMTEXT NULL,
  `time` MEDIUMTEXT NULL,
  `timekeeper` MEDIUMTEXT NULL,
  PRIMARY KEY (`record_db_id`));
-- DROP TABLE `VoiceMonitor`.`record_db`;


/**
 * TABLE NAME:base_info      用户基本信息
 * 
 * Colums:    usr_id:        用户id号（主键）
 * 		      usr_account:   用户账号
 *            nickname:      用户昵称
 *            record_times:  用户记录次数
 *            average_db:    记录平均分贝数
 *            max_db:        最大分贝数
 *            min_db:        最小分贝数
 *            record_minter: 记录总时长
 *            head_protrait: 头像id号
 */
CREATE TABLE `VoiceMonitor`.`base_info` (
  `usr_id` INT NOT NULL,
  `usr_account`  VARCHAR(45) NOT NULL,
  `nickname`  VARCHAR(45) NULL,
  `record_times` INT NOT NULL,
  `average_db` DOUBLE NOT NULL,
  `max_db` INT NOT NULL,
  `min_db` INT NOT NULL,
  `record_minter` DOUBLE NOT NULL,
  `head_protrait` INT NULL,
  PRIMARY KEY (`usr_id`));
-- DROP TABLE `VoiceMonitor`.`base_info`;

  
/**
 * TABLE NAME:advertising_column      广告栏数据
 * 
 * Colums:    advertising_column_id:  广告id号（主键、自动生成）
 * 		      advertisement_id:       广告id号
 *            image_id:               广告图片id
 *            title:                  广告标题
 */
CREATE TABLE `VoiceMonitor`.`advertising_column` (
  `advertising_column_id` INT NOT NULL auto_increment,
  `advertisement_id` INT NOT NULL,
  `image_id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`advertising_column_id`));
-- DROP TABLE `VoiceMonitor`.`advertising_column`;

  
/**
 * TABLE NAME:store_info        商家信息
 * 
 * Colums:    store_id:         商家id号（主键、自动生成）
 * 		      advertisement_id: 广告id号
 *            image_id:         图片id号
 *            title:            标题
 *            average_db:       记录平均分贝数
 *            summary:          商家概要
 *            latitude:         经度
 *            longitude:        纬度
 *            type:             商家类型
 */
CREATE TABLE `VoiceMonitor`.`store_info` (
  `store_id` INT NOT NULL auto_increment,
  `advertisement_id` INT NOT NULL,
  `image_id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `average_db` INT NOT NULL,
  `summary` VARCHAR(256) NULL,
  `latitude` DOUBLE,
  `longitude` DOUBLE,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`store_id`));
-- DROP TABLE `VoiceMonitor`.`store_info`; 


 /**
 * TABLE NAME:store_record_db  商家单次记录分贝的所有信息
 * 
 * Colums:    store_record_id: 记录的id号（主键、自动生成）
 *            store_id：        商家id号
 * 		      usr_id:          用户id号
 * 		      times:           记录的次数，仅针对此id号的商家
 *            year:            记录时的年份
 *            month:           记录时的月份
 *            day:             记录时的天数
 *            db:              分贝串，以"|"分割
 *            latitude:        纬度串，以"|"分割
 *            longitude:       经度串，以"|"分割
 *            time:            实际事件串，以"|"分割
 * 			  timekeeper:      当前次数记录的事件点串，以"|"分割
 *            reocrd_status：   记录状态，区分审核未审核
 */
CREATE TABLE `VoiceMonitor`.`store_record_db` (
  `store_record_id` INT NOT NULL auto_increment,
  `store_id` INT NOT NULL,
  `usr_id` INT NOT NULL,
  `times` INT NULL,
  `year` INT NULL,
  `month` INT NULL,
  `day` INT NULL,
  `db` MEDIUMTEXT NULL,
  `latitude` MEDIUMTEXT NULL,
  `longitude` MEDIUMTEXT NULL,
  `time` MEDIUMTEXT NULL,
  `timekeeper` MEDIUMTEXT NULL,
  `reocrd_status` INT NOT NULL,
  PRIMARY KEY (`store_record_id`));
-- DROP TABLE `VoiceMonitor`.`store_record_db`; 

  
/**
 * TABLE NAME:image_id_to_address  图片实际地址和图片id的对应表
 * 
 * Colums:    image_id:            图片的id号（主键、自动生成）
 * 		      address:             实际存放的地址
 *            description:         图片描述
 */
CREATE TABLE `VoiceMonitor`.`image_id_to_address` (
  `image_id` INT NOT NULL auto_increment,
  `address` VARCHAR(256) NOT NULL,
  `description` VARCHAR(256) NULL,
  PRIMARY KEY (`image_id`));
-- DROP TABLE `VoiceMonitor`.`image_id_to_address`; 


