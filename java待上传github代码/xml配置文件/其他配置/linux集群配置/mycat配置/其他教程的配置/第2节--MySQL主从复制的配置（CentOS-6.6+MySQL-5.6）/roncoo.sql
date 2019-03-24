create database if not exists ron default charset utf8 collate utf8_general_ci;

use ron;

DROP TABLE IF EXISTS `edu_user`;
CREATE TABLE `edu_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `pwd` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

INSERT INTO `edu_user` VALUES (1,'吴水成','123456'),(2,'清风','123456'),(3,'龙果','roncoo.com');
