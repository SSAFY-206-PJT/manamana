-- -----------------------------------------------------
-- Table `mana_db`.`webtoon_providers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mana_db`.`webtoon_providers` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `provider_url` VARCHAR(255) NOT NULL,
  `provider_image` VARCHAR(255) NOT NULL,
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


