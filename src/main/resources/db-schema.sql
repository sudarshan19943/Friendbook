CREATE TABLE users
(
  id BIGINT PRIMARY KEY NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255),
  birth_date DATE,
  reg_date TIMESTAMP DEFAULT now() NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  city VARCHAR(255),
  country VARCHAR(255),
  confirmation_token VARCHAR(255),
  enabled CHAR
);

CREATE UNIQUE INDEX users_email_uindex ON users (email);

CREATE TABLE friends (
  userid bigint NOT NULL,
  friendid bigint NOT NULL
);


ALTER TABLE  friends ADD CONSTRAINT friends_users_userid_fk FOREIGN KEY (userid) REFERENCES users(id);

ALTER TABLE  friends ADD CONSTRAINT friends_users_friendid_fk FOREIGN KEY (friendid) REFERENCES users(id);

ALTER TABLE `friends` ADD UNIQUE `unique_index`(`userid`, `friendid`);

CREATE TABLE messages (
  id bigint NOT NULL,
  date timestamp DEFAULT now(),
  sender bigint NOT NULL,
  recipient bigint NOT NULL,
  body character varying(255) NOT NULL
);


ALTER TABLE  messages ADD CONSTRAINT messages_id_pk PRIMARY KEY (id);

ALTER TABLE  messages ADD CONSTRAINT messages_recipient_id_fk FOREIGN KEY (recipient) REFERENCES users(id);

ALTER TABLE  messages ADD CONSTRAINT messages_sender_id_fk FOREIGN KEY (sender) REFERENCES users(id);

CREATE INDEX fki_messages_sender_id_fk ON messages (sender);

CREATE UNIQUE INDEX messages_id_uindex ON messages (id);
