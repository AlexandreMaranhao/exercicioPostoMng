CREATE TABLE IF NOT EXISTS `posto`.`Produto` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Preco` FLOAT NULL,
  `Categoria_Id` INT NOT NULL,
  `Validade` DATE NULL,

  PRIMARY KEY (`Id`)

);

CREATE TABLE IF NOT EXISTS `posto`.`Venda` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Data` DATE NOT NULL,
  `Valor` FLOAT NOT NULL,
  `CupomFiscal` VARCHAR(45) NULL,
  `MetodoPagamento_Id` INT NOT NULL,
  `Cliente_Id` INT NULL,
  `Usuarios_Id` INT NOT NULL,
  `Promocao_Id` INT NULL,
  `Extorno` TINYINT NULL,

  PRIMARY KEY (`Id`)
);
