DROP SCHEMA IF EXISTS `studylog`;

CREATE SCHEMA IF NOT EXISTS `studylog` DEFAULT CHARACTER SET utf8 ;
USE `studylog` ;

DROP TABLE IF EXISTS `studylog`.`user` ;

CREATE TABLE `studylog`.`user` (
  `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `nick_name` VARCHAR(255) NOT NULL UNIQUE,
  `image_url` VARCHAR(255) NULL,
  `create_date_time` DATETIME NOT NULL,
  `update_date_time` DATETIME NULL
);

DROP TABLE IF EXISTS `studylog`.`blog` ;

CREATE TABLE `studylog`.`blog` (
  `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL UNIQUE,
  `introduce` VARCHAR(255) NULL,
  `image_url` VARCHAR(255) NULL,
  `create_date_time` DATETIME NOT NULL,
  `update_date_time` DATETIME NULL
);

DROP TABLE IF EXISTS `studylog`.`blog_member_role` ;

CREATE TABLE `studylog`.`blog_member_role` (
  `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS `studylog`.`blog_member` ;

CREATE TABLE `studylog`.`blog_member` (
  `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `user_id` INT UNSIGNED NOT NULL,
  `blog_id` INT UNSIGNED NOT NULL,
  `role_id` INT UNSIGNED NOT NULL,
  `create_date_time` DATETIME NOT NULL,
  `update_date_time` DATETIME NULL,
  CONSTRAINT `fk_blog_member_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `studylog`.`user` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_blog_member_blog`
    FOREIGN KEY (`blog_id`)
    REFERENCES `studylog`.`blog` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_blog_member_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `studylog`.`blog_member_role` (`id`)
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS `studylog`.`blog_member_invitation_key`;

CREATE TABLE `studylog`.`blog_member_invitation_key` (
	`id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `value` VARCHAR(100) NOT NULL,
    `create_date_time` DATETIME NOT NULL,
    `blog_member_id` INT UNSIGNED NOT NULL,
    CONSTRAINT `fk_blog_member`
		FOREIGN KEY (`blog_member_id`)
        REFERENCES `studylog`.`blog_member` (`id`)
        ON DELETE CASCADE
);

DROP TABLE IF EXISTS `studylog`.`issue_category` ;

CREATE TABLE `studylog`.`issue_category` (
  `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS `studylog`.`issue`;

CREATE TABLE `studylog`.`issue` (
  `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL,
  `contents` MEDIUMTEXT NOT NULL,
  `close_status` TINYINT DEFAULT 0,
  `create_date` DATE NOT NULL,
  `issue_category_id` INT UNSIGNED NOT NULL,
  `blog_member_id` INT UNSIGNED,
  CONSTRAINT `fk_issue_issue_category`
    FOREIGN KEY (`issue_category_id`)
    REFERENCES `studylog`.`issue_category` (`id`)
    ON DELETE RESTRICT,
  CONSTRAINT `fk_issue_blog_member`
    FOREIGN KEY (`blog_member_id`)
    REFERENCES `studylog`.`blog_member` (`id`)
    ON DELETE SET NULL
);

DROP TABLE IF EXISTS `studylog`.`issue_comment`;

CREATE TABLE IF NOT EXISTS `studylog`.`issue_comment` (
  `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `contents` MEDIUMTEXT NOT NULL,
  `create_date` DATE NOT NULL,
  `issue_id` INT UNSIGNED NOT NULL,
  `blog_member_id` INT UNSIGNED,
  CONSTRAINT `fk_issue_comment_issue`
    FOREIGN KEY (`issue_id`)
    REFERENCES `studylog`.`issue` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_issue_comment_blog_member1`
    FOREIGN KEY (`blog_member_id`)
    REFERENCES `studylog`.`blog_member` (`id`)
    ON DELETE SET NULL
);