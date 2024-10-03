-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema posto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema posto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `posto`  ;
USE `posto` ;

-- -----------------------------------------------------
-- Table `posto`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `Nome_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NULL,
  `category_id` INT NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Produto_Categoria_idx` (`category_id` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`name` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Categoria`
    FOREIGN KEY (`category_id`)
    REFERENCES `posto`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Stock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` FLOAT NOT NULL,
  `validity` DATE NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Estoque_Produto1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_Estoque_Produto1`
    FOREIGN KEY (`product_id`)
    REFERENCES `posto`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Payment_Method`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Payment_Method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `method` VARCHAR(45) NOT NULL,
  `discount` VARCHAR(45) NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `metodo_UNIQUE` (`method` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Loyalty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Loyalty` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL,
  `points` INT NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `numero_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Costumer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Costumer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `loyalty_id` INT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`cpf` ASC) VISIBLE,
  INDEX `fk_Cliente_Fidelidade1_idx` (`loyalty_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Fidelidade1`
    FOREIGN KEY (`loyalty_id`)
    REFERENCES `posto`.`Loyalty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `permission` VARCHAR(45) CHARACTER SET 'armscii8' NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `Usuario_UNIQUE` (`user` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Deal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Deal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `validity` DATETIME NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idPromocao_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Sale` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `amount` FLOAT NOT NULL,
  `invoice_number` VARCHAR(45) NULL,
  `payment_method_id` INT NOT NULL,
  `costumer_id` INT NULL,
  `user_id` INT NOT NULL,
  `deal_id` INT NULL,
  `refound` TINYINT NULL,
  `loyalty_points` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Vendas_MetodoPagamento1_idx` (`payment_method_id` ASC) VISIBLE,
  INDEX `fk_Vendas_Cliente1_idx` (`costumer_id` ASC) VISIBLE,
  INDEX `fk_Vendas_Usuarios1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Vendas_Promocao1_idx` (`deal_id` ASC) VISIBLE,
  CONSTRAINT `fk_Vendas_MetodoPagamento1`
    FOREIGN KEY (`payment_method_id`)
    REFERENCES `posto`.`Payment_Method` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Cliente1`
    FOREIGN KEY (`costumer_id`)
    REFERENCES `posto`.`Costumer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Usuarios1`
    FOREIGN KEY (`user_id`)
    REFERENCES `posto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Promocao1`
    FOREIGN KEY (`deal_id`)
    REFERENCES `posto`.`Deal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Sale_Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Sale_Product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` FLOAT NOT NULL,
  `sale_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_VendaProduto_Vendas1_idx` (`sale_id` ASC) VISIBLE,
  INDEX `fk_VendaProduto_Produto1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_VendaProduto_Vendas1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `posto`.`Sale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VendaProduto_Produto1`
    FOREIGN KEY (`product_id`)
    REFERENCES `posto`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Stock_Control`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Stock_Control` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `quantity` FLOAT NOT NULL,
  `type` INT NOT NULL,
  `sale_product_id` INT NULL,
  `user_id` INT NOT NULL,
  `stock_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_ControleEstoque_VendaProduto1_idx` (`sale_product_id` ASC) VISIBLE,
  INDEX `fk_ControleEstoque_Usuarios1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_ControleEstoque_Estoque1_idx` (`stock_id` ASC) VISIBLE,
  CONSTRAINT `fk_ControleEstoque_VendaProduto1`
    FOREIGN KEY (`sale_product_id`)
    REFERENCES `posto`.`Sale_Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ControleEstoque_Usuarios1`
    FOREIGN KEY (`user_id`)
    REFERENCES `posto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ControleEstoque_Estoque1`
    FOREIGN KEY (`stock_id`)
    REFERENCES `posto`.`Stock` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
