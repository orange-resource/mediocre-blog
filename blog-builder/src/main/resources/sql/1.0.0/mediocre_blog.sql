/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : mediocre_blog

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2021-11-13 12:10:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员用户表';

-- ----------------------------
-- Table structure for t_aliyun_oss
-- ----------------------------
DROP TABLE IF EXISTS `t_aliyun_oss`;
CREATE TABLE `t_aliyun_oss` (
  `scheme_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'none',
  `domain_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '前缀域名',
  `endpoint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `key_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `key_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `catalogue` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '目录路径',
  `region` varchar(255) DEFAULT NULL COMMENT 'bucket所在的区域， 默认oss-cn-hangzhou',
  PRIMARY KEY (`scheme_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='阿里云oss存储对象配置';

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` varchar(50) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `is_show` int(2) DEFAULT NULL COMMENT '1=显示 2=不显示',
  `is_recommend` int(2) DEFAULT NULL COMMENT '1=推荐首页 2=不推荐到首页',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `content_html` longtext,
  `master_image_url` varchar(255) DEFAULT NULL COMMENT '主图',
  `uri` varchar(50) DEFAULT NULL COMMENT '文章资源标识符',
  `category_id` varchar(50) DEFAULT NULL,
  `category_child_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_uri` (`uri`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `master_image_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '主图',
  `sort` int(11) DEFAULT NULL COMMENT '排序 大的数排最前面',
  `open_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='首页轮播图';

-- ----------------------------
-- Table structure for t_blogroll
-- ----------------------------
DROP TABLE IF EXISTS `t_blogroll`;
CREATE TABLE `t_blogroll` (
  `id` varchar(50) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `open_url` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序 大的数排最前面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='友情链接';

-- ----------------------------
-- Table structure for t_blog_version
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_version`;
CREATE TABLE `t_blog_version` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `version_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_version_number` (`version_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统版本号，版本兼容性检查，无需关注这张表';

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` varchar(50) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `pid` varchar(50) DEFAULT NULL COMMENT '父级id',
  `sort` int(11) DEFAULT NULL COMMENT '排序 大的数排最前面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类';

-- ----------------------------
-- Table structure for t_file_record
-- ----------------------------
DROP TABLE IF EXISTS `t_file_record`;
CREATE TABLE `t_file_record` (
  `id` varchar(50) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '文件现在名称',
  `original_name` varchar(255) DEFAULT NULL COMMENT '原始文件名称',
  `file_suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `url` varchar(255) DEFAULT NULL COMMENT '访问网址',
  `cloud_path` varchar(255) DEFAULT NULL COMMENT '文件云存放路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件上传记录';

-- ----------------------------
-- Table structure for t_rss_config
-- ----------------------------
DROP TABLE IF EXISTS `t_rss_config`;
CREATE TABLE `t_rss_config` (
  `scheme_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'none',
  `title` varchar(255) DEFAULT NULL COMMENT '频道名称',
  `link` varchar(255) DEFAULT NULL COMMENT 'web域名 比如http://www.ongsat.com',
  `description` varchar(255) DEFAULT NULL COMMENT '频道的描述',
  `language` varchar(50) DEFAULT NULL COMMENT '频道内容使用的语言 默认zh-cn',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `copyright` varchar(255) DEFAULT NULL,
  `web_master` varchar(50) DEFAULT NULL COMMENT 'feed管理员的电子邮件地址',
  `managing_editor` varchar(50) DEFAULT NULL COMMENT 'feed内容编辑的电子邮件地址',
  PRIMARY KEY (`scheme_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='RSS配置';

-- ----------------------------
-- Table structure for t_section
-- ----------------------------
DROP TABLE IF EXISTS `t_section`;
CREATE TABLE `t_section` (
  `id` varchar(50) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `master_image_url` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序 大的数排最前面',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面右边自定义块';

-- ----------------------------
-- Table structure for t_system_config
-- ----------------------------
DROP TABLE IF EXISTS `t_system_config`;
CREATE TABLE `t_system_config` (
  `scheme_name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT 'none',
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '网站标题',
  `footer` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '网站底部信息文字',
  `seo_keywords` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'seo关键词',
  `seo_description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'seo描述',
  `logo_url` longtext COLLATE utf8mb4_bin COMMENT '网站logo',
  `page_gray_mode` int(2) DEFAULT NULL COMMENT '0=正常颜色 1=页面灰色',
  `big_logo_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `common_head_html` text COLLATE utf8mb4_bin,
  `common_footer_html` text COLLATE utf8mb4_bin,
  PRIMARY KEY (`scheme_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统配置';
