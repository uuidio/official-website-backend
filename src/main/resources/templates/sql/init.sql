

SET NAMES utf8;


-- ----------------------------
-- Table structure for about_platform
-- ----------------------------
DROP TABLE IF EXISTS `about_platform`;
CREATE TABLE `about_platform`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about_platform_id` bigint(20) NULL DEFAULT NULL,
  `about_platform_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `about_platform_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `about_platform_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 231 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for about_platform_icon
-- ----------------------------
DROP TABLE IF EXISTS `about_platform_icon`;
CREATE TABLE `about_platform_icon`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pic_id` bigint(20) NULL DEFAULT NULL,
  `about_platform_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for carousel_pic_comp
-- ----------------------------
DROP TABLE IF EXISTS `carousel_pic_comp`;
CREATE TABLE `carousel_pic_comp`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sequence` int(10) NULL DEFAULT 0,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  `is_blank` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for common_code
-- ----------------------------
DROP TABLE IF EXISTS `common_code`;
CREATE TABLE `common_code`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `show_order` int(10) NULL DEFAULT NULL,
  `remark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for footer
-- ----------------------------
DROP TABLE IF EXISTS `footer`;
CREATE TABLE `footer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for homebanner
-- ----------------------------
DROP TABLE IF EXISTS `homebanner`;
CREATE TABLE `homebanner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `home_banner_id` bigint(20) NULL DEFAULT NULL,
  `banner_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for homelogo
-- ----------------------------
DROP TABLE IF EXISTS `homelogo`;
CREATE TABLE `homelogo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `logo_id` bigint(20) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for homepage
-- ----------------------------
DROP TABLE IF EXISTS `homepage`;
CREATE TABLE `homepage`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `home_banner_id` bigint(20) NULL DEFAULT NULL COMMENT '轮播图',
  `about_platform_id` bigint(20) NULL DEFAULT NULL COMMENT '关于平台内容',
  `platform_dynamics_id` bigint(20) NULL DEFAULT NULL COMMENT '第三页内容及图片',
  `about_platform_icon` bigint(20) NULL DEFAULT NULL COMMENT 'icon',
  `platform_dynamics_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三块图片',
  `about_platform_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第二页图片',
  `about_platform_regist` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '注册链接',
  `about_platform_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第二页标题',
  `about_platform_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第二页标题下内容',
  `logo_id` bigint(20) NULL DEFAULT NULL COMMENT 'logo',
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  `platform_dynamics_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三块图片里面的内容的标题',
  `platform_dynamics_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三块图片里面内容的内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for identify
-- ----------------------------
DROP TABLE IF EXISTS `identify`;
CREATE TABLE `identify`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `footer_id` bigint(20) NULL DEFAULT NULL COMMENT '关联列id',
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转地址',
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for introduce_content_title
-- ----------------------------
DROP TABLE IF EXISTS `introduce_content_title`;
CREATE TABLE `introduce_content_title`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标识',
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for introduce_page
-- ----------------------------
DROP TABLE IF EXISTS `introduce_page`;
CREATE TABLE `introduce_page`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `banner_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for introduce_page_content
-- ----------------------------
DROP TABLE IF EXISTS `introduce_page_content`;
CREATE TABLE `introduce_page_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `titleId` bigint(255) NULL DEFAULT NULL COMMENT '标识',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `before_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点击前图标',
  `after_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点击后图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for introduce_pic
-- ----------------------------
DROP TABLE IF EXISTS `introduce_pic`;
CREATE TABLE `introduce_pic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pic_id` bigint(20) NULL DEFAULT NULL,
  `introduce_page_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for one_key_article
-- ----------------------------
DROP TABLE IF EXISTS `one_key_article`;
CREATE TABLE `one_key_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `click_num` bigint(20) NULL DEFAULT 0 COMMENT '文章点击数',
  `article_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发表人',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章图片',
  `one_key_category_id` bigint(20) NULL DEFAULT NULL COMMENT '文章类别',
  `one_key_two_menu_id` bigint(20) NULL DEFAULT NULL COMMENT '二级菜单',
  `one_key_college_id` bigint(20) NULL DEFAULT NULL COMMENT '学院id 固定',
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for one_key_article_label
-- ----------------------------
DROP TABLE IF EXISTS `one_key_article_label`;
CREATE TABLE `one_key_article_label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label_id` bigint(20) NULL DEFAULT NULL,
  `article_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for one_key_category
