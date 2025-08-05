INSERT INTO `items` (`id`, `name`, `img_path`, `price`, `discount_per`, `created`)
VALUES
    (1, 'Starry', '001.jpg',  10000000, 5, '2025-07-07 16:02:39'),
    (2, 'Seascape', '002.jpg',  20000000, 10, '2025-07-07 16:02:39'),
    (3, 'Arles', '003.jpg',  30000000, 15, '2025-07-07 16:02:39'),
    (4, 'Mountain', '004.jpg',  40000000, 20, '2025-07-07 16:02:39'),
    (5, 'Provence', '005.jpg',  50000000, 25, '2025-07-07 16:02:39'),
    (6, 'Houses', '006.jpg',  60000000, 30, '2025-07-07 16:02:39');

INSERT INTO `members` (`id`, `name`, `login_id`, `login_pw`, `created`)
VALUES
    (1, '홍길동', 'hong@naver.com', '$2a$10$ZyxDba1ngT10bWSBKnmi3uBgqQD8z3xkiXKSf/Qxg1/4AQzqehf/u', '2025-07-08 10:20:56'),
    (2, '아리동동', 'aridong@naver.com', '$2a$10$t.LhPMwETHW5I3UpN6Ee0OQ9IH0Dps3yD7sz63r4fApY3q0EdZDNe', '2025-07-08 14:36:31'),
    (6, '김하빈', 'habi@gmail.com', '$2a$10$MdynYTRk39bJbLN35f0UiuzAsVM2tZAPLpoVYofEKDdS9ECqy3Q9y', '2025-08-01 15:25:26');