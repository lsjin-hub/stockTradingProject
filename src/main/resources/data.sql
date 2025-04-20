DELETE FROM `user`;
DELETE FROM `stock`;

-- user
INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('aaaaa', '사용자1', NOW(), NOW());
INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('bbbbb', '사용자2', NOW(), NOW());
INSERT INTO `user` (user_id,user_name,create_date_time,update_date_time) VALUES ('ccccc', '사용자3', NOW(), NOW());

-- 주식종목
INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('005930', '삼성전자', 55200);

INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('035720', '카카오', 39000);

INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('035420', '네이버', 183000);

INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('000660', 'SK하이닉스', 180800);

INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('373220', 'LG에너지솔루션', 335500);

INSERT INTO stock (stock_id, stock_name, stock_price)
VALUES ('005380', '현대차', 177500);

-- 계좌
INSERT INTO account (account_number, user_id, account_bank, real_balance, active_balance)
VALUES ('1111111111111', 'aaaaa', '신한은행', 1000000, 1000000);

INSERT INTO account (account_number, user_id, account_bank, real_balance, active_balance)
VALUES ('2222222222222', 'bbbbb', '우리은행', 2000000, 2000000);

INSERT INTO account (account_number, user_id, account_bank, real_balance, active_balance)
VALUES ('3333333333333', 'ccccc', 'KB국민은행', 3000000, 3000000);

-- 보유주식
INSERT INTO amount_of_stock (user_id, stock_id, buy_stock_amount, buy_stock_price, active_amount)
VALUES ('aaaaa', '005930', 20, 70000, 20);

INSERT INTO amount_of_stock (user_id, stock_id, buy_stock_amount, buy_stock_price, active_amount)
VALUES ('bbbbb', '035420', 10, 163000, 10);

INSERT INTO amount_of_stock (user_id, stock_id, buy_stock_amount, buy_stock_price, active_amount)
VALUES ('ccccc', '373220', 5, 300000, 5);