CREATE TABLE `User` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  'FIRST_NAME' varchar2 NOT NULL,
  'LAST_NAME' varchar2 NOT NULL,
  'EMAIL' varchar2 NOT NULL,
  'USER_NAME' varchar2 NOT NULL,
  'PASSWORD' varchar2 NOT NULL,
  'CREATE_DATE' DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `Contact_Detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user__id` int(11) unsigned NOT NULL,
  'PHONE_NUMBER' varchar2 NOT NULL,
  'ACTIVE'  TINYINT DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;