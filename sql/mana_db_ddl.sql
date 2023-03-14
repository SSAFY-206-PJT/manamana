-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mana_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mana_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mana_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `mana_db` ;

-- -----------------------------------------------------
-- Table `mana_db`.`webtoon_providers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`webtoon_providers` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `provider_url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`webtoons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`webtoons` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `image_path` VARCHAR(255) NOT NULL,
  `plot` TEXT NOT NULL,
  `grade_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `webtoon_url` VARCHAR(255) NOT NULL,
  `webtoon_id` VARCHAR(255) NOT NULL,
  `start_date` DATE NOT NULL,
  `total_ep` INT NOT NULL,
  `is_deleted` TINYINT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  `color_hsl` VARCHAR(15) NOT NULL,
  `provider_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_webtoons_webtoon_providers1_idx` (`provider_id` ASC) VISIBLE,
  CONSTRAINT `fk_webtoons_webtoon_providers1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `mana_db`.`webtoon_providers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`genres` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`webtoons_and_genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`webtoons_and_genres` (
  `webtoon_id` BIGINT NOT NULL,
  `genre_id` INT NOT NULL,
  PRIMARY KEY (`webtoon_id`, `genre_id`),
  INDEX `fk_webtoons_and_genres_genres1_idx` (`genre_id` ASC) VISIBLE,
  CONSTRAINT `fk_webtoons_and_genres_webtoons1`
    FOREIGN KEY (`webtoon_id`)
    REFERENCES `mana_db`.`webtoons` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_webtoons_and_genres_genres1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `mana_db`.`genres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`login_providers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`login_providers` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `image_path` VARCHAR(255) NOT NULL,
  `gender` VARCHAR(255) NOT NULL,
  `age` INT NOT NULL,
  `is_deleted` TINYINT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  `provider_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_login_providers1_idx` (`provider_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_login_providers1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `mana_db`.`login_providers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`comments` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NOT NULL,
  `is_spoiler` TINYINT NOT NULL,
  `report` BIGINT NOT NULL,
  `is_deleted` TINYINT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  `webtoon_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_webtoons1_idx` (`webtoon_id` ASC) VISIBLE,
  INDEX `fk_comments_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_comments_webtoons1`
    FOREIGN KEY (`webtoon_id`)
    REFERENCES `mana_db`.`webtoons` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mana_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`webtoon_days`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`webtoon_days` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code_id` INT NOT NULL,
  `webtoon_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_webtoon_days_webtoons_idx` (`webtoon_id` ASC) VISIBLE,
  CONSTRAINT `fk_webtoon_days_webtoons`
    FOREIGN KEY (`webtoon_id`)
    REFERENCES `mana_db`.`webtoons` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`authors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `webtoon_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_authors_webtoons1_idx` (`webtoon_id` ASC) VISIBLE,
  CONSTRAINT `fk_authors_webtoons1`
    FOREIGN KEY (`webtoon_id`)
    REFERENCES `mana_db`.`webtoons` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`users_and_webtoons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`users_and_webtoons` (
  `score` INT NOT NULL,
  `is_liked` TINYINT NOT NULL,
  `is_deleted` TINYINT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  `user_id` BIGINT NOT NULL,
  `webtoon_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `webtoon_id`),
  INDEX `fk_users_and_webtoons_webtoons1_idx` (`webtoon_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_and_webtoons_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mana_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_and_webtoons_webtoons1`
    FOREIGN KEY (`webtoon_id`)
    REFERENCES `mana_db`.`webtoons` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`users_and_genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`users_and_genres` (
  `weight` INT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  `user_id` BIGINT NOT NULL,
  `genre_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `genre_id`),
  INDEX `fk_users_and_genres_users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_users_and_genres_genres1_idx` (`genre_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_and_genres_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mana_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_and_genres_genres1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `mana_db`.`genres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`webtoon_additions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`webtoon_additions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `view` BIGINT NOT NULL,
  `total_score` BIGINT NOT NULL,
  `score_count` BIGINT NOT NULL,
  `create_time` TIMESTAMP NOT NULL,
  `update_time` TIMESTAMP NOT NULL,
  `webtoon_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_webtoon_additions_webtoons1_idx` (`webtoon_id` ASC) VISIBLE,
  CONSTRAINT `fk_webtoon_additions_webtoons1`
    FOREIGN KEY (`webtoon_id`)
    REFERENCES `mana_db`.`webtoons` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`day_codes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`day_codes` (
  `id` INT NOT NULL,
  `day` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`serial_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`serial_status` (
  `id` INT NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`grades` (
  `id` INT NOT NULL,
  `grade` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`sorts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`sorts` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mana_db`.`reports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`reports` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `comment_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reports_comments1_idx` (`comment_id` ASC) VISIBLE,
  CONSTRAINT `fk_reports_comments1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `mana_db`.`comments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
