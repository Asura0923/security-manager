CREATE TABLE `security_resource`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NULL DEFAULT NULL,
  `uri` varchar(255) NULL DEFAULT NULL,
  `pid` bigint(20) NULL DEFAULT NULL,
  `seq` int(11) NULL DEFAULT NULL,
  `intercept` int(1) NULL DEFAULT NULL,
  `menu` int(1) NULL DEFAULT NULL,
  `icon` varchar(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `security_resource` VALUES (1, '系统管理', NULL, NULL, 1, 0, 1, '');
INSERT INTO `security_resource` VALUES (2, '系统监控', NULL, NULL, 2, 0, 1, 'fa-dashboard');
INSERT INTO `security_resource` VALUES (11, '资源管理', '/security/resource/list', 1, 0, 1, 1, 'fa-braille');
INSERT INTO `security_resource` VALUES (12, '用户管理', '/security/user/list', 1, 1, 1, 1, 'fa-address-book');
INSERT INTO `security_resource` VALUES (13, '角色管理', '/security/role/list', 1, 2, 1, 1, 'fa-user-secret');
INSERT INTO `security_resource` VALUES (14, '组织管理', '/security/group/list', 1, 3, 1, 1, 'fa-group');
INSERT INTO `security_resource` VALUES (21, '系统状态', '/security/dashboard', 2, 0, 1, 1, NULL);
INSERT INTO `security_resource` VALUES (111, '添加资源', '/security/resource/add', 11, 0, 1, 1, 'fa-plus');
INSERT INTO `security_resource` VALUES (112, '修改资源', '/security/resource/update', 11, 1, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (113, '删除资源', '/security/resource/delete', 11, 2, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (121, '添加用户', '/security/user/add', 12, 0, 1, 1, '');
INSERT INTO `security_resource` VALUES (122, '修改用户', '/security/user/update', 12, 1, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (123, '删除用户', '/security/user/delete', 12, 2, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (124, '关联资源', '/security/user/resource', 12, 3, 1, 0, '');
INSERT INTO `security_resource` VALUES (125, '关联角色', '/security/user/role', 12, 4, 1, 0, '');
INSERT INTO `security_resource` VALUES (126, '关联组织', '/security/user/group', 12, 5, 1, 0, '');
INSERT INTO `security_resource` VALUES (131, '添加角色', '/security/role/add', 13, 0, 1, 1, '');
INSERT INTO `security_resource` VALUES (132, '修改角色', '/security/role/update', 13, 1, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (133, '删除角色', '/security/role/delete', 13, 2, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (134, '关联资源', '/security/role/resource', 13, 3, 1, 0, '');
INSERT INTO `security_resource` VALUES (141, '添加组织', '/security/group/add', 14, 0, 1, 1, '');
INSERT INTO `security_resource` VALUES (142, '修改组织', '/security/group/update', 14, 1, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (143, '删除组织', '/security/group/delete', 14, 2, 1, 0, NULL);
INSERT INTO `security_resource` VALUES (144, '关联资源', '/security/group/resource', 14, 3, 1, 0, '');


CREATE TABLE `security_group`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NULL DEFAULT NULL,
  `pid` bigint(20) NULL DEFAULT NULL,
  `seq` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `security_group` VALUES (1162283683193950208, '组织1', NULL, 0);
INSERT INTO `security_group` VALUES (1162291893233651712, '测试组织1', 1162283683193950208, 0);
INSERT INTO `security_group` VALUES (1162291949789646848, '测试组织2', 1162291893233651712, 0);


CREATE TABLE `security_role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `security_role` VALUES (1162281105550872576, '拥有用户管理权限的角色');
INSERT INTO `security_role` VALUES (1162291105367199744, '测试角色');


CREATE TABLE `security_user`  (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NULL DEFAULT NULL,
  `name` varchar(255) NULL DEFAULT NULL,
  `password` varchar(255) NULL DEFAULT NULL,
  `last_login` varchar(255) NULL DEFAULT NULL,
  `enabled` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ;


INSERT INTO `security_user` VALUES (0, 'sunyu@qq.com', '孙宇', '$2a$10$FFWj/LwZym2QV3fe5lSoOeEHOeQDwra.9c0EVrLoprO51roM8MEXG', '2019-08-16 17:00:18', 1);
INSERT INTO `security_user` VALUES (1161940627777261568, 'sz@qq.com', '施政', '$2a$10$C1UlPHRqb1G6g6fXWfZtvOT82PJNZQZDd2Kqo6UY2wxG9w0sMNFja', NULL, 0);
INSERT INTO `security_user` VALUES (1162288479632232448, 'cs@qq.com', '测试账户', '$2a$10$LwQW5Z6SEadev6lwXlQ/h.CEPTNj.79pEaMN7GhVGKvkiR2tknmMK', NULL, 1);


CREATE TABLE `security_group_resource`  (
  `group_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_id`, `resource_id`)
) ;


INSERT INTO `security_group_resource` VALUES (1162283683193950208, 1);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 2);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 11);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 12);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 13);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 14);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 21);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 111);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 112);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 113);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 121);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 122);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 123);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 124);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 125);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 126);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 131);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 132);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 133);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 134);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 141);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 142);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 143);
INSERT INTO `security_group_resource` VALUES (1162283683193950208, 144);
INSERT INTO `security_group_resource` VALUES (1162291949789646848, 2);
INSERT INTO `security_group_resource` VALUES (1162291949789646848, 21);


CREATE TABLE `security_role_resource`  (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `resource_id`)
) ;

INSERT INTO `security_role_resource` VALUES (1162281105550872576, 1);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 11);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 12);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 13);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 14);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 121);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 122);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 123);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 124);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 125);
INSERT INTO `security_role_resource` VALUES (1162281105550872576, 126);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 1);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 2);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 11);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 12);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 13);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 14);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 21);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 111);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 112);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 113);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 121);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 122);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 123);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 124);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 125);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 126);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 131);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 132);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 133);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 134);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 141);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 142);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 143);
INSERT INTO `security_role_resource` VALUES (1162291105367199744, 144);


CREATE TABLE `security_user_group`  (
  `user_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`)
) ;


INSERT INTO `security_user_group` VALUES (1162288479632232448, 1162283683193950208);
INSERT INTO `security_user_group` VALUES (1162288479632232448, 1162291949789646848);


CREATE TABLE `security_user_resource`  (
  `user_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `resource_id`)
) ;

INSERT INTO `security_user_resource` VALUES (0, 1);
INSERT INTO `security_user_resource` VALUES (0, 2);
INSERT INTO `security_user_resource` VALUES (0, 11);
INSERT INTO `security_user_resource` VALUES (0, 12);
INSERT INTO `security_user_resource` VALUES (0, 13);
INSERT INTO `security_user_resource` VALUES (0, 14);
INSERT INTO `security_user_resource` VALUES (0, 21);
INSERT INTO `security_user_resource` VALUES (0, 111);
INSERT INTO `security_user_resource` VALUES (0, 112);
INSERT INTO `security_user_resource` VALUES (0, 113);
INSERT INTO `security_user_resource` VALUES (0, 121);
INSERT INTO `security_user_resource` VALUES (0, 122);
INSERT INTO `security_user_resource` VALUES (0, 123);
INSERT INTO `security_user_resource` VALUES (0, 124);
INSERT INTO `security_user_resource` VALUES (0, 125);
INSERT INTO `security_user_resource` VALUES (0, 126);
INSERT INTO `security_user_resource` VALUES (0, 131);
INSERT INTO `security_user_resource` VALUES (0, 132);
INSERT INTO `security_user_resource` VALUES (0, 133);
INSERT INTO `security_user_resource` VALUES (0, 134);
INSERT INTO `security_user_resource` VALUES (0, 141);
INSERT INTO `security_user_resource` VALUES (0, 142);
INSERT INTO `security_user_resource` VALUES (0, 143);
INSERT INTO `security_user_resource` VALUES (0, 144);
INSERT INTO `security_user_resource` VALUES (1161940627777261568, 1);
INSERT INTO `security_user_resource` VALUES (1161940627777261568, 2);
INSERT INTO `security_user_resource` VALUES (1161940627777261568, 12);
INSERT INTO `security_user_resource` VALUES (1161940627777261568, 21);
INSERT INTO `security_user_resource` VALUES (1161940627777261568, 121);
INSERT INTO `security_user_resource` VALUES (1161940627777261568, 122);
INSERT INTO `security_user_resource` VALUES (1161940627777261568, 123);
INSERT INTO `security_user_resource` VALUES (1162288479632232448, 2);
INSERT INTO `security_user_resource` VALUES (1162288479632232448, 21);


CREATE TABLE `security_user_role`  (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`)
) ;


INSERT INTO `security_user_role` VALUES (1162288479632232448, 1162291105367199744);