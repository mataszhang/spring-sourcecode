-- 用于spring jdbc测试
CREATE TABLE USER(
   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
   NAME VARCHAR(255) DEFAULT  NULL ,
   age INT DEFAULT  NULL ,
   sex VARCHAR(255) DEFAULT  NULL
) ENGINE = INNODB DEFAULT  CHARSET=utf8;