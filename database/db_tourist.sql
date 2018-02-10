/*
Navicat MySQL Data Transfer

Source Server         : cloud-db
Source Server Version : 50715
Source Host           : rm-bp1d79mlswd465ue0o.mysql.rds.aliyuncs.com:3306
Source Database       : db_tourist

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-02-10 12:07:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_active
-- ----------------------------
DROP TABLE IF EXISTS `tb_active`;
CREATE TABLE `tb_active` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL,
  `icon` varchar(1000) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `price` float DEFAULT NULL,
  `description` text,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_active
-- ----------------------------
INSERT INTO `tb_active` VALUES ('2', '儿童乐园', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/active/leyuan.png', '08:30:00', '18:00:00', '399', '供儿童游玩娱乐的场所，包含各种各样的游戏。', '2018-01-23 11:22:43');
INSERT INTO `tb_active` VALUES ('3', '亲子体验', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/active/qinzi.png', '14:00:00', '18:00:00', '230', '使家庭成员增进彼此了解，建立友谊。', '2018-01-23 11:34:13');
INSERT INTO `tb_active` VALUES ('4', '花卉婚纱摄影', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/active/hunsha.png', '08:00:00', '10:00:00', '999', '坚持以人为本的服务理念，打造地区顶尖写真摄影。', '2018-01-23 11:35:44');
INSERT INTO `tb_active` VALUES ('5', '武陟书院', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/active/shuyuan.png', '09:00:00', '17:00:00', '654', '书院聚集了大批文人，其中许多是有名的学者。', '2018-01-23 11:37:31');
INSERT INTO `tb_active` VALUES ('6', '水上茶座', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/active/chazuo.png', '18:00:00', '21:00:00', '298', '一种较为优雅和闲适的艺术享受。', '2018-01-23 11:38:34');

-- ----------------------------
-- Table structure for tb_active_assess
-- ----------------------------
DROP TABLE IF EXISTS `tb_active_assess`;
CREATE TABLE `tb_active_assess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `fk_tb_active_id` int(11) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `assess` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_23` (`fk_tb_user_id`),
  KEY `FK_Relationship_24` (`fk_tb_active_id`),
  CONSTRAINT `FK_Relationship_23` FOREIGN KEY (`fk_tb_user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK_Relationship_24` FOREIGN KEY (`fk_tb_active_id`) REFERENCES `tb_active` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_active_assess
-- ----------------------------

-- ----------------------------
-- Table structure for tb_active_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_active_img`;
CREATE TABLE `tb_active_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_active_id` int(11) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_39` (`fk_tb_active_id`),
  CONSTRAINT `FK_Relationship_39` FOREIGN KEY (`fk_tb_active_id`) REFERENCES `tb_active` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_active_img
-- ----------------------------

-- ----------------------------
-- Table structure for tb_active_remark
-- ----------------------------
DROP TABLE IF EXISTS `tb_active_remark`;
CREATE TABLE `tb_active_remark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_remark_id` int(11) DEFAULT NULL,
  `fk_tb_active_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_41` (`fk_tb_active_id`),
  KEY `FK_Relationship_42` (`fk_tb_remark_id`),
  CONSTRAINT `FK_Relationship_41` FOREIGN KEY (`fk_tb_active_id`) REFERENCES `tb_active` (`id`),
  CONSTRAINT `FK_Relationship_42` FOREIGN KEY (`fk_tb_remark_id`) REFERENCES `tb_remark` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_active_remark
-- ----------------------------
INSERT INTO `tb_active_remark` VALUES ('1', '1', '3');

-- ----------------------------
-- Table structure for tb_adsense
-- ----------------------------
DROP TABLE IF EXISTS `tb_adsense`;
CREATE TABLE `tb_adsense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `detailurl` varchar(1024) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_adsense
-- ----------------------------
INSERT INTO `tb_adsense` VALUES ('3', '小爱活动', '', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/adsense/20180122154506.jpg', '2018-01-25 11:45:15', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body><h2 class=\"rich_media_title\" style=\"margin: 0px 0px 14px; padding: 0px 0px 10px; font-weight: 400; font-size: 24px; line-height: 1.4; border-bottom: 1px solid rgb(231, 231, 235); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">爱依特幼儿园协助教学机器人湖北试点</h2><p><span class=\"rich_media_meta meta_original_tag\" style=\"margin: 0px 8px 10px 0px; padding: 1px 0.5em; display: inline-block; vertical-align: middle; font-size: 15px; border: 1px solid rgb(158, 158, 158); color: rgb(140, 140, 140); border-radius: 20% 50%; line-height: 1.1; max-width: none;\">原创</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: middle; font-', '<h2 class=\"rich_media_title\" style=\"margin: 0px 0px 14px; padding: 0px 0px 10px; font-weight: 400; font-size: 24px; line-height: 1.4; border-bottom: 1px solid rgb(231, 231, 235); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">爱依特幼儿园协助教学机器人湖北试点</h2><p><span class=\"rich_media_meta meta_original_tag\" style=\"margin: 0px 8px 10px 0px; padding: 1px 0.5em; display: inline-block; vertical-align: middle; font-size: 15px; border: 1px solid rgb(158, 158, 158); color: rgb(140, 140, 140); border-radius: 20% 50%; line-height: 1.1; max-width: none;\">原创</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: middle; font-size: 16px; color: rgb(140, 140, 140); max-width: none;\">2017-11-24</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0p');
INSERT INTO `tb_adsense` VALUES ('4', '小爱机器人', '', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/adsense/20180122154512.jpg', '2018-01-25 11:46:46', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body><h2 class=\"rich_media_title\" style=\"margin: 0px 0px 14px; padding: 0px 0px 10px; font-weight: 400; font-size: 24px; line-height: 1.4; border-bottom: 1px solid rgb(231, 231, 235); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">安徽中青旅第二课堂研学活动走进爱依特，与前沿科技零距离接触</h2><p><span class=\"rich_media_meta meta_original_tag\" style=\"margin: 0px 8px 10px 0px; padding: 1px 0.5em; display: inline-block; vertical-align: middle; font-size: 15px; border: 1px solid rgb(158, 158, 158); color: rgb(140, 140, 140); border-radius: 20% 50%; line-height: 1.1; max-width: none;\">原创</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: m', '<h2 class=\"rich_media_title\" style=\"margin: 0px 0px 14px; padding: 0px 0px 10px; font-weight: 400; font-size: 24px; line-height: 1.4; border-bottom: 1px solid rgb(231, 231, 235); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">安徽中青旅第二课堂研学活动走进爱依特，与前沿科技零距离接触</h2><p><span class=\"rich_media_meta meta_original_tag\" style=\"margin: 0px 8px 10px 0px; padding: 1px 0.5em; display: inline-block; vertical-align: middle; font-size: 15px; border: 1px solid rgb(158, 158, 158); color: rgb(140, 140, 140); border-radius: 20% 50%; line-height: 1.1; max-width: none;\">原创</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: middle; font-size: 16px; color: rgb(140, 140, 140); max-width: none;\">2017-11-20</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px;');
INSERT INTO `tb_adsense` VALUES ('5', '小爱', '', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/adsense/shitou-001.jpg', '2018-01-25 14:10:32', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body><h2 class=\"rich_media_title\" style=\"margin: 0px 0px 14px; padding: 0px 0px 10px; font-weight: 400; font-size: 24px; line-height: 1.4; border-bottom: 1px solid rgb(231, 231, 235); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">爱依特签约清华启迪研学游</h2><p><span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: middle; font-size: 16px; color: rgb(140, 140, 140); max-width: none;\">2017-12-04</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: middle; font-size: 16px; color: rgb(140, 140, 140); max-width: none;\">Avatar-T</span>&nbsp;</', '<h2 class=\"rich_media_title\" style=\"margin: 0px 0px 14px; padding: 0px 0px 10px; font-weight: 400; font-size: 24px; line-height: 1.4; border-bottom: 1px solid rgb(231, 231, 235); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; white-space: normal; background-color: rgb(255, 255, 255);\">爱依特签约清华启迪研学游</h2><p><span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: middle; font-size: 16px; color: rgb(140, 140, 140); max-width: none;\">2017-12-04</span>&nbsp;<span class=\"rich_media_meta rich_media_meta_text\" style=\"margin: 0px 8px 10px 0px; padding: 0px; display: inline-block; vertical-align: middle; font-size: 16px; color: rgb(140, 140, 140); max-width: none;\">Avatar-T</span>&nbsp;</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; max-width: 100%; clear: both; min-height: 1em; box-sizing: border-box !important; word-wrap: break-word !importa');

-- ----------------------------
-- Table structure for tb_attractions
-- ----------------------------
DROP TABLE IF EXISTS `tb_attractions`;
CREATE TABLE `tb_attractions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `robot_id` int(11) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_attractions
-- ----------------------------
INSERT INTO `tb_attractions` VALUES ('1', '武陟书院', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/attractions/2A0c060000001pdto7910.jpg', '33', '武陟山庄测试机器人');
INSERT INTO `tb_attractions` VALUES ('2', '武陟书院', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/attractions/saf121312311432.jpg', '24', '武陟山庄测试机器人');
INSERT INTO `tb_attractions` VALUES ('3', '武陟书院', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/attractions/qwef12312314.jpg', '28', '武陟山庄测试机器人');

-- ----------------------------
-- Table structure for tb_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_coupons_type_id` int(11) DEFAULT NULL,
  `name` varchar(500) DEFAULT NULL,
  `money` float DEFAULT NULL,
  `spend_money` float DEFAULT NULL,
  `explains` text,
  `valid_start_time` datetime DEFAULT NULL,
  `valid_end_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_27` (`fk_tb_coupons_type_id`),
  CONSTRAINT `FK_Relationship_27` FOREIGN KEY (`fk_tb_coupons_type_id`) REFERENCES `tb_coupons_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon
-- ----------------------------
INSERT INTO `tb_coupon` VALUES ('2', '1', '优惠券', '30', '150', null, '2018-01-22 11:57:49', '2018-02-15 11:57:49', '2018-01-23 11:59:44');
INSERT INTO `tb_coupon` VALUES ('3', '1', '优惠券', '10', '50', null, '2018-01-26 17:15:46', '2018-02-15 11:57:49', '2018-01-25 17:16:03');
INSERT INTO `tb_coupon` VALUES ('4', '1', '优惠券', '10', '30', null, '2018-01-25 17:16:19', '2018-02-15 11:57:49', '2018-01-25 17:16:26');
INSERT INTO `tb_coupon` VALUES ('6', '1', '餐饮优惠券', '10', '20', null, '2018-02-01 17:03:33', '2018-02-15 11:57:49', '2018-02-02 17:04:35');
INSERT INTO `tb_coupon` VALUES ('7', '1', '餐饮优惠券', '30', '100', null, '2018-02-01 17:03:33', '2018-02-15 11:57:49', '2018-02-02 17:08:16');
INSERT INTO `tb_coupon` VALUES ('8', '1', '餐饮优惠券', '10', '40', null, '2018-02-01 17:03:33', '2018-02-15 11:57:49', '2018-02-02 17:08:21');
INSERT INTO `tb_coupon` VALUES ('9', '2', '活动优惠券', '10', '40', null, '2018-02-01 17:03:33', '2018-02-15 11:57:49', '2018-02-02 17:08:36');
INSERT INTO `tb_coupon` VALUES ('10', '2', '活动优惠券', '5', '10', null, '2018-02-01 17:03:33', '2018-02-15 11:57:49', '2018-02-02 17:08:43');
INSERT INTO `tb_coupon` VALUES ('11', '2', '活动优惠券', '100', '400', null, '2018-02-01 17:03:33', '2018-02-15 11:57:49', '2018-02-02 17:08:57');
INSERT INTO `tb_coupon` VALUES ('12', '2', '活动优惠券', '20', '60', null, '2018-02-08 17:03:33', '2018-02-15 17:03:33', '2018-02-02 17:09:57');
INSERT INTO `tb_coupon` VALUES ('13', '1', '餐饮优惠券', '10', '30', null, '2018-02-08 17:03:33', '2018-02-15 17:03:33', '2018-02-02 17:10:12');
INSERT INTO `tb_coupon` VALUES ('14', '1', '餐饮优惠券', '20', '80', null, '2018-02-08 17:03:33', '2018-02-15 17:03:33', '2018-02-02 17:10:20');

-- ----------------------------
-- Table structure for tb_coupons_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupons_status`;
CREATE TABLE `tb_coupons_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_number` varchar(100) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupons_status
-- ----------------------------
INSERT INTO `tb_coupons_status` VALUES ('1', '001', '未使用', '2018-01-20 16:40:58');
INSERT INTO `tb_coupons_status` VALUES ('2', '002', '已使用', '2018-01-22 10:15:44');

-- ----------------------------
-- Table structure for tb_coupons_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupons_type`;
CREATE TABLE `tb_coupons_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupons_type
-- ----------------------------
INSERT INTO `tb_coupons_type` VALUES ('1', 'foodcoupons', '餐饮券', '2018-01-23 10:52:08');
INSERT INTO `tb_coupons_type` VALUES ('2', 'activecoupons', '活动劵', '2018-01-30 10:40:10');
INSERT INTO `tb_coupons_type` VALUES ('3', 'goodscoupons', '商品劵', '2018-01-30 10:43:00');

-- ----------------------------
-- Table structure for tb_coupons_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupons_users`;
CREATE TABLE `tb_coupons_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_order_active_id` int(11) DEFAULT NULL,
  `fk_tb_coupons_status_id` int(11) DEFAULT NULL,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `fk_tb_coupon_id` int(11) DEFAULT NULL,
  `fk_tb_order_food_id` int(11) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_26` (`fk_tb_user_id`),
  KEY `FK_Relationship_28` (`fk_tb_coupon_id`),
  KEY `FK_Relationship_29` (`fk_tb_order_food_id`),
  KEY `FK_Relationship_32` (`fk_tb_order_active_id`),
  KEY `FK_Relationship_43` (`fk_tb_coupons_status_id`),
  CONSTRAINT `FK_Relationship_26` FOREIGN KEY (`fk_tb_user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK_Relationship_28` FOREIGN KEY (`fk_tb_coupon_id`) REFERENCES `tb_coupon` (`id`),
  CONSTRAINT `FK_Relationship_29` FOREIGN KEY (`fk_tb_order_food_id`) REFERENCES `tb_order_food` (`id`),
  CONSTRAINT `FK_Relationship_32` FOREIGN KEY (`fk_tb_order_active_id`) REFERENCES `tb_order_active` (`id`),
  CONSTRAINT `FK_Relationship_43` FOREIGN KEY (`fk_tb_coupons_status_id`) REFERENCES `tb_coupons_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupons_users
-- ----------------------------
INSERT INTO `tb_coupons_users` VALUES ('2', '2', '2', '1', '2', null, '0', '2018-01-23 15:06:19');
INSERT INTO `tb_coupons_users` VALUES ('3', null, '1', '1', '2', null, '0', '2018-01-23 15:06:39');
INSERT INTO `tb_coupons_users` VALUES ('4', null, '1', '3', '4', null, '0', '2018-01-30 09:03:34');
INSERT INTO `tb_coupons_users` VALUES ('5', null, '2', '1', '4', '2', '1', '2018-01-30 11:11:47');
INSERT INTO `tb_coupons_users` VALUES ('6', null, '1', '1', '2', null, '1', '2018-02-08 14:12:37');
INSERT INTO `tb_coupons_users` VALUES ('7', null, '1', '1', '9', null, '1', '2018-02-08 14:12:37');
INSERT INTO `tb_coupons_users` VALUES ('8', null, '1', '1', '6', null, '1', '2018-02-08 14:12:37');
INSERT INTO `tb_coupons_users` VALUES ('9', null, '1', '1', '3', null, '1', '2018-02-09 09:10:47');
INSERT INTO `tb_coupons_users` VALUES ('10', null, '1', '1', '13', null, '1', '2018-02-09 09:10:48');

-- ----------------------------
-- Table structure for tb_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback`;
CREATE TABLE `tb_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `fk_tb_feedback_type_id` int(11) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_45` (`fk_tb_feedback_type_id`),
  CONSTRAINT `FK_Relationship_45` FOREIGN KEY (`fk_tb_feedback_type_id`) REFERENCES `tb_feedback_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_feedback
-- ----------------------------
INSERT INTO `tb_feedback` VALUES ('1', '1', '1', '我的建议', 'zhangwen@avatarcn.com', '12345667123', '2018-01-26 14:36:59');
INSERT INTO `tb_feedback` VALUES ('2', '1', '1', '反馈信息', null, null, '2018-01-30 11:33:06');
INSERT INTO `tb_feedback` VALUES ('3', '3', '2', '哈哈哈哈', null, null, '2018-01-30 11:35:12');
INSERT INTO `tb_feedback` VALUES ('4', '5', '1', '无法输入搜索商品', null, null, '2018-01-31 15:38:01');

-- ----------------------------
-- Table structure for tb_feedback_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback_img`;
CREATE TABLE `tb_feedback_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_feedback_id` int(11) DEFAULT NULL,
  `image` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_46` (`fk_tb_feedback_id`),
  CONSTRAINT `FK_Relationship_46` FOREIGN KEY (`fk_tb_feedback_id`) REFERENCES `tb_feedback` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_feedback_img
-- ----------------------------
INSERT INTO `tb_feedback_img` VALUES ('1', '1', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/feedback/set00012312931231.png');

-- ----------------------------
-- Table structure for tb_feedback_reply
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback_reply`;
CREATE TABLE `tb_feedback_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_feedback_id` int(11) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_47` (`fk_tb_feedback_id`),
  CONSTRAINT `FK_Relationship_47` FOREIGN KEY (`fk_tb_feedback_id`) REFERENCES `tb_feedback` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_feedback_reply
-- ----------------------------
INSERT INTO `tb_feedback_reply` VALUES ('1', '1', '感谢您的反馈', '2018-01-26 14:38:47');

-- ----------------------------
-- Table structure for tb_feedback_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_feedback_type`;
CREATE TABLE `tb_feedback_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_feedback_type
-- ----------------------------
INSERT INTO `tb_feedback_type` VALUES ('1', '发现故障');
INSERT INTO `tb_feedback_type` VALUES ('2', '意见建议');
INSERT INTO `tb_feedback_type` VALUES ('3', '消费服务');
INSERT INTO `tb_feedback_type` VALUES ('4', '投诉举报');
INSERT INTO `tb_feedback_type` VALUES ('5', '其他反馈');

-- ----------------------------
-- Table structure for tb_food
-- ----------------------------
DROP TABLE IF EXISTS `tb_food`;
CREATE TABLE `tb_food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_food_type_id` int(11) DEFAULT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `description` text,
  `price` float DEFAULT NULL,
  `rebate_id` float(11,0) DEFAULT NULL,
  `sold_number` int(11) DEFAULT NULL,
  `img` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_34` (`fk_tb_food_type_id`),
  CONSTRAINT `FK_Relationship_34` FOREIGN KEY (`fk_tb_food_type_id`) REFERENCES `tb_food_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_food
-- ----------------------------
INSERT INTO `tb_food` VALUES ('1', '1', '鱼香茄子', null, '20', '1', null, 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/food/20180125172630.png', '2018-01-25 17:31:43');
INSERT INTO `tb_food` VALUES ('2', '1', '家常小炒肉', null, '20', '1', null, 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/food/20180125173316.png', '2018-01-25 17:34:29');
INSERT INTO `tb_food` VALUES ('3', '1', '家常清炒甜豆', null, '20', '1', null, 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/food/20180125173740.png', '2018-01-25 17:38:55');
INSERT INTO `tb_food` VALUES ('4', '3', '栗子鸡肉', null, '10', '1', null, 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/food/20180125174036.png', '2018-01-25 17:41:26');
INSERT INTO `tb_food` VALUES ('5', '3', '芝麻馒头', null, '8', '1', null, 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/food/20180125174337.png', '2018-01-25 17:44:22');

-- ----------------------------
-- Table structure for tb_food_assess
-- ----------------------------
DROP TABLE IF EXISTS `tb_food_assess`;
CREATE TABLE `tb_food_assess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `fk_tb_food_order_id` int(11) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `assess` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_38` (`fk_tb_user_id`),
  KEY `FK_Relationship_35` (`fk_tb_food_order_id`),
  CONSTRAINT `tb_food_assess_ibfk_1` FOREIGN KEY (`fk_tb_food_order_id`) REFERENCES `tb_order_food` (`id`),
  CONSTRAINT `tb_food_assess_ibfk_2` FOREIGN KEY (`fk_tb_user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_food_assess
-- ----------------------------

-- ----------------------------
-- Table structure for tb_food_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_food_type`;
CREATE TABLE `tb_food_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL,
  `type_img` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_food_type
-- ----------------------------
INSERT INTO `tb_food_type` VALUES ('1', '炒菜类', null, '2018-01-25 16:34:41');
INSERT INTO `tb_food_type` VALUES ('2', '小吃类', null, '2018-01-25 17:20:08');
INSERT INTO `tb_food_type` VALUES ('3', '主食类', null, '2018-01-25 17:20:31');
INSERT INTO `tb_food_type` VALUES ('4', '汤类', null, '2018-01-25 17:20:42');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` longtext,
  `img` varchar(1024) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `read_count` int(11) DEFAULT NULL,
  `web_content` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_news
-- ----------------------------
INSERT INTO `tb_news` VALUES ('1', '新闻一', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/news/20180122154444.jpg', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body>2018年中国·盈江春节花海乡村文化旅游节暨首届全国越野摩托车场地大奖赛，是经过德宏盈江县批准以构建和谐盈江为目标，努力营造喜庆、祥和、文明节日文化氛围。为充分展现盈江县生态旅游，文化体验、休闲度假的良好城市形象和独特的乡村文化旅游资源，展现盈江县全新的绿色发展之路，以节庆活动带动地方产业的大力发展，完善新型产业结构调整，奠定盈江县在全州乡村文化旅游的、生态文化体验领先地位，让更多的友人们感到乡村文化旅游的独特魅力。</body></html>', '39', '2018年中国·盈江春节花海乡村文化旅游节暨首届全国越野摩托车场地大奖赛，是经过德宏盈江县批准以构建和谐盈江为目标，努力营造喜庆、祥和、文明节日文化氛围。为充分展现盈江县生态旅游，文化体验、休闲度假的良好城市形象和独特的乡村文化旅游资源，展现盈江县全新的绿色发展之路，以节庆活动带动地方产业的大力发展，完善新型产业结构调整，奠定盈江县在全州乡村文化旅游的、生态文化体验领先地位，让更多的友人们感到乡村文化旅游的独特魅力。', '2018-01-22 15:51:49');
INSERT INTO `tb_news` VALUES ('2', '新闻二', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/news/20180122154506.jpg', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body>乡土气息浓郁的英歌舞表演、客家乡谣、潮语创文表演唱等精彩表演接连上演，瞬间带动了开幕式现场的火热气氛，台上台下共享节会欢乐时刻。</body></html>', '7', '乡土气息浓郁的英歌舞表演、客家乡谣、潮语创文表演唱等精彩表演接连上演，瞬间带动了开幕式现场的火热气氛，台上台下共享节会欢乐时刻。', '2018-01-22 15:52:36');
INSERT INTO `tb_news` VALUES ('3', '新闻三', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/news/20180122154512.jpg', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body>不仅5A、4A级高品质景区广泛参与、优惠力度大，而且冬季热门的温泉、滑雪类景区也积极参与，出台形式多样的优惠措施。同时，冰雪节、冰瀑节、美食节、花灯会、民俗庙会等各种旅游节庆活动，将在河北各地轮番上演，赏民俗、品小吃、享优惠，广大游客可以充分感受到河北人民的热情好客，燕赵大地的多彩风情。</body></html>', '3', '不仅5A、4A级高品质景区广泛参与、优惠力度大，而且冬季热门的温泉、滑雪类景区也积极参与，出台形式多样的优惠措施。同时，冰雪节、冰瀑节、美食节、花灯会、民俗庙会等各种旅游节庆活动，将在河北各地轮番上演，赏民俗、品小吃、享优惠，广大游客可以充分感受到河北人民的热情好客，燕赵大地的多彩风情。', '2018-01-22 15:53:39');
INSERT INTO `tb_news` VALUES ('4', '新闻四', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/news/20180122154520.jpg', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body>来到畲乡之窗云鹤水岸里品味畲族风情，住在如隐·小佐居推开窗便能望见梯田稻浪，置身于养生石寨的清泉石上居体会闲情逸致……相比“千店一面”的酒店，如今，强调个性化的旅游民宿成了越来越多游客的新选择，也间接推动了景宁乡村旅游的发展。</body></html>', '6', '来到畲乡之窗云鹤水岸里品味畲族风情，住在如隐·小佐居推开窗便能望见梯田稻浪，置身于养生石寨的清泉石上居体会闲情逸致……相比“千店一面”的酒店，如今，强调个性化的旅游民宿成了越来越多游客的新选择，也间接推动了景宁乡村旅游的发展。', '2018-01-22 15:54:54');
INSERT INTO `tb_news` VALUES ('5', '新闻五', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/news/20180122154530.jpg', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body>西餐宴会创意摆台的9位选手各有主题，相对于中式宴会创意摆台的大圆桌，西式宴会创意摆台用的是精致的长方形桌子。选手们专业地摆好桌布，细致认真地把酒杯、餐具等放上，调整桌位间距，最后摆好自己精心挑选的主题装饰，一个个让人耳目一新的西式宴会创意摆台呈现在评委老师眼前。客房中式铺床服务技能竞赛考验的是选手床单和被套的折法、枕头的摆放等专业技能。在比赛之前，选手们细心整理好比赛寝具，然后向评委老师展示仪容仪表，接着开始信心的铺床。经过严谨的铺床过程，一张张整洁舒适的客房床铺呈现在评委们的面前。</body></html>', '4', '西餐宴会创意摆台的9位选手各有主题，相对于中式宴会创意摆台的大圆桌，西式宴会创意摆台用的是精致的长方形桌子。选手们专业地摆好桌布，细致认真地把酒杯、餐具等放上，调整桌位间距，最后摆好自己精心挑选的主题装饰，一个个让人耳目一新的西式宴会创意摆台呈现在评委老师眼前。客房中式铺床服务技能竞赛考验的是选手床单和被套的折法、枕头的摆放等专业技能。在比赛之前，选手们细心整理好比赛寝具，然后向评委老师展示仪容仪表，接着开始信心的铺床。经过严谨的铺床过程，一张张整洁舒适的客房床铺呈现在评委们的面前。', '2018-01-22 15:56:49');
INSERT INTO `tb_news` VALUES ('6', '新闻六', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/news/20180122154535.jpg', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body>新闻六的内容...</body></html>', '14', '新闻六的内容...', '2018-01-22 15:57:27');
INSERT INTO `tb_news` VALUES ('7', '新闻七', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/news/20180122154538.jpg', '<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> <style>img{max-width: 100%; width:auto; height:auto;}</style></head><body>新闻七的内容...</body></html>', '30', '新闻七的内容...', '2018-01-22 15:57:48');

-- ----------------------------
-- Table structure for tb_news_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_news_comment`;
CREATE TABLE `tb_news_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `fk_tb_news_id` int(11) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_18` (`fk_tb_user_id`),
  KEY `FK_Relationship_19` (`fk_tb_news_id`),
  CONSTRAINT `FK_Relationship_18` FOREIGN KEY (`fk_tb_user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK_Relationship_19` FOREIGN KEY (`fk_tb_news_id`) REFERENCES `tb_news` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_news_comment
-- ----------------------------
INSERT INTO `tb_news_comment` VALUES ('1', '2', '1', '测试评论...', '2018-01-23 09:50:26');
INSERT INTO `tb_news_comment` VALUES ('2', '2', '1', '测试评论02...', '2018-01-23 09:50:46');
INSERT INTO `tb_news_comment` VALUES ('3', '2', '7', '哈哈哈哈', '2018-01-23 10:04:19');
INSERT INTO `tb_news_comment` VALUES ('4', '2', '7', '呵呵', '2018-01-23 10:04:32');

-- ----------------------------
-- Table structure for tb_news_up
-- ----------------------------
DROP TABLE IF EXISTS `tb_news_up`;
CREATE TABLE `tb_news_up` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_news_id` int(11) DEFAULT NULL,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_20` (`fk_tb_news_id`),
  KEY `FK_Relationship_21` (`fk_tb_user_id`),
  CONSTRAINT `FK_Relationship_20` FOREIGN KEY (`fk_tb_news_id`) REFERENCES `tb_news` (`id`),
  CONSTRAINT `FK_Relationship_21` FOREIGN KEY (`fk_tb_user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_news_up
-- ----------------------------
INSERT INTO `tb_news_up` VALUES ('1', '1', '2', '2018-01-23 09:58:24');
INSERT INTO `tb_news_up` VALUES ('2', '7', '2', '2018-01-23 10:00:06');

-- ----------------------------
-- Table structure for tb_order_active
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_active`;
CREATE TABLE `tb_order_active` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_active_id` int(11) DEFAULT NULL,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `fk_tb_order_status_id` int(11) DEFAULT NULL,
  `number` varchar(100) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `total_money` float DEFAULT NULL,
  `real_money` float DEFAULT NULL,
  `tourist_name` varchar(50) DEFAULT NULL,
  `tourist_phone` varchar(100) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_25` (`fk_tb_user_id`),
  KEY `FK_Relationship_37` (`fk_tb_order_status_id`),
  KEY `FK_Relationship_40` (`fk_tb_active_id`),
  CONSTRAINT `FK_Relationship_25` FOREIGN KEY (`fk_tb_user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK_Relationship_37` FOREIGN KEY (`fk_tb_order_status_id`) REFERENCES `tb_order_status` (`id`),
  CONSTRAINT `FK_Relationship_40` FOREIGN KEY (`fk_tb_active_id`) REFERENCES `tb_active` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_active
-- ----------------------------
INSERT INTO `tb_order_active` VALUES ('2', '2', '1', '3', 'active180123154140235000', '1', '399', '369', null, null, '2018-01-23 15:41:40');
INSERT INTO `tb_order_active` VALUES ('3', '6', '1', '1', 'active180124102736343000', '2', '596', '596', null, null, '2018-01-24 10:27:36');
INSERT INTO `tb_order_active` VALUES ('4', '2', '1', '1', 'active180126115214832001', '1', '399', '399', 'ziven', '18756921527', '2018-01-26 11:52:15');
INSERT INTO `tb_order_active` VALUES ('5', '2', '1', '1', 'active180126115927903000', '1', '399', '399', 'ziven', '18756921527', '2018-01-26 11:59:28');
INSERT INTO `tb_order_active` VALUES ('6', '2', '2', '1', 'active180126115929716001', '2', '798', '798', 'agan', '18856049631', '2018-01-26 11:59:30');
INSERT INTO `tb_order_active` VALUES ('7', '5', '2', '1', 'active180126130518767002', '1', '654', '654', 'agan', '18856049631', '2018-01-26 13:05:19');
INSERT INTO `tb_order_active` VALUES ('8', '4', '2', '1', 'active180126131526515003', '1', '999', '999', 'agan', '18856049631', '2018-01-26 13:15:27');
INSERT INTO `tb_order_active` VALUES ('9', '4', '5', '1', 'active180131153652537000', '1', '999', '999', 'ziven', '18759921527', '2018-01-31 15:36:53');

-- ----------------------------
-- Table structure for tb_order_food
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_food`;
CREATE TABLE `tb_order_food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_room_time_id` int(11) DEFAULT NULL,
  `fk_tb_order_status_id` int(11) DEFAULT NULL,
  `fk_tb_user_id` int(11) DEFAULT NULL,
  `number` varchar(100) DEFAULT NULL,
  `total_money` float DEFAULT NULL COMMENT 'δ�����ۿ۵��ܼ�',
  `real_money` float DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_22` (`fk_tb_user_id`),
  KEY `FK_Relationship_36` (`fk_tb_order_status_id`),
  KEY `FK_Relationship_31` (`fk_tb_room_time_id`),
  CONSTRAINT `FK_Relationship_22` FOREIGN KEY (`fk_tb_user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK_Relationship_31` FOREIGN KEY (`fk_tb_room_time_id`) REFERENCES `tb_room_time` (`id`),
  CONSTRAINT `FK_Relationship_36` FOREIGN KEY (`fk_tb_order_status_id`) REFERENCES `tb_order_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_food
-- ----------------------------
INSERT INTO `tb_order_food` VALUES ('1', '4', '1', '1', 'food180130173849018000', '100', '100', '2018-01-30 17:38:49');
INSERT INTO `tb_order_food` VALUES ('2', '5', '3', '1', 'food180130174256158001', '50', '40', '2018-01-30 17:42:56');
INSERT INTO `tb_order_food` VALUES ('4', '7', '1', '2', 'food180202163817401001', '80', '80', '2018-02-02 16:38:17');
INSERT INTO `tb_order_food` VALUES ('5', '8', '1', '2', 'food180202164203029002', '80', '80', '2018-02-02 16:42:03');

-- ----------------------------
-- Table structure for tb_order_food_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_food_menu`;
CREATE TABLE `tb_order_food_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_order_food_id` int(11) DEFAULT NULL,
  `fk_tb_food_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_30` (`fk_tb_order_food_id`),
  KEY `FK_Relationship_33` (`fk_tb_food_id`),
  CONSTRAINT `FK_Relationship_30` FOREIGN KEY (`fk_tb_order_food_id`) REFERENCES `tb_order_food` (`id`),
  CONSTRAINT `FK_Relationship_33` FOREIGN KEY (`fk_tb_food_id`) REFERENCES `tb_food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_food_menu
-- ----------------------------
INSERT INTO `tb_order_food_menu` VALUES ('1', '1', '1', '2');
INSERT INTO `tb_order_food_menu` VALUES ('2', '1', '2', '3');
INSERT INTO `tb_order_food_menu` VALUES ('3', '2', '3', '2');
INSERT INTO `tb_order_food_menu` VALUES ('4', '2', '4', '1');
INSERT INTO `tb_order_food_menu` VALUES ('5', '4', '1', '2');
INSERT INTO `tb_order_food_menu` VALUES ('6', '4', '3', '1');
INSERT INTO `tb_order_food_menu` VALUES ('7', '4', '2', '1');
INSERT INTO `tb_order_food_menu` VALUES ('8', '5', '1', '1');
INSERT INTO `tb_order_food_menu` VALUES ('9', '5', '2', '1');
INSERT INTO `tb_order_food_menu` VALUES ('10', '5', '3', '2');

-- ----------------------------
-- Table structure for tb_order_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_status`;
CREATE TABLE `tb_order_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_status
-- ----------------------------
INSERT INTO `tb_order_status` VALUES ('1', null, '已提交', '2018-01-20 17:41:34');
INSERT INTO `tb_order_status` VALUES ('2', null, '已取消', '2018-01-20 17:41:58');
INSERT INTO `tb_order_status` VALUES ('3', null, '已支付', '2018-01-20 17:42:02');
INSERT INTO `tb_order_status` VALUES ('4', null, '已完成', '2018-01-20 17:42:12');

-- ----------------------------
-- Table structure for tb_promotion
-- ----------------------------
DROP TABLE IF EXISTS `tb_promotion`;
CREATE TABLE `tb_promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_promotion
-- ----------------------------
INSERT INTO `tb_promotion` VALUES ('1', '新人专属优惠', '新用户注册享专属新人红包', null, '2018-02-08 14:08:27');
INSERT INTO `tb_promotion` VALUES ('2', '摄影大赛', null, null, '2018-02-08 14:08:53');
INSERT INTO `tb_promotion` VALUES ('3', '蒿子粑粑节', null, null, '2018-02-08 14:09:01');

-- ----------------------------
-- Table structure for tb_promotion_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tb_promotion_coupon`;
CREATE TABLE `tb_promotion_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_promotion_id` int(11) DEFAULT NULL,
  `fk_tb_coupon_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Relationship_49` (`fk_tb_promotion_id`),
  KEY `Relationship_50` (`fk_tb_coupon_id`),
  CONSTRAINT `Relationship_49` FOREIGN KEY (`fk_tb_promotion_id`) REFERENCES `tb_promotion` (`id`),
  CONSTRAINT `Relationship_50` FOREIGN KEY (`fk_tb_coupon_id`) REFERENCES `tb_coupon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_promotion_coupon
-- ----------------------------
INSERT INTO `tb_promotion_coupon` VALUES ('6', '1', '2');
INSERT INTO `tb_promotion_coupon` VALUES ('7', '1', '9');
INSERT INTO `tb_promotion_coupon` VALUES ('8', '1', '6');
INSERT INTO `tb_promotion_coupon` VALUES ('9', '2', '3');
INSERT INTO `tb_promotion_coupon` VALUES ('10', '2', '13');
INSERT INTO `tb_promotion_coupon` VALUES ('11', '3', '7');

-- ----------------------------
-- Table structure for tb_promotion_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_promotion_record`;
CREATE TABLE `tb_promotion_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_promotion_id` int(11) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Relationship_51` (`fk_tb_promotion_id`),
  CONSTRAINT `Relationship_51` FOREIGN KEY (`fk_tb_promotion_id`) REFERENCES `tb_promotion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_promotion_record
-- ----------------------------
INSERT INTO `tb_promotion_record` VALUES ('2', '1', '15077901467', '2018-02-08 14:12:37');
INSERT INTO `tb_promotion_record` VALUES ('3', '2', '15077901467', '2018-02-09 09:10:48');

-- ----------------------------
-- Table structure for tb_recommendation
-- ----------------------------
DROP TABLE IF EXISTS `tb_recommendation`;
CREATE TABLE `tb_recommendation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_recommendation
-- ----------------------------
INSERT INTO `tb_recommendation` VALUES ('4', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/recommendation/banner1.jpg');
INSERT INTO `tb_recommendation` VALUES ('5', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/recommendation/banner2.jpg');
INSERT INTO `tb_recommendation` VALUES ('6', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/recommendation/banner3.jpg');

-- ----------------------------
-- Table structure for tb_remark
-- ----------------------------
DROP TABLE IF EXISTS `tb_remark`;
CREATE TABLE `tb_remark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_remark
-- ----------------------------
INSERT INTO `tb_remark` VALUES ('1', '亲子活动', '2018-01-23 11:23:22');

-- ----------------------------
-- Table structure for tb_room
-- ----------------------------
DROP TABLE IF EXISTS `tb_room`;
CREATE TABLE `tb_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(1024) DEFAULT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `number` varchar(1024) DEFAULT NULL,
  `people` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_room
-- ----------------------------
INSERT INTO `tb_room` VALUES ('1', null, '一层302房', '302', '8', '2018-01-26 15:15:44');

-- ----------------------------
-- Table structure for tb_room_time
-- ----------------------------
DROP TABLE IF EXISTS `tb_room_time`;
CREATE TABLE `tb_room_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_time_id` int(11) NOT NULL,
  `fk_tb_room_id` int(11) NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Relationship_55` (`fk_tb_time_id`),
  KEY `FK_Relationship_56` (`fk_tb_room_id`),
  CONSTRAINT `tb_room_time_ibfk_1` FOREIGN KEY (`fk_tb_time_id`) REFERENCES `tb_timestamp` (`id`),
  CONSTRAINT `tb_room_time_ibfk_2` FOREIGN KEY (`fk_tb_room_id`) REFERENCES `tb_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_room_time
-- ----------------------------
INSERT INTO `tb_room_time` VALUES ('2', '1', '1', '2018-01-26');
INSERT INTO `tb_room_time` VALUES ('4', '2', '1', '2018-01-30');
INSERT INTO `tb_room_time` VALUES ('5', '2', '1', '2018-01-31');
INSERT INTO `tb_room_time` VALUES ('7', '1', '1', '2018-02-08');
INSERT INTO `tb_room_time` VALUES ('8', '1', '1', '2018-02-15');

-- ----------------------------
-- Table structure for tb_speciality
-- ----------------------------
DROP TABLE IF EXISTS `tb_speciality`;
CREATE TABLE `tb_speciality` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_tb_speciality_type_id` int(11) NOT NULL,
  `name` varchar(1024) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `sale` int(11) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`fk_tb_speciality_type_id`),
  KEY `FK_Relationship_48` (`fk_tb_speciality_type_id`),
  CONSTRAINT `FK_Relationship_48` FOREIGN KEY (`fk_tb_speciality_type_id`) REFERENCES `tb_speciality_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_speciality
-- ----------------------------
INSERT INTO `tb_speciality` VALUES ('1', '1', '六安瓜片', '499', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/speciality/guapian006966690.jpg', '0', '六安瓜片是一种片状烘青绿茶，因产地不同，各有特色。茶外形平展，茶芽肥壮，叶缘微翘， 叶质柔软，大小匀整。色泽翠绿有光，香气清高，滋味鲜醇，回味甘美，汤色清澈晶亮，叶 底嫩绿。齐云山产茶又有“内山”和“外山”之别，“内山”是指黄石、里冲等地，“内山” 茶优于“外山”。', '2018-01-23 09:41:54');
INSERT INTO `tb_speciality` VALUES ('3', '1', '霍山黄芽', '600', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/speciality/20160308155445731.jpg', '0', '霍山黄芽主产安徽霍山大化坪镇金鸡山、太阳乡金竹坪、上土市九宫山、单龙寺、磨子谭、胡家河等地。霍山黄芽为中国名茶之一。该茶外形条直微展，匀齐成朵、形似雀舌、嫩绿披毫，香气清香持久，滋味鲜醇浓厚回甘，汤色黄绿清澈明亮，叶底嫩黄明亮。', '2018-01-25 17:04:51');
INSERT INTO `tb_speciality` VALUES ('4', '4', '金寨猕猴桃 ', '199', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/speciality/34347.gif', '0', '金寨猕猴桃的感官特色：果形端正，色泽艳丽，大小均一，肉质细嫩，红肉品种甜味浓郁，黄肉品种甜酸可口，绿肉品种风味酸甜，均具有浓厚的猕猴桃香味。', '2018-01-25 17:07:30');
INSERT INTO `tb_speciality` VALUES ('5', '1', '舒城小兰花 ', '259', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/speciality/18852.gif', '0', '传说在清朝以前，当地士、绅阶层极为讲究兰花茶生产”，由他主编的《中国名茶研究选集》和《制茶学》中阐明，舒城小兰花茶与碧螺春、太平猴魁、涌溪火青、六安瓜片、铁观音等名茶同在清朝创制。据此，兰花茶迄今至少已有二百多年历史。', '2018-01-25 17:08:44');
INSERT INTO `tb_speciality` VALUES ('6', '2', '金寨天麻 ', '128', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/speciality/32676.gif', '0', '天麻性味甘，平，有平肝息风的功能，常用于头痛眩晕、肢体麻木等症。《神农本草经》记载，天麻有“主杀鬼精物、蛊毒恶气，久服益气力、长阴肥健、轻身增年”的奇效；《名医别录》记载，天麻能“消痛肿，下支满，寒疝下血”；《日华子本草》记载，天麻能“助阳气补五劳七伤，通血脉，开窍”；《开宝本草》记载，天麻能“主诸风湿痹，四肢拘挛，小儿风痫、惊气，利腰膝，强筋骨”；《本草纲目》记载，“天麻，乃肝经气分之药”。《素问》云：诸风掉眩，皆属于木。故天麻入厥阴之经而治诸病。按罗天益云：眼黑头眩，虚风内作，非天麻不能治。天麻乃定风草，故为治风之神药“；《小儿药证直诀》、《圣济总录》、《普济方》、《外科正宗》、《兰台轨范》等记载，天麻分别配伍相关药物，可治疗急惊风发热、痉挛抽搐、偏正头痛、筋骨疼痛、风湿脚气、早衰白发、眩晕、肢体麻木、手足不遂、肝虚头痛、中风偏瘫、破伤风等症。此外，还用于保健。《本草衍义》记载，“天麻，用根须别药相佐使，然后见其功。仍须加用之。人或蜜渍为果，或蒸煮食，用天麻者，深思之则得矣”。清光绪年间，慈禧太后患面风，用天麻配伍其它祛风活络药，研末酒调，热熨患部；光绪头痛眩晕，也常用天麻配伍相关药物煎水洗头。在现代，天麻在医疗保健中的应用更为广泛。《中国药典》记载，天麻有“具平肝熄风、祛风定惊的功效，用于头晕目眩，肢体麻木，小儿惊风，癫痫，高血压，耳源性眩晕”。《全国中草药汇编》记载，天麻“主治高血压、眩晕、头痛、口眼歪斜、肢体麻木、小儿惊厥等症”。一些名医把天麻的作用归结为“三抗、三镇、一补”，即抗癫痫、抗惊厥、抗风湿，镇静、镇痉、镇痛，补虚。近年来，对天麻的医疗保健作用不断有新发现。将天麻用于高空飞行人员，作为脑保健药物，可增强视神经的分辨能力；日本用天麻治疗老年性痴呆症。随着科学技术的发展，对天麻化学成分及药理的认识不断深入，对天麻产品的开发也在不断扩大。', '2018-01-25 17:10:28');
INSERT INTO `tb_speciality` VALUES ('7', '3', '天堂寨小吊酒', '999', 'http://avatarcn.oss-cn-hangzhou.aliyuncs.com/tourists/speciality/46562.gif', '0', '小吊酒是安徽、河南以及湖北交界处的大别山的特产。千百年来，大别山的农户们就一直酿造小吊酒。每年秋收后，农民用辣蓼花制作的酒曲，将大米或稻谷加水蒸熟，拌入酒曲发酵十四、五天后，闻酒香加热蒸溜，出酒。这种酒口感较好，但很有后劲。农民酿成多留自饮、也有待客的，也有馈赠亲友的。', '2018-01-25 17:12:10');

-- ----------------------------
-- Table structure for tb_speciality_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_speciality_type`;
CREATE TABLE `tb_speciality_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `icon` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_speciality_type
-- ----------------------------
INSERT INTO `tb_speciality_type` VALUES ('1', '茶叶', null);
INSERT INTO `tb_speciality_type` VALUES ('2', '药材', null);
INSERT INTO `tb_speciality_type` VALUES ('3', '饮品', null);
INSERT INTO `tb_speciality_type` VALUES ('4', '食品', null);

-- ----------------------------
-- Table structure for tb_timestamp
-- ----------------------------
DROP TABLE IF EXISTS `tb_timestamp`;
CREATE TABLE `tb_timestamp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_timestamp
-- ----------------------------
INSERT INTO `tb_timestamp` VALUES ('1', '11:00:00', '13:00:00');
INSERT INTO `tb_timestamp` VALUES ('2', '16:00:00', '18:00:00');
INSERT INTO `tb_timestamp` VALUES ('3', '19:00:00', '21:00:00');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `server_id` varchar(1024) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '116', '1');
INSERT INTO `tb_user` VALUES ('2', '91', '1');
INSERT INTO `tb_user` VALUES ('3', '90', '1');
INSERT INTO `tb_user` VALUES ('4', '109', '1');
INSERT INTO `tb_user` VALUES ('5', '119', '1');
INSERT INTO `tb_user` VALUES ('6', '111', '1');
INSERT INTO `tb_user` VALUES ('7', '65', '1');
