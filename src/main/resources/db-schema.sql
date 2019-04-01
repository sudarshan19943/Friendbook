CREATE TABLE `friends` (
  `userid` bigint(20) NOT NULL,
  `friendid` bigint(20) NOT NULL,
  UNIQUE KEY `unique_index` (`userid`,`friendid`),
  KEY `friends_users_friendid_fk` (`friendid`),
  CONSTRAINT `friends_users_friendid_fk` FOREIGN KEY (`friendid`) REFERENCES `users` (`id`),
  CONSTRAINT `friends_users_userid_fk` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `friends` 
CHANGE COLUMN `friend_token` `friend_token` TINYINT(4) NULL DEFAULT 0 ;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `enabled` char(1) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `friends` (
  `userid` bigint(20) NOT NULL,
  `friendid` bigint(20) NOT NULL,
  UNIQUE KEY `unique_index` (`userid`,`friendid`),
  KEY `friends_users_friendid_fk` (`friendid`),
  CONSTRAINT `friends_users_friendid_fk` FOREIGN KEY (`friendid`) REFERENCES `users` (`id`),
  CONSTRAINT `friends_users_userid_fk` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post` varchar(255) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=latin1;


CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `comment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_id_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `post_id_fk_idx` (`post_id_fk`),
  CONSTRAINT `post_id_fk` FOREIGN KEY (`post_id_fk`) REFERENCES `post` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `logs` (
  `user_id` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `logger` varchar(500) NOT NULL,
  `level` varchar(10) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

####User #####

CREATE PROCEDURE `getUserById` (IN id BIGINT(20))
BEGIN
SELECT * FROM users WHERE users.id = id;
END

CREATE PROCEDURE `getUserByEmailPassword`(IN email VARCHAR(45), password VARCHAR(45))
BEGIN
SELECT * FROM users WHERE email = email and password = password;
END

CREATE PROCEDURE `getUserByEmail` (IN email VARCHAR(255))
BEGIN
SELECT * FROM users WHERE email = email;
END

PROCEDURE `addUser`(IN email VARCHAR(255), password VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255))
BEGIN
INSERT INTO users (email, password, first_name, last_name) values  (email = email , password = password , first_name = first_name, last_name = last_name);
END

CREATE PROCEDURE `updateUser`(IN confirmation_token VARCHAR(255), email VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255), password VARCHAR(255), province VARCHAR(255), country VARCHAR(255), enabled CHAR(1))
BEGIN
UPDATE users SET confirmation_token=confirmation_token , email=email, enabled=enabled, first_name=first_name, last_name=last_name, password =password, province=province ,country=country WHERE  id=id;
END

CREATE PROCEDURE `findUserByResetToken` (IN confirmation_token VARCHAR(255))
BEGIN
SELECT * FROM users WHERE confirmation_token = confirmation_token;
END

CREATE PROCEDURE `getUserList`()
BEGIN
select * from
users
where  first_name LIKE '%%'AND last_name LIKE '%%' AND city LIKE '%Halifax%' AND country LIKE '%%';
END

###POST SECTION######

CREATE PROCEDURE `addNewPost` (IN sender_id INT(11), recipient_id INT(11), post VARCHAR(255), timestamp TIMESTAMP)
BEGIN
INSERT INTO post (sender_id, recipient_id, post, timestamp) VALUES (sender_id = sender_id, recipient_id = recipient_id, post = post, timestamp = timestamp);
END

CREATE PROCEDURE `removePost`(IN post_id INT(11))
BEGIN
DELETE from post WHERE post_id = post_id;
END

CREATE PROCEDURE `getMessage` (IN sender_id INT(11))
BEGIN
SELECT * from post where sender_id = sender_id;
END

###COMMENT SECTION #####

CREATE PROCEDURE `addNewComment` (sender_id INT(11), receiver_id INT(11), comment VARCHAR(255), timestamp TIMESTAMP, comment_id INT(11))
BEGIN
INSERT INTO comment (sender_id, receiver_id, comment, timestamp, comment_id) VALUES (sender_id = sender_id, receiver_id =receiver_id, comment =comment, timestamp =timestamp, comment_id=comment_id);
END

CREATE PROCEDURE `getComment` (IN post_id INT(11))
BEGIN
SELECT * from comment where post_id = comment_id;
END

#####FRIENDS####
CREATE PROCEDURE `addFriend`(IN friendid BIGINT(20))
BEGIN
INSERT INTO friends (friendid) VALUES (friends.friendid = friendid);
END

CREATE PROCEDURE `removeFriend`(IN id BIGINT(20))
BEGIN
DELETE FROM friends WHERE  friends.friendid = id;
END

##Change parameters to pass different fields
CREATE PROCEDURE `findFriends`(IN id INT(11))
BEGIN
SELECT * FROM users 
INNER JOIN  friends ON friends.friendid = users.id
where id = friends.userid and friends.friend_token = 1;
END

CREATE PROCEDURE `confirmFriend` (id INT(20))
BEGIN
UPDATE `friends` SET `friend_token` = '1' WHERE (`friendid` = id);
END



#session Management
CREATE TABLE CSCI5308_1_DEVINT.SPRING_SESSION (
PRIMARY_ID CHAR(36) NOT NULL,
SESSION_ID CHAR(36) NOT NULL,
CREATION_TIME BIGINT NOT NULL,
LAST_ACCESS_TIME BIGINT NOT NULL,
MAX_INACTIVE_INTERVAL INT NOT NULL,
EXPIRY_TIME BIGINT NOT NULL,
PRINCIPAL_NAME VARCHAR(100),
CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);
CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON CSCI5308_1_DEVINT.SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON CSCI5308_1_DEVINT.SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON CSCI5308_1_DEVINT.SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE CSCI5308_1_DEVINT.SPRING_SESSION_ATTRIBUTES (
SESSION_PRIMARY_ID CHAR(36) NOT NULL,
ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
ATTRIBUTE_BYTES BLOB NOT NULL,
CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES CSCI5308_1_DEVINT.SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateFriendToken`(IN id INT(11))
BEGIN
UPDATE friends  SET friend_token = '1'  WHERE (friends.friendid = id);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateConfirmToken`(IN friendid INT(11))
BEGIN
UPDATE `friends` SET `confirm_token` = '1'  WHERE (friends.friendid = friendid);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `confirmFriend`(IN friendid BIGINT(20))
BEGIN
INSERT INTO friends (friendid) VALUES (friends.friendid = friendid);
UPDATE friends SET confirm_token = 1 WHERE (friends.friendid = friendid) AND friend_token =1;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `findFriends`(IN id INT(11))
BEGIN
SELECT * FROM users 
inner join friends on friends.userid = users.id
where id = friends.userid and friends.friend_token = 1; 
END
