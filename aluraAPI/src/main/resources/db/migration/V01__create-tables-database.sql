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
CREATE SCHEMA IF NOT EXISTS `posto` DEFAULT CHARACTER SET utf8 ;
USE `posto` ;

-- -----------------------------------------------------
-- Table `posto`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Categoria` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Ativo` TINYINT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Produto` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Preco` FLOAT NULL,
  `Categoria_Id` INT NOT NULL,
  `Ativo` TINYINT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  INDEX `fk_Produto_Categoria_idx` (`Categoria_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Categoria`
    FOREIGN KEY (`Categoria_Id`)
    REFERENCES `posto`.`Categoria` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Estoque` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Quantidade` INT NOT NULL,
  `Validade` DATE NOT NULL,
  `Produto_Id` INT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  INDEX `fk_Estoque_Produto1_idx` (`Produto_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Estoque_Produto1`
    FOREIGN KEY (`Produto_Id`)
    REFERENCES `posto`.`Produto` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`MetodoPagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`MetodoPagamento` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Metodo` VARCHAR(45) NOT NULL,
  `Desconto` VARCHAR(45) NULL,
  `Ativo` TINYINT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Fidelidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Fidelidade` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Numero` VARCHAR(45) NOT NULL,
  `Pontos` INT NOT NULL,
  `Ativo` TINYINT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Cliente` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `CPF` VARCHAR(45) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Fidelidade_Id` INT NULL,
  `Ativo` TINYINT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC) VISIBLE,
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC) VISIBLE,
  INDEX `fk_Cliente_Fidelidade1_idx` (`Fidelidade_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Fidelidade1`
    FOREIGN KEY (`Fidelidade_Id`)
    REFERENCES `posto`.`Fidelidade` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Usuario` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Usuario` VARCHAR(45) NOT NULL,
  `Senha` VARCHAR(45) NOT NULL,
  `Permissao` VARCHAR(45) NOT NULL,
  `Ativo` TINYINT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  UNIQUE INDEX `Usuario_UNIQUE` (`Usuario` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Promocao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Promocao` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Validade` DATETIME NOT NULL,
  `Ativo` TINYINT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `idPromocao_UNIQUE` (`Id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`Venda` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Data` DATETIME NOT NULL,
  `Valor` FLOAT NOT NULL,
  `CupomFiscal` VARCHAR(45) NULL,
  `MetodoPagamento_Id` INT NOT NULL,
  `Cliente_Id` INT NULL,
  `Usuarios_Id` INT NOT NULL,
  `Promocao_Id` INT NULL,
  `Extorno` TINYINT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  INDEX `fk_Vendas_MetodoPagamento1_idx` (`MetodoPagamento_Id` ASC) VISIBLE,
  INDEX `fk_Vendas_Cliente1_idx` (`Cliente_Id` ASC) VISIBLE,
  INDEX `fk_Vendas_Usuarios1_idx` (`Usuarios_Id` ASC) VISIBLE,
  INDEX `fk_Vendas_Promocao1_idx` (`Promocao_Id` ASC) VISIBLE,
  CONSTRAINT `fk_Vendas_MetodoPagamento1`
    FOREIGN KEY (`MetodoPagamento_Id`)
    REFERENCES `posto`.`MetodoPagamento` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Cliente1`
    FOREIGN KEY (`Cliente_Id`)
    REFERENCES `posto`.`Cliente` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Usuarios1`
    FOREIGN KEY (`Usuarios_Id`)
    REFERENCES `posto`.`Usuario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vendas_Promocao1`
    FOREIGN KEY (`Promocao_Id`)
    REFERENCES `posto`.`Promocao` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`VendaProduto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`VendaProduto` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Quantidade` FLOAT NOT NULL,
  `Vendas_Id` INT NOT NULL,
  `Produto_Id` INT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  INDEX `fk_VendaProduto_Vendas1_idx` (`Vendas_Id` ASC) VISIBLE,
  INDEX `fk_VendaProduto_Produto1_idx` (`Produto_Id` ASC) VISIBLE,
  CONSTRAINT `fk_VendaProduto_Vendas1`
    FOREIGN KEY (`Vendas_Id`)
    REFERENCES `posto`.`Venda` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VendaProduto_Produto1`
    FOREIGN KEY (`Produto_Id`)
    REFERENCES `posto`.`Produto` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `posto`.`ControleEstoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `posto`.`ControleEstoque` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Data` DATETIME NOT NULL,
  `Quantidade` FLOAT NOT NULL,
  `Movimentacao` VARCHAR(45) NOT NULL,
  `VendaProduto_Id` INT NULL,
  `Usuarios_Id` INT NOT NULL,
  `Estoque_Id` INT NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
  INDEX `fk_ControleEstoque_VendaProduto1_idx` (`VendaProduto_Id` ASC) VISIBLE,
  INDEX `fk_ControleEstoque_Usuarios1_idx` (`Usuarios_Id` ASC) VISIBLE,
  INDEX `fk_ControleEstoque_Estoque1_idx` (`Estoque_Id` ASC) VISIBLE,
  CONSTRAINT `fk_ControleEstoque_VendaProduto1`
    FOREIGN KEY (`VendaProduto_Id`)
    REFERENCES `posto`.`VendaProduto` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ControleEstoque_Usuarios1`
    FOREIGN KEY (`Usuarios_Id`)
    REFERENCES `posto`.`Usuario` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ControleEstoque_Estoque1`
    FOREIGN KEY (`Estoque_Id`)
    REFERENCES `posto`.`Estoque` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
