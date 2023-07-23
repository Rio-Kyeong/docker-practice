CREATE TABLE `mysql_db`.`orders` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `order_item` VARCHAR(45) NULL,
    `price` INT NULL,
    `order_date` DATE NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='주문';
