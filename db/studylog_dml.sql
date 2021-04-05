USE `studylog`;

INSERT INTO `user`(`name`, `email`, `nick_name`, `img_url`) VALUES 
("김영곤", "dudrhs571@gmail.com", "zeroGone", "/img/user-default/1.png"),
("모세현", "ahtpgus@naver.com", "모세", "/img/user-default/3.png"),
("홍성수", "hss0281@naver.com", "홍구리", "/img/user-default/4.png"),
("김영곤", "dudrhs571@naver.com", "zeroGone7247", null),
("홍준성", "dundung@gmail.com", "DunDung", "/img/user-default/5.png"),
("양진민", "yangjinmin95@gmail.com", "JinminZZANG2", "/img/user-default/2.png"),
("양진민", "yjm7485@naver.com", "JinminZZANG", null),
("김민섭", "welcltm@gmail.com", "김민섭", "https://studylog.s3.ap-northeast-2.amazonaws.com/img/user/sulivan.jpg");

INSERT INTO `blog_member_role`(`name`)
VALUES ("ADMIN"), ("MEMBER"), ("INVITING");

INSERT INTO `blog`(`name`, `introduce`, `img_url`)
VALUES ("studylog", "web platform for team blog", null);

INSERT INTO `blog_member` (`user_id`, `blog_id`, `role_id`)
VALUES (1, 1, 1), (6,1,2);

INSERT INTO `issue_category` (`name`)
VALUES ("NOTICE"), ("QUESTION"), ("CHAT"), ("REQUEST");

INSERT INTO `issue` (`title`, `contents`, `create_date`, `issue_category_id`, `blog_member_id`)
VALUES 
("스터디 로그 공지사항", "언제부터 시작했는지 기억도 안나지만 꼭 이번 프로젝트 무사히 완성하고 배포까지 해서 포트폴리오로 자랑스럽게 내놓고 취업에 성공하자", CURRENT_DATE(), 1, 1),
("멘탈 관리 주의사항", "프로젝트하면서 자신의 실력에 너무 낙담하지말고 열심히 하되 게을러 지지는 말자 놀땐 놀더라도 놀고 할땐 하자. 그리고 열심히 하자", CURRENT_DATE(), 1, 1),
("ㅎㅇ", "ㅎㅇ", CURRENT_DATE(), 3, 1);

INSERT INTO `issue_comment` (`contents`, `create_date`, `issue_id`, `blog_member_id`)
VALUES ("hi", CURRENT_DATE(), 3, 1);
