CREATE TABLE `friends` (
  `userid` bigint(20) DEFAULT NULL,
  `friendid` bigint(20) DEFAULT NULL,
  UNIQUE KEY `unique_index` (`userid`,`friendid`),
  KEY `friends_users_friendid_fk` (`friendid`),
  CONSTRAINT `friends_users_friendid_fk` FOREIGN KEY (`friendid`) REFERENCES `users` (`id`),
  CONSTRAINT `friends_users_userid_fk` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `friend_confirm_token` tinyint(4) DEFAULT '0',
  `friend_token` tinyint(4) DEFAULT '0',
  `user_image` mediumblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;



CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post` varchar(255) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;



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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

CREATE TABLE `logs` (
  `user_id` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `logger` varchar(500) NOT NULL,
  `level` varchar(10) NOT NULL,
  `message` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#STORED PROCEDURES

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `addFriend`(IN friendid INT(11), id INT(11))
BEGIN
INSERT INTO friends (friendid, userid) VALUES (friendid,id);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `addNewComment`(IN sender INT(11),IN reciever INT(11), IN commentVal VARCHAR(255),IN post_id INT(11))
BEGIN
INSERT INTO comment (sender_id,receiver_id,comment,post_id_fk) VALUES (sender,reciever,commentVal,post_id);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `addNewPost`(IN senderID INT(11), IN post VARCHAR(255))
BEGIN
INSERT INTO post (sender_id, post) VALUES (senderID,post);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `addUser`(IN emailVal VARCHAR(255), IN passwordVal VARCHAR(255), IN firstName VARCHAR(255), IN lastName VARCHAR(255))
BEGIN
INSERT INTO users
(`first_name`,`last_name`,`email`,`password`)
VALUE(firstName,lastName,emailVal,passwordVal);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `clearFriendConfirmToken`(IN id INT(11))
BEGIN
UPDATE users  SET friend_confirm_token = false where id = id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `clearFriendToken`(IN id INT(11))
BEGIN
UPDATE users  SET friend_token = false where id = id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `findFriend`(IN idVal INT(11))
BEGIN
select * from friends where userid=idVal OR friendid=idVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `findFriends`(IN inpID INT(11))
BEGIN
SELECT * FROM friends
inner join users on users.id = friends.userid
where userid = inpID or friendid = inpID;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `findUserByResetToken`(IN confirmation_tokenVal VARCHAR(255))
BEGIN
SELECT * FROM users WHERE confirmation_token = confirmation_tokenVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getComment`(IN post_id INT(11))
BEGIN
SELECT * from comment
INNER JOIN  post ON comment.post_id_fk = post.post_id
where comment.post_id_fk  = post_id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getMessage`(IN recipientIDVal INT(11))
BEGIN
SELECT * from post where recipient_id = recipientIDVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getMessageByUserID`(IN id INT(11))
BEGIN
select * from post where sender_id = id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getPostCreator`(IN postID INT(11))
BEGIN
select * from post where post_id = postID;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getUserByEmail`(IN emailVal VARCHAR(255))
BEGIN
SELECT * FROM users WHERE email = emailVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getUserByEmailPassword`(IN emailVal VARCHAR(45), passwordVal VARCHAR(45))
BEGIN
SELECT * FROM users WHERE email = emailVal and password = passwordVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getUserById`(IN id BIGINT(20))
BEGIN
SELECT * FROM users WHERE users.id = id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `getUserList`(IN firstName VARCHAR(255), lastName VARCHAR(255), city VARCHAR(255), state VARCHAR(255), country VARCHAR(255))
BEGIN
select * from
users
where  first_name LIKE firstName AND last_name LIKE lastName AND city LIKE city AND state LIKE state AND country LIKE country;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `removeFriend`(IN id BIGINT(20))
BEGIN
DELETE FROM friends WHERE  friends.friendid = id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `removeFriendUser`(IN id BIGINT(20))
BEGIN
DELETE FROM friends WHERE  friends.userid = id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `removePost`(IN post_id INT(11))
BEGIN
DELETE from post WHERE post_id = post_id;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `resetUserPassword`(IN passwordVal VARCHAR(255),IN confirmation_token VARCHAR(255), IN emailVal VARCHAR(255),IN enabledVal CHAR(1))
BEGIN
UPDATE users SET password=passwordVal , confirmation_token=confirmation_token , enabled=enabledVal WHERE  email=emailVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateConfirmToken`(IN id INT(11))
BEGIN
UPDATE users  SET friend_confirm_token = true  WHERE (users.id = id);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateFriendToken`(IN id INT(11))
BEGIN
UPDATE users  SET friend_token = true  WHERE (users.id = id);
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateUser`(IN confirmation_token VARCHAR(255), IN emailVal VARCHAR(255),IN enabledVal CHAR(1))
BEGIN
UPDATE users SET confirmation_token=confirmation_token , enabled=enabledVal WHERE  email=emailVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateUserImage`(IN userImage MEDIUMBLOB, IN emailVal VARCHAR(255))
BEGIN
update users set user_image=userImage where email=emailVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateUserImageDefault`(IN userImage MEDIUMBLOB, IN emailVal VARCHAR(255))
BEGIN
update users set user_image=userImage where email=emailVal;
END

CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `updateUserLocation`(IN countryVal VARCHAR(255), IN stateVal VARCHAR(255),IN cityVal VARCHAR(255),IN emailVal VARCHAR(255))
BEGIN
UPDATE users SET country=countryVal , province=stateVal,city=cityVal WHERE  email=emailVal;
END