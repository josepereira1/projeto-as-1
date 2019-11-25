-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema trading
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema trading
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `trading` DEFAULT CHARACTER SET utf8 ;
USE `trading` ;

-- -----------------------------------------------------
-- Table `trading`.`CFD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`CFD` (
  `id` VARCHAR(45) NOT NULL,
  `idAtivo` VARCHAR(45) NOT NULL,
  `tipo` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `stopLess` FLOAT NULL,
  `takeProfit` FLOAT NULL,
  `dataAbertura` TIMESTAMP NOT NULL,
  `dataEncerramento` TIMESTAMP NULL,
  `numeroDeAtivos` INT NOT NULL,
  `valorInicial` FLOAT NOT NULL,
  `valorInvestido` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trading`.`Acao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`Acao` (
  `id` VARCHAR(45) NOT NULL,
  `designacao` VARCHAR(45) NOT NULL,
  `valorVenda` FLOAT NOT NULL,
  `valorCompra` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trading`.`Commoditie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`Commoditie` (
  `id` VARCHAR(45) NOT NULL,
  `designacao` VARCHAR(45) NOT NULL,
  `valorVenda` FLOAT NOT NULL,
  `valorCompra` FLOAT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trading`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`Administrador` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trading`.`Trader`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `trading`.`Trader` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `plafond` FLOAT NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
