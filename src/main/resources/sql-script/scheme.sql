CREATE TABLE `tbl_sms_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `phone_number` varchar(50) NOT NULL COMMENT '手机号',
  `msg_content` varchar(500) DEFAULT NULL COMMENT '短信内容',
  `status` int(1) NOT NULL COMMENT '0:待执行；1：已执行；2：失败；3：正在执行',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `plan_sent_time` datetime DEFAULT NULL COMMENT '计划发送时间（开始时间）',
  `actual_sent_time` datetime DEFAULT NULL COMMENT '实际发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;