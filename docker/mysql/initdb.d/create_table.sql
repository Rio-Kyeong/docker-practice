create table POST (
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '게시글 ID',
    name varchar(128) NOT NULL COMMENT '게시글 제목',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='게시글';
