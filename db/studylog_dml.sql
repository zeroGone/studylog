USE `studylog`;

INSERT INTO `user`(`name`, `email`, `nick_name`, `image_url`) VALUES 
("김영곤", "dudrhs571@gmail.com", "zeroGone", "https://studylog.s3.ap-northeast-2.amazonaws.com/img/user/myimg.jpg"),
("모세현", "ahtpgus@naver.com", "모세", "/img/user-default/3.png"),
("홍성수", "hss0281@naver.com", "홍구리", "/img/user-default/4.png"),
("김영곤", "dudrhs571@naver.com", "zeroGone7247", "/img/user-default/9.png"),
("홍준성", "dundung@gmail.com", "DunDung", "/img/user-default/5.png"),
("양진민", "yangjinmin95@gmail.com", "JinminZZANG2", "/img/user-default/2.png"),
("양진민", "yjm7485@naver.com", "JinminZZANG", "/img/user-default/8.png"),
("김민섭", "welcltm@gmail.com", "김민섭", "https://studylog.s3.ap-northeast-2.amazonaws.com/img/user/sulivan.jpg");

INSERT INTO `blog_member_role`(`name`)
VALUES ("ADMIN"), ("MEMBER"), ("INVITING");

INSERT INTO `blog`(`name`, `introduce`, `image_url`, `invitation_key`)
VALUES ("studylog", "web platform for team blog", "/img/blog-default.png", "115116117100121108111103");

INSERT INTO `blog_member` (`user_id`, `blog_id`, `role_id`)
VALUES (1, 1, 1), (6,1,2);

INSERT INTO `post` (`title`, `contents`, `blog_id`, `user_id`)
VALUES ("studylog project", '호이스팅\n==========\n\n- 자바스크립트 Parser가 스크립트를 실행하기 전에 전체를 한 번 훑고\n스크립트 내의 변수와 함수를 <u>**최상단에 올려 선언**</u>하는 것을 말한다\n \n - 실제 메모리에는 변화가 없다\n \n - 호이스팅의 대상은 var 변수와 함수 선언문이다. let,const 변수와 함수 표현문은 호이스팅되지 않는다.\n \n ```\n var value = "Hello World!";\n ```\n 위의 코드가 실제 실행되었을 때 이처럼 실행된다\n \n ```\n var value;\n value = "Hello World!";\n ```\n 전자가 실제 실행되었을 때 후자처럼 value 변수가 호이스팅되서 **최상단에 선언** 된다\n \n - 따라서 후에 선언된 var 변수를 사용했을 경우 **값이 할당이 안되어** undefined 가 반환되지만 let, const 변수는 **선언조차 안되었기 때문에 에러**가 발생한다\n \n ```\n console.log(value); // undefined\n console.log(letValue); //error!\n \n var value = "Hello World!";\n let letVlaue = "Hello Let";\n ```\n 위의 코드가 실제 실행되었을 때 이처럼 실행된다\n \n ```\n var value;\n console.log(value); //undefined\n console.log(letValue);  //error!\n \n value = "Hello World!";\n let letValue = "Hello Let";\n ```\n \n ---------------------\n \n - 함수 선언문의 호이스팅\n \n ```\n printHello(); // "Hello"\n \n function printHello(){\n   console.log("Hello");\n }\n ```\n 위의 코드에서 함수는 호이스팅되어 최상단에 선언되기 때문에 실제로는 아래 코드와 같다\n ```\n function printHello(){\n   console.log("Hello");\n }\n \n printHello(); // "Hello"\n ```\n \n - 하지만 함수 표현문의 경우 호이스팅되지 않는다\n \n ```\n printHello(); // error\n \n var printHello = function(){\n   console.log("Hello");\n }\n ```\n \n 위의 코드에서 printHello 함수가 실제로 실행되었을 때는 변수로 선언되기 때문에 함수가 아니라는 에러가 뜬다. 아래 코드는 위 코드가 실제 실행되었을 때이다.\n \n ```\n var printHello;\n \n printHello(); // this is not function!!\n \n printHello = function(){\n   console.log("Hello");\n }\n ```\n \n \n - 함수 선언문과 함수 표현문은 실제 동작하는 것은 다르고, 함수 표현문은 **디버깅 하기가 힘들기 때문에** 충분히 생각하고 사용하자\n \n -----------\n \n > 원문\n [[JavaScript] 호이스팅(Hoisting)\n ](https://gmlwjd9405.github.io/2019/04/22/javascript-hoisting.html)\n', 1, 1);

INSERT INTO `issue_category` (`name`)
VALUES ("NOTICE"), ("QUESTION"), ("CHAT"), ("REQUEST");

INSERT INTO `issue` (`title`, `contents`, `create_date`, `issue_category_id`, `blog_member_id`)
VALUES 
("스터디 로그 공지사항", "언제부터 시작했는지 기억도 안나지만 꼭 이번 프로젝트 무사히 완성하고 배포까지 해서 포트폴리오로 자랑스럽게 내놓고 취업에 성공하자", CURRENT_DATE(), 1, 1),
("멘탈 관리 주의사항", "프로젝트하면서 자신의 실력에 너무 낙담하지말고 열심히 하되 게을러 지지는 말자 놀땐 놀더라도 놀고 할땐 하자. 그리고 열심히 하자", CURRENT_DATE(), 1, 1),
("ㅎㅇ", "ㅎㅇ", CURRENT_DATE(), 3, 1);

INSERT INTO `issue_comment` (`contents`, `create_date`, `issue_id`, `blog_member_id`)
VALUES ("hi", CURRENT_DATE(), 3, 1);
