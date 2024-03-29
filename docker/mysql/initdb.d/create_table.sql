CREATE TABLE `mysql_db`.`orders`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `order_item` VARCHAR(45) NULL,
    `price`      INT         NULL,
    `order_date` DATETIME    NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='주문';

CREATE TABLE `mysql_db`.`post`
(
    `id`         BINARY(16)   NOT NULL COMMENT '게시글 ID',
    `user_id`    INT(11)      NULL COMMENT '고객 ID',
    `title`      VARCHAR(45)  NULL COMMENT '게시글 명',
    `contents`   VARCHAR(200) NULL COMMENT '게시글 내용',
    `created_at` DATETIME     NULL DEFAULT NOW() COMMENT '데이터 최초 생성 일시',
    `updated_at` DATETIME     NULL DEFAULT NOW() COMMENT '데이터 최종 수정 일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='게시글';