-- ----------------------------
DROP TABLE IF EXISTS `one_key_category`;
CREATE TABLE `one_key_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `one_key_college_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for one_key_college
-- ----------------------------
DROP TABLE IF EXISTS `one_key_college`;
CREATE TABLE `one_key_college`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for one_key_label
-- ----------------------------
DROP TABLE IF EXISTS `one_key_label`;
CREATE TABLE `one_key_label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for one_key_two_menu
-- ----------------------------
DROP TABLE IF EXISTS `one_key_two_menu`;
CREATE TABLE `one_key_two_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `one_key_category_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_test` bit(1) NULL DEFAULT b'0',
  `level` int(11) NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `is_delete` bit(1) NULL DEFAULT NULL,
  `is_parent` bit(1) NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CODE_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_delete` bit(1) NULL DEFAULT NULL,
  `is_parent` bit(1) NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CODE_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permission_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `permission_role_rel`;
CREATE TABLE `permission_role_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 787 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permission_url_rel
-- ----------------------------
DROP TABLE IF EXISTS `permission_url_rel`;
CREATE TABLE `permission_url_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `old_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picture_grouping_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for picture_grouping
-- ----------------------------
DROP TABLE IF EXISTS `picture_grouping`;
CREATE TABLE `picture_grouping`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picture_ownership` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for platform_dynamics
-- ----------------------------
DROP TABLE IF EXISTS `platform_dynamics`;
CREATE TABLE `platform_dynamics`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `platform_dynamics_id` bigint(20) NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for platform_dynamics_article
-- ----------------------------
DROP TABLE IF EXISTS `platform_dynamics_article`;
CREATE TABLE `platform_dynamics_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `image` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `platform_dynamics_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_describe_thr
-- ----------------------------
DROP TABLE IF EXISTS `product_describe_thr`;
CREATE TABLE `product_describe_thr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `product_service_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_describe_thr_content
-- ----------------------------
DROP TABLE IF EXISTS `product_describe_thr_content`;
CREATE TABLE `product_describe_thr_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `thr_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_describe_two
-- ----------------------------
DROP TABLE IF EXISTS `product_describe_two`;
CREATE TABLE `product_describe_two`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `image` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `product_service_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_service
-- ----------------------------
DROP TABLE IF EXISTS `product_service`;
CREATE TABLE `product_service`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varbinary(255) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `videoUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_service_content
-- ----------------------------
DROP TABLE IF EXISTS `product_service_content`;
CREATE TABLE `product_service_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_service_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_service_image
-- ----------------------------
DROP TABLE IF EXISTS `product_service_image`;
CREATE TABLE `product_service_image`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_service_id` bigint(20) NULL DEFAULT NULL,
  `pic_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NAME_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uuid` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unionId` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `push_reg_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `main_org_id` bigint(20) NULL DEFAULT NULL,
  `last_login_time` datetime NULL DEFAULT NULL,
  `is_delete` bit(1) NULL DEFAULT NULL,
  `is_disable` bit(1) NULL DEFAULT b'0',
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uuid_UNIQUE`(`uuid`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE,
  INDEX `name_index`(`name`) USING BTREE,
  INDEX `type_index`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `push_reg_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 865 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_org_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_org_rel`;
CREATE TABLE `user_org_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_user_id` bigint(20) NULL DEFAULT NULL,
  `r_org_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `r_user_id_index`(`r_user_id`) USING BTREE,
  INDEX `r_org_id_index`(`r_org_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id_index`(`role_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

INSERT INTO `org` VALUES (1, '0001', '一键开工运营团队', b'0', 1, '0001', 1, b'0', b'0', NULL, '2018-09-07 12:33:50', '2018-10-12 11:36:31');
INSERT INTO `role`(`id`, `alias`, `name`, `remark`, `create_time`, `last_modified_time`) VALUES (1, '管理员 ', 'ROLE_ADMIN', NULL, '2018-09-06 18:43:31', '2018-10-29 15:03:31');
INSERT INTO `role`(`id`, `alias`, `name`, `remark`, `create_time`, `last_modified_time`) VALUES (2, '运营人员', 'ROLE_OPERATE', NULL, '2018-11-29 11:13:11', '2018-11-29 11:13:11');

INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (23, '0011', '系统管理', 1, '0011', 15, 'menu', 'xit', NULL, b'1', NULL, 1, 1, '2018-06-28 09:58:15', '2018-11-29 10:17:05');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (25, '00110002', '权限管理', 2, '0011!00110002', 2, 'menu', '', NULL, b'0', 23, 1, 1, '2018-06-28 10:04:18', '2018-06-28 10:04:18');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (28, '00110005', '菜单管理', 2, '0011!00110005', 5, 'menu', '', NULL, b'0', 23, 1, 1, '2018-06-28 10:04:57', '2018-06-28 10:04:57');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (30, '00090005', '轮播图片管理', 2, '0009!00090005', 5, 'menu', NULL, b'0', b'0', 17, 1, 1, '2018-09-06 18:46:20', '2018-09-06 18:46:20');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (49, '0012', '页面管理', 1, '0012', 1, 'menu', 'gx', NULL, b'1', NULL, 1, 1, '2018-11-09 14:54:11', '2018-11-28 16:00:35');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (50, '00120001', '主页参数', 2, '0012!00120001', 1, 'menu', '', NULL, b'0', 49, 1, 1, '2018-11-09 14:54:37', '2018-11-09 14:59:00');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (51, '00120002', '图片管理', 2, '0012!00120002', 2, 'menu', '', NULL, b'0', 49, 1, 1, '2018-11-10 17:17:09', '2018-11-10 17:17:09');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (52, '00120003', '轮播图片管理', 2, '0012!00120003', 3, 'menu', '', NULL, b'0', 49, 1, 1, '2018-11-12 11:10:25', '2018-11-12 11:10:25');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (53, '00120004', '页脚编辑', 2, '0012!00120004', 6, 'menu', '', NULL, b'0', 49, 1, 1, '2018-11-13 18:38:02', '2018-11-25 16:05:03');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (65, '00120015', '品牌合作logo', 2, '0012!00120015', 4, 'menu', '', NULL, b'0', 49, 1, 1, '2018-11-23 12:07:10', '2018-11-25 16:05:11');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (67, '00120017', '平台动态文章列表', 2, '0012!00120017', 5, 'menu', '', NULL, b'0', 49, 1, 1, '2018-11-27 18:37:12', '2018-11-28 09:21:01');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (68, '0013', '平台介绍管理', 1, '0013', 12, 'menu', 'gx', NULL, b'1', NULL, 1, 1, '2018-11-28 15:45:25', '2018-11-28 15:56:02');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (71, '00130001', '平台介绍参数', 2, '0013!00130001', 1, 'menu', '', NULL, b'0', 68, 1, 1, '2018-11-28 15:55:22', '2018-11-28 15:55:22');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (72, '00130002', '平台介绍标题类别参数', 2, '0013!00130002', 2, 'menu', '', NULL, b'0', 68, 1, 1, '2018-11-28 15:55:43', '2018-11-29 10:22:14');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (73, '00130003', '平台介绍内容参数', 2, '0013!00130003', 3, 'menu', '', NULL, b'0', 68, 1, 1, '2018-11-28 15:56:02', '2018-11-28 15:56:02');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (74, '0014', '产品服务管理', 1, '0014', 13, 'menu', 'gx', NULL, b'1', NULL, 1, 1, '2018-11-28 15:57:07', '2018-11-28 15:59:05');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (75, '00140001', '产品服务主页', 2, '0014!00140001', 1, 'menu', '', NULL, b'0', 74, 1, 1, '2018-11-28 15:58:00', '2018-11-28 15:58:00');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (76, '00140002', '第二标题内容参数', 2, '0014!00140002', 2, 'menu', '', NULL, b'0', 74, 1, 1, '2018-11-28 15:58:28', '2018-11-28 15:58:28');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (77, '00140003', '第三标题内容参数', 2, '0014!00140003', 3, 'menu', '', NULL, b'0', 74, 1, 1, '2018-11-28 15:59:05', '2018-11-29 10:38:22');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (78, '0015', '一键学院管理', 1, '0015', 14, 'menu', 'gx', NULL, b'1', NULL, 1, 1, '2018-11-28 15:59:43', '2018-11-28 16:01:34');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (79, '00150001', '一键学院主页', 2, '0015!00150001', 1, 'menu', '', NULL, b'0', 78, 1, 1, '2018-11-28 16:00:13', '2018-11-28 16:00:13');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (81, '00150002', '新闻分类列表', 2, '0015!00150002', 2, 'menu', '', NULL, b'0', 78, 1, 1, '2018-11-28 16:00:56', '2018-11-28 16:00:56');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (82, '00150003', '新闻二级标题', 2, '0015!00150003', 3, 'menu', '', NULL, b'0', 78, 1, 1, '2018-11-28 16:01:13', '2018-11-28 16:01:13');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (83, '00150004', '新闻文章列表', 2, '0015!00150004', 4, 'menu', '', NULL, b'0', 78, 1, 1, '2018-11-28 16:01:34', '2018-11-28 16:01:34');
INSERT INTO `permission`(`id`, `code`, `name`, `level`, `path`, `sort`, `type`, `icon`, `is_delete`, `is_parent`, `parent_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (84, '00110006', '组织架构', 2, '0011!00110006', 6, 'menu', '', NULL, b'0', 23, 1, 1, '2018-11-29 10:16:48', '2018-11-29 10:16:48');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (745, 49, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (746, 83, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (747, 71, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (748, 25, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (749, 72, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (750, 82, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (751, 51, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (752, 23, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (753, 50, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (754, 84, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (755, 77, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (756, 78, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (757, 76, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (758, 65, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (759, 79, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (760, 75, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (761, 52, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (762, 74, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (763, 68, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (764, 28, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (765, 53, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (766, 81, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (767, 73, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (768, 67, 1, '2018-11-29 10:17:15', '2018-11-29 10:17:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (784, 83, 2, '2018-11-29 14:32:15', '2018-11-29 14:32:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (785, 78, 2, '2018-11-29 14:32:15', '2018-11-29 14:32:15');
INSERT INTO `permission_role_rel`(`id`, `permission_id`, `role_id`, `create_time`, `last_modified_time`) VALUES (786, NULL, 2, '2018-11-29 14:32:15', '2018-11-29 14:32:15');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (14, '/bg/role/v/index.html', 25, '2018-06-28 10:04:18', '2018-06-28 10:04:18');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (17, '/bg/menu/v/index.html', 28, '2018-06-28 10:04:57', '2018-06-28 10:04:57');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (19, '/bg/ec/carouselPicComp/v/index.html', 30, '2018-07-04 19:16:48', '2018-07-04 19:16:48');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (33, '/bg/picture/v/index.html', 14, '2018-11-08 10:57:04', '2018-11-08 10:57:04');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (36, '/bg/homePage/v/index.html', 50, '2018-11-09 14:59:00', '2018-11-09 14:59:00');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (37, '/bg/picture/v/index.html', 51, '2018-11-10 17:17:09', '2018-11-10 17:17:09');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (38, '/bg/carouselPicComp/v/index.html', 52, '2018-11-12 11:10:25', '2018-11-12 11:10:25');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (42, '/bg/homepage/v/index.html', 49, '2018-11-16 09:56:50', '2018-11-16 09:56:50');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (78, '/bg/footer/v/index.html', 53, '2018-11-25 16:05:03', '2018-11-25 16:05:03');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (79, '/bg/homeLogo/v/index.html', 65, '2018-11-25 16:05:11', '2018-11-25 16:05:11');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (81, '/bg/platformDy/v/index.html', 67, '2018-11-28 09:21:01', '2018-11-28 09:21:01');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (82, '/bg/introducePage/v/index.html', 71, '2018-11-28 15:55:22', '2018-11-28 15:55:22');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (84, '/bg/introduceContent/v/index.html', 73, '2018-11-28 15:56:02', '2018-11-28 15:56:02');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (85, '/bg/productService/v/index.html', 75, '2018-11-28 15:58:00', '2018-11-28 15:58:00');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (86, '/bg/productDescribeTwo/v/index.html', 76, '2018-11-28 15:58:28', '2018-11-28 15:58:28');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (88, '/bg/oneKeyCollege/v/index.html', 79, '2018-11-28 16:00:13', '2018-11-28 16:00:13');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (90, '/bg/oneKeyCategory/v/index.html', 81, '2018-11-28 16:00:56', '2018-11-28 16:00:56');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (91, '/bg/oneKeyTwo/v/index.html', 82, '2018-11-28 16:01:13', '2018-11-28 16:01:13');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (92, '/bg/oneKeyArticle/v/index.html', 83, '2018-11-28 16:01:34', '2018-11-28 16:01:34');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (93, '/bg/org/v/index.html', 84, '2018-11-29 10:16:48', '2018-11-29 10:16:48');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (94, '/bg/introduceContentTitle/v/index.html', 72, '2018-11-29 10:22:14', '2018-11-29 10:22:14');
INSERT INTO `permission_url_rel`(`id`, `url`, `permission_id`, `create_time`, `last_modified_time`) VALUES (95, '/bg/productDescribeThr/v/index.html', 77, '2018-11-29 10:38:22', '2018-11-29 10:38:22');
INSERT INTO `footer`(`id`, `name`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (11, '平台介绍', 1, 1, '2018-11-23 17:50:11', '2018-11-28 16:25:42');
INSERT INTO `footer`(`id`, `name`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (12, '产品服务', 1, 1, '2018-11-23 17:51:06', '2018-11-23 17:51:06');
INSERT INTO `footer`(`id`, `name`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (13, '一键学院', 1, 1, '2018-11-23 17:52:15', '2018-11-23 17:52:15');
INSERT INTO `footer`(`id`, `name`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (14, '帮助支持', 1, 1, '2018-11-23 17:52:40', '2018-11-23 17:52:40');
INSERT INTO `footer`(`id`, `name`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (15, '联系我们', 1, 1, '2018-11-23 17:53:09', '2018-11-23 17:53:09');

INSERT INTO `user`(`id`, `email`, `mobile`, `name`, `nickname`, `password`, `type`, `uuid`, `unionId`, `push_reg_id`, `main_org_id`, `last_login_time`, `is_delete`, `is_disable`, `create_time`, `last_modified_time`) VALUES (1, 'admin', '1234568978', 'admin', '管理员', 'admin', 'admin', '', '', NULL, 1, '2018-08-06 11:09:38', b'0', b'0', '2018-07-17 11:52:06', '2018-11-29 11:07:21');
INSERT INTO `user`(`id`, `email`, `mobile`, `name`, `nickname`, `password`, `type`, `uuid`, `unionId`, `push_reg_id`, `main_org_id`, `last_login_time`, `is_delete`, `is_disable`, `create_time`, `last_modified_time`) VALUES (2, 'aaaa', '123154654613', 'aaaa', '测试人员', 'aaaa', NULL, 'de491d02c4b1344353373249ab534581', '77e47d2e79562872c0febbaba03b73de', NULL, 1, NULL, b'0', b'0', '2018-11-29 11:11:44', '2018-11-29 11:12:01');

INSERT INTO `homepage`(`id`, `home_banner_id`, `about_platform_id`, `platform_dynamics_id`, `about_platform_icon`, `platform_dynamics_image`, `about_platform_image`, `about_platform_regist`, `about_platform_title`, `about_platform_content`, `logo_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`, `platform_dynamics_title`, `platform_dynamics_content`) VALUES (1, NULL, 1, 1, 1, '/uploads/1543477620765.jpg', '/uploads/1542967660233.jpg', 'http://www.baidu.com', '阿大声道', '啥的吖', 1, 1, 1, '2018-11-29 15:28:24', '2018-11-29 15:47:16', '热烈祝贺‘一键开工’在武汉体博会', '阿萨德斩获2018年度体育创新品牌奖');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (34, 12, '找工人', '', 1, 1, '2018-11-23 17:51:06', '2018-11-23 17:51:06');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (35, 12, '找施工', '', 1, 1, '2018-11-23 17:51:06', '2018-11-23 17:51:06');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (36, 12, '找材料', '', 1, 1, '2018-11-23 17:51:06', '2018-11-23 17:51:06');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (37, 12, '租设备', '', 1, 1, '2018-11-23 17:51:06', '2018-11-23 17:51:06');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (38, 13, '行业快讯', '', 1, 1, '2018-11-23 17:52:15', '2018-11-23 17:52:15');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (39, 13, '货料干货', '', 1, 1, '2018-11-23 17:52:15', '2018-11-23 17:52:15');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (40, 13, '一键商盟', '', 1, 1, '2018-11-23 17:52:15', '2018-11-23 17:52:15');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (41, 13, '公益活动', '', 1, 1, '2018-11-23 17:52:15', '2018-11-23 17:52:15');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (42, 14, '新手入门', '', 1, 1, '2018-11-23 17:52:40', '2018-11-23 17:52:40');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (43, 14, '常见问题', '', 1, 1, '2018-11-23 17:52:40', '2018-11-23 17:52:40');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (44, 14, '联系客服', '', 1, 1, '2018-11-23 17:52:40', '2018-11-23 17:52:40');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (45, 15, '立即注册', '', 1, 1, '2018-11-23 17:53:09', '2018-11-23 17:53:09');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (46, 15, '400-1107-017', '', 1, 1, '2018-11-23 17:53:09', '2018-11-23 17:53:09');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (47, 11, '我们的平台', '', 1, 1, '2018-11-28 16:25:42', '2018-11-28 16:25:42');
INSERT INTO `identify`(`id`, `footer_id`, `name`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (48, 11, '我们的团队', '', 1, 1, '2018-11-28 16:25:42', '2018-11-28 16:25:42');
INSERT INTO `introduce_page`(`id`, `banner_img`, `title`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (1, '/uploads/1542961935834.jpg', '用专业 重新定义行业标准', 1, 1, '2018-11-29 15:51:47', '2018-11-29 15:49:32');
INSERT INTO `one_key_college`(`id`, `image`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (1, NULL, 1, 1, '2018-11-29 16:05:12', '2018-11-29 16:07:50');
INSERT INTO `one_key_category`(`id`, `name`, `one_key_college_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (7, '结构设计', 1, 1, 1, '2018-11-29 16:08:33', '2018-11-29 16:08:33');
INSERT INTO `one_key_category`(`id`, `name`, `one_key_college_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (8, '建筑施工', 1, 1, 1, '2018-11-29 16:08:38', '2018-11-29 16:08:38');
INSERT INTO `one_key_category`(`id`, `name`, `one_key_college_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (9, '岩土工程', 1, 1, 1, '2018-11-29 16:08:43', '2018-11-29 16:08:43');
INSERT INTO `one_key_category`(`id`, `name`, `one_key_college_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (10, '路桥市政', 1, 1, 1, '2018-11-29 16:08:47', '2018-11-29 16:08:47');
INSERT INTO `one_key_category`(`id`, `name`, `one_key_college_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (11, '注册考试', 1, 1, 1, '2018-11-29 16:08:51', '2018-11-29 16:08:51');
INSERT INTO `one_key_two_menu`(`id`, `name`, `one_key_category_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (14, '土建造价', 7, 1, 1, '2018-11-29 16:09:10', '2018-11-29 16:09:10');
INSERT INTO `one_key_two_menu`(`id`, `name`, `one_key_category_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (15, '安装造价', 7, 1, 1, '2018-11-29 16:09:15', '2018-11-29 16:09:15');
INSERT INTO `one_key_two_menu`(`id`, `name`, `one_key_category_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (16, '市政造价', 8, 1, 1, '2018-11-29 16:09:22', '2018-11-29 16:09:22');
INSERT INTO `one_key_two_menu`(`id`, `name`, `one_key_category_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (17, '公路造价', 9, 1, 1, '2018-11-29 16:09:31', '2018-11-29 16:09:31');
INSERT INTO `one_key_two_menu`(`id`, `name`, `one_key_category_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (18, '园林造价', 10, 1, 1, '2018-11-29 16:09:37', '2018-11-29 16:09:37');
INSERT INTO `one_key_two_menu`(`id`, `name`, `one_key_category_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (19, '建筑识图', 11, 1, 1, '2018-11-29 16:09:47', '2018-11-29 16:09:47');
INSERT INTO `platform_dynamics`(`id`, `platform_dynamics_id`, `image`, `title`, `content`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (112, 1, NULL, '行业动态', '一站式场馆建设服务提供商', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `platform_dynamics`(`id`, `platform_dynamics_id`, `image`, `title`, `content`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (113, 1, NULL, '产品动态', '专注，口碑，极致，快', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `platform_dynamics`(`id`, `platform_dynamics_id`, `image`, `title`, `content`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (114, 1, NULL, '最新活动', '行业展会，峰会论坛，公益活动', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `product_service`(`id`, `image`, `title`, `videoUrl`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (1, 0x2F75706C6F6164732F313534323936373636303233332E6A7067, '国内首家体育设施建设行业一站式服务平台', 'http://vjs.zencdn.net/v/oceans.mp4', NULL, NULL, NULL, '2018-11-29 15:56:46');
INSERT INTO `product_service_content`(`id`, `title`, `content`, `product_service_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (48, '行业门户级信息平台', '工程信息发布，工匠查找,技术资料检索下载，材料设备及施工设备查询等需求一站式解决', 1, 1, 1, '2018-11-29 15:56:46', '2018-11-29 15:56:46');
INSERT INTO `product_service_content`(`id`, `title`, `content`, `product_service_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (49, '行业坐标级交易平台', '合才材料设备及施工设备资源，以及材料商,施工方，行业专家，第三方服务机构资源，建设专业化线上交易中心;为入驻施工方及甲方提供优质而专业的交易平台;', 1, 1, 1, '2018-11-29 15:56:46', '2018-11-29 15:56:46');
INSERT INTO `product_service_content`(`id`, `title`, `content`, `product_service_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (50, '行业标杆级服务平台', '通过建立甲方、材料商、施工方、专家及第三方服务机构的交流联动关系，对接第三方法务、检测、设计等技术服务，并为您提供系统化的专业综合性解决方案', 1, 1, 1, '2018-11-29 15:56:46', '2018-11-29 15:56:46');
INSERT INTO `product_describe_two`(`id`, `title`, `content`, `image`, `product_service_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (1, '五大平台级核心优势满足全行业需求', '全面集聚行业顶级资源，材料应有尽有\r\n\r\n专业行业专家学者汇聚，专业更有保障\r\n\r\n诚信全行业龙头级平台，够实力更诚信\r\n\r\n安全致力行业标准制定，规范铸就安全\r\n\r\n便捷一站式综合型平台，需求及时响应', '/uploads/1542961872737.jpg', NULL, NULL, NULL, NULL, '2018-11-29 15:59:56');
INSERT INTO `product_describe_thr_content`(`id`, `title`, `content`, `thr_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (13, '‘一键”帮工人’', '找工作，更加快捷\n\n评技能，更加规范\n\n领工资，更有保障', 60, 1, 1, '2018-11-29 16:00:48', '2018-11-29 16:00:48');
INSERT INTO `product_describe_thr_content`(`id`, `title`, `content`, `thr_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (14, '“一键”帮施工队', '找工人，靠谱可信\n\n找工程，真实可靠\n\n找材料，货真价实\n\n租设备，便利迅捷', 60, 1, 1, '2018-11-29 16:00:48', '2018-11-29 16:00:48');
INSERT INTO `product_describe_thr_content`(`id`, `title`, `content`, `thr_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (15, '“一键”帮工程', '找施工，靠谱又可控\n\n能监督，专业更省心\n\n查进度，自由可掌握', 60, 1, 1, '2018-11-29 16:00:48', '2018-11-29 16:00:48');
INSERT INTO `product_describe_thr_content`(`id`, `title`, `content`, `thr_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (16, '快', '查资料 够快\n\n找工人 够快\n\n租设备 够快\n\n承接业务 照样快', 61, 1, 1, '2018-11-29 16:01:44', '2018-11-29 16:01:44');
INSERT INTO `product_describe_thr_content`(`id`, `title`, `content`, `thr_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (17, '精', '帮商家推广原材料 更精准\n\n帮施工方选取材料 更精确\n\n帮工程方管理进度 更精细\n\n帮工人提升自己技能 更精心', 61, 1, 1, '2018-11-29 16:01:44', '2018-11-29 16:01:44');
INSERT INTO `product_describe_thr_content`(`id`, `title`, `content`, `thr_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (18, '准', '查找招投标信息 够准确\n\n工程款项结算 更准时\n\n相关技能培训 更有准头', 61, 1, 1, '2018-11-29 16:01:44', '2018-11-29 16:01:44');
INSERT INTO `product_describe_thr`(`id`, `title`, `image`, `product_service_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (60, '操作简单方便 响应快速细致 让每个使用者都满意', '/uploads/1542961872740.jpg', 1, 1, 1, '2018-11-29 16:00:48', '2018-11-29 16:00:48');
INSERT INTO `product_describe_thr`(`id`, `title`, `image`, `product_service_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (61, '加入一键开工 获取‘快精准’ 解决方案', '/uploads/1542961872712.jpg', 1, 1, 1, '2018-11-29 16:01:44', '2018-11-29 16:01:44');
INSERT INTO `introduce_page_content`(`id`, `title`, `sort`, `titleId`, `content`, `image`, `before_icon`, `after_icon`, `url`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (19, NULL, NULL, 7, '专业、迅速、准确地为企业找到合适的工匠', '/uploads/1543029549653.jpg', '/uploads/1543029749678.jpg', '/uploads/1543029749664.jpg', NULL, 1, 1, '2018-11-29 15:54:35', '2018-11-29 15:54:35');
INSERT INTO `introduce_content_title`(`id`, `title`, `code`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (7, '工程建设及管控', NULL, 1, 1, '2018-11-29 15:53:57', '2018-11-29 15:53:57');
INSERT INTO `about_platform`(`id`, `about_platform_id`, `about_platform_title`, `about_platform_content`, `about_platform_icon`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (226, 1, '24小时快速匹配工程需求', '集聚超200支行业顶尖施工队伍  提供工匠查找、快速匹配施工队服务', '/uploads/1543476156169.jpg', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `about_platform`(`id`, `about_platform_id`, `about_platform_title`, `about_platform_content`, `about_platform_icon`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (227, 1, '数十项工程管理工具', '工程信息发布、技术资料下载  材料设备交易、第三方法务、检测等面面俱到', '/uploads/1543476151067.jpg', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `about_platform`(`id`, `about_platform_id`, `about_platform_title`, `about_platform_content`, `about_platform_icon`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (228, 1, '系统化管控工程进度', '平台推出“ 场地勘察员、过程监察员”  设置，全程实时监控工程进度', '/uploads/1543476143542.jpg', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `about_platform`(`id`, `about_platform_id`, `about_platform_title`, `about_platform_content`, `about_platform_icon`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (229, 1, '系统化管控工程进度', '平台推出“ 场地勘察员、过程监察员”  设置，全程实时监控工程进度', '/uploads/1543476137246.jpg', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `about_platform`(`id`, `about_platform_id`, `about_platform_title`, `about_platform_content`, `about_platform_icon`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (230, 1, '系统化管控工程进度', '平台推出“ 场地勘察员、过程监察员”  设置，全程实时监控工程进度', '/uploads/1543476130873.jpg', 1, 1, '2018-11-29 15:47:16', '2018-11-29 15:47:16');
INSERT INTO `user_org_rel`(`id`, `r_user_id`, `r_org_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (2, 1, 1, 1, 1, '2018-11-28 16:08:13', '2018-11-28 16:08:13');
INSERT INTO `user_org_rel`(`id`, `r_user_id`, `r_org_id`, `org_id`, `creator_id`, `create_time`, `last_modified_time`) VALUES (5, 2, 1, 1, 2, '2018-11-29 11:13:18', '2018-11-29 11:13:18');
INSERT INTO `user_role_rel`(`id`, `role_id`, `user_id`, `create_time`, `last_modified_time`) VALUES (2, 1, 1, NULL, NULL);
INSERT INTO `user_role_rel`(`id`, `role_id`, `user_id`, `create_time`, `last_modified_time`) VALUES (4, 2, 2, NULL, NULL);

-- 2018.12.10

ALTER TABLE `product_service`
ADD COLUMN `videoImg` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `last_modified_time`;


ALTER TABLE `one_key_article`
MODIFY COLUMN `image` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章图片' AFTER `article_user`,
ADD COLUMN `cover` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `last_modified_time`;

ALTER TABLE `homepage`
MODIFY COLUMN `platform_dynamics_image` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '第三块图片' AFTER `about_platform_icon`,
ADD COLUMN `platform_dynamics_img` longtext NULL AFTER `platform_dynamics_image`;

ALTER TABLE `homepage`
ADD COLUMN `seo_title` varchar(255) NULL COMMENT 'seo标题' ,
ADD COLUMN `seo_keywords` longtext NULL COMMENT 'seo keywords' ,
ADD COLUMN `seo_description` longtext NULL COMMENT 'seo description' ;

ALTER TABLE `platform_dynamics`
ADD COLUMN `seo_title` varchar(255) NULL COMMENT 'seo标题' ,
ADD COLUMN `seo_keywords` longtext NULL COMMENT 'seo keywords' ,
ADD COLUMN `seo_description` longtext NULL COMMENT 'seo description' ;


ALTER TABLE `introduce_page`
ADD COLUMN `seo_title` varchar(255) NULL COMMENT 'seo标题' ,
ADD COLUMN `seo_keywords` longtext NULL COMMENT 'seo keywords' ,
ADD COLUMN `seo_description` longtext NULL COMMENT 'seo description' ;



ALTER TABLE `product_service`
ADD COLUMN `seo_title` varchar(255) NULL COMMENT 'seo标题' ,
ADD COLUMN `seo_keywords` longtext NULL COMMENT 'seo keywords' ,
ADD COLUMN `seo_description` longtext NULL COMMENT 'seo description' ;


ALTER TABLE `one_key_college`
ADD COLUMN `seo_title` varchar(255) NULL COMMENT 'seo标题' ,
ADD COLUMN `seo_keywords` longtext NULL COMMENT 'seo keywords',
ADD COLUMN `seo_description` longtext NULL COMMENT 'seo description';

-- 2018.12.11

ALTER TABLE `platform_dynamics_article`
ADD COLUMN `cover` longtext NULL COMMENT '封面图片';


-- 2018.12.14

ALTER TABLE `footer`
ADD COLUMN `sort` int(4) NULL COMMENT '排序';

DROP TABLE IF EXISTS `footer_details`;
CREATE TABLE `footer_details`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `footer_id` bigint(20) NULL DEFAULT NULL COMMENT '关联列id',
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转名称',
  `url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '跳转地址',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `creator_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- 2018.12.18

ALTER TABLE `one_key_article`
ADD COLUMN `sort` int(10) NULL COMMENT '排序';


-- 2018.12.21
ALTER TABLE `platform_dynamics_article`
ADD COLUMN `sort` int(10) NULL COMMENT '排序';


CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `main_image` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

CREATE TABLE `product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `pic_id` bigint(20) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

CREATE TABLE `product_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `org_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;