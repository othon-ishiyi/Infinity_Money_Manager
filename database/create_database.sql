-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema database
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `database` DEFAULT CHARACTER SET utf8 ;
USE `database` ;

-- -----------------------------------------------------
-- Table `database`.`ganhos_fixos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`ganhos_fixos` (
  `id` INT NOT NULL,
  `periodicidade` VARCHAR(50) NOT NULL,
  `tipo` VARCHAR(50) NOT NULL,
  `valor` DECIMAL(19,2) NOT NULL,
  `fonte` VARCHAR(50) NOT NULL,
  `descricao` LONGTEXT NULL,
  `data` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database`.`ganhos_variaveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`ganhos_variaveis` (
  `id` INT NOT NULL,
  `tipo` VARCHAR(50) NOT NULL,
  `valor` DECIMAL(19,2) NOT NULL,
  `fonte` VARCHAR(50) NOT NULL,
  `descricao` LONGTEXT NULL,
  `data` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database`.`metas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`metas` (
  `id` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `valor_alvo` DECIMAL(19,2) NOT NULL,
  `valor_arrecadado` DECIMAL(19,2) NOT NULL,
  `prazo` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database`.`gastos_fixos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`gastos_fixos` (
  `id` INT NOT NULL,
  `periodicidade` VARCHAR(50) NOT NULL,
  `tipo` VARCHAR(50) NOT NULL,
  `valor` DECIMAL(19,2) NOT NULL,
  `categoria` VARCHAR(50) NOT NULL,
  `descricao` LONGTEXT NULL,
  `data` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `metas_id`
    FOREIGN KEY (`id`)
    REFERENCES `database`.`metas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database`.`gastos_variaveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database`.`gastos_variaveis` (
  `id` INT NOT NULL,
  `tipo` VARCHAR(50) NOT NULL,
  `valor` DECIMAL(19,2) NOT NULL,
  `categoria` VARCHAR(50) NOT NULL,
  `descricao` LONGTEXT NULL,
  `data` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `metas_id`
    FOREIGN KEY (`id`)
    REFERENCES `database`.`metas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
