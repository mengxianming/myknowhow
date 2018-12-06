CREATE TABLE renter( 
  id INT (11) NOT NULL PRIMARY KEY
  , name VARCHAR (32) NOT NULL
  , phone VARCHAR (32)
  , age int
); 

CREATE TABLE room( 
  id INT (11) NOT NULL PRIMARY KEY
  , name VARCHAR (32) NOT NULL
  , area decimal(5,2)
  , createtime timestamp
); 

CREATE TABLE `adp_user_wx` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `wechat` varchar(64) NOT NULL DEFAULT '' COMMENT '微信号',
  `name` varchar(64)  NOT NULL DEFAULT '' COMMENT '微信亲呢',
  `picture` varchar(256)  NOT NULL DEFAULT '' COMMENT '微信头像地址',
  `type` smallint(6) NOT NULL DEFAULT '1' COMMENT '微信类型(1:个人 2:公众号)',
  `advance` varchar(32)  NOT NULL DEFAULT '' COMMENT '擅长类型(1:服装 2:美妆 3:亲子 4:居家) ',
  `fans_num` int(11) NOT NULL DEFAULT '0' COMMENT '微信好友数',
  `create_by` int(11) NOT NULL DEFAULT '1' COMMENT '创建人',
  `create_on` timestamp NOT NULL DEFAULT '1972-01-01 00:00:00' COMMENT '创建时间',
  `update_by` int(11) NOT NULL DEFAULT '1' COMMENT '更新人',
  `update_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `invited_from` varchar(20)  NOT NULL DEFAULT 'N/A' COMMENT '用户来自的渠道标识',
  `invited_on` timestamp NOT NULL DEFAULT '1972-01-01 00:00:00' COMMENT '用户通过推广渠道注册时间',
  `wechat_account` varchar(64)  NOT NULL DEFAULT '' COMMENT '微信号(微信小程序)',
  `wechat_name` varchar(256)  NOT NULL DEFAULT '' COMMENT '微信昵称(微信小程序)',
  `wechat_picture` varchar(256)  NOT NULL DEFAULT '' COMMENT '微信头像地址(微信小程序)',
  PRIMARY KEY (`id`),
  KEY `idx_aux_user_id` (`user_id`)
) COMMENT='唯享客用户从表' ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
