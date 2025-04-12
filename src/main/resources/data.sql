DELETE FROM `user`;
DELETE FROM `stock`;

INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('aaaaa', '사용자1', NOW(), NOW());
INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('bbbbb', '사용자2', NOW(), NOW());
INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('ccccc', '사용자3', NOW(), NOW());
INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('ddddd', '사용자4', NOW(), NOW());
INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('eeeee', '사용자5', NOW(), NOW());

-- 삼성전자
INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('005930', '삼성전자', 55200);

-- 카카오
INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('035720', '카카오', 39000);

-- 네이버
INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('035420', '네이버', 183000);

-- SK하이닉스
INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('000660', 'SK하이닉스', 180800);

-- LG에너지솔루션
INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('373220', 'LG에너지솔루션', 335500);

-- 현대차
INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('005380', '현대차', 177500);