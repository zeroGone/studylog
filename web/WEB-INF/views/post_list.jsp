<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/post_list.css">
    <script src="js/post_list.js" defer></script>
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container posts">
    <section class="title">
        <img src="img/post_icon.png" alt="post_icon" class="title-icon">
        <h2 class="title-header">Post</h2>
        <div class="title-footer">
            <span class="title-footer-texts">Look at all the posts.</span>
        </div>
    </section>
    <article class="search-box">
        <div class="search-icon"><i class="fas fa-search search-icon-button"></i></div>
        <input class="search-input" type="text" value="" placeholder="검색어를 입력해주세요."/>
    </article>
    <section class="posts-list">
        <div class="card left">
            <div class="card-image">
                <img class="card-image-thumbnail" src="img/user-default/2.png" alt="card-image-thumbnail"/>
            </div>
            <div class="card-text">
                <h3 class="card-title">나는 자연인이다</h3>
                <div class="card-sub">(전국종합=연합뉴스) 임화섭 이재림 정빛나 기자 = 국내 신종 코로나바이러스 감염증(코로나19) 확진자 증가세가 주말·휴일 영향으로 다소 주춤해진
                    가운데
                    22일에도 전국 곳곳에서 신규 확진자가 잇따랐다.
                    방역당국과 서울시 등 각 지방자치단체에 따르면 이날 0시부터 오후 6시까지 전국에서 코로나19 양성 판정을 받은 신규 확진자는 총 254명으로 집계됐다.
                    전날 같은 시간에 집계된 271명보다 17명 적다.
                    확진자가 나온 지역을 보면 수도권이 209명(82.3%), 비수도권이 45명(17.7%)이다.
                    시도별로는 서울 107명, 경기 91명, 인천 11명, 부산·강원 각 10명, 경북 7명, 충남 4명, 광주·경남·전북 각 3명, 충북 2명, 대구·대전·전남 각 1명이다.
                    전국 17개 시도 가운데 울산·세종·제주에서는 아직 확진자가 나오지 않았다.
                    집계를 마감하는 자정까지 아직 시간이 남은 만큼 23일 0시 기준으로 발표될 신규 확진자 수는 이보다 늘어 300명 안팎을 기록할 것으로 보인다. 다만 평일에는 검사 건수가 다시
                    늘어나는
                    만큼 밤 시간대에 확진자가 증가하면서 감염 규모가 더 커질 수도 있다.
                </div>
            </div>
            <div class="card-hashtags">
                <span class="hashtags-item">#영곤</span>
                <span class="hashtags-item">#진민</span>
                <span class="hashtags-item">#스터디로그</span>
            </div>
            <div class="card-footer">
                <div class="date border">
                    <div class="type">date</div>
                    <div class="value">2021-02-22</div>
                </div>
                <div class="views">
                    <div class="type">views</div>
                    <div class="value">123</div>
                </div>
            </div>
        </div>
        <div class="card not-image">
            <div class="card-image"></div>
            <div class="card-text">
                <h3 class="card-title">나는 자연인이다</h3>
                <div class="card-sub">(전국종합=연합뉴스) 임화섭 이재림 정빛나 기자 = 국내 신종 코로나바이러스 감염증(코로나19) 확진자 증가세가 주말·휴일 영향으로 다소 주춤해진
                    가운데
                    22일에도 전국 곳곳에서 신규 확진자가 잇따랐다.
                    방역당국과 서울시 등 각 지방자치단체에 따르면 이날 0시부터 오후 6시까지 전국에서 코로나19 양성 판정을 받은 신규 확진자는 총 254명으로 집계됐다.
                    전날 같은 시간에 집계된 271명보다 17명 적다.
                    확진자가 나온 지역을 보면 수도권이 209명(82.3%), 비수도권이 45명(17.7%)이다.
                    시도별로는 서울 107명, 경기 91명, 인천 11명, 부산·강원 각 10명, 경북 7명, 충남 4명, 광주·경남·전북 각 3명, 충북 2명, 대구·대전·전남 각 1명이다.
                    전국 17개 시도 가운데 울산·세종·제주에서는 아직 확진자가 나오지 않았다.
                    집계를 마감하는 자정까지 아직 시간이 남은 만큼 23일 0시 기준으로 발표될 신규 확진자 수는 이보다 늘어 300명 안팎을 기록할 것으로 보인다. 다만 평일에는 검사 건수가 다시
                    늘어나는
                    만큼 밤 시간대에 확진자가 증가하면서 감염 규모가 더 커질 수도 있다.
                </div>
            </div>
            <div class="card-hashtags">
                <span class="hashtags-item">#영곤</span>
                <span class="hashtags-item">#진민</span>
                <span class="hashtags-item">#스터디로그</span>
            </div>
            <div class="card-footer">
                <div class="date border">
                    <div class="type">date</div>
                    <div class="value">2021-02-22</div>
                </div>
                <div class="views">
                    <div class="type">views</div>
                    <div class="value">123</div>
                </div>
            </div>
        </div>
        <div class="card right">
            <div class="card-image"></div>
            <div class="card-text">
                <h3 class="card-title">나는 자연인이다</h3>
                <div class="card-sub">(전국종합=연합뉴스) 임화섭 이재림 정빛나 기자 = 국내 신종 코로나바이러스 감염증(코로나19) 확진자 증가세가 주말·휴일 영향으로 다소 주춤해진
                    가운데
                    22일에도 전국 곳곳에서 신규 확진자가 잇따랐다.
                    방역당국과 서울시 등 각 지방자치단체에 따르면 이날 0시부터 오후 6시까지 전국에서 코로나19 양성 판정을 받은 신규 확진자는 총 254명으로 집계됐다.
                    전날 같은 시간에 집계된 271명보다 17명 적다.
                    확진자가 나온 지역을 보면 수도권이 209명(82.3%), 비수도권이 45명(17.7%)이다.
                    시도별로는 서울 107명, 경기 91명, 인천 11명, 부산·강원 각 10명, 경북 7명, 충남 4명, 광주·경남·전북 각 3명, 충북 2명, 대구·대전·전남 각 1명이다.
                    전국 17개 시도 가운데 울산·세종·제주에서는 아직 확진자가 나오지 않았다.
                    집계를 마감하는 자정까지 아직 시간이 남은 만큼 23일 0시 기준으로 발표될 신규 확진자 수는 이보다 늘어 300명 안팎을 기록할 것으로 보인다. 다만 평일에는 검사 건수가 다시
                    늘어나는
                    만큼 밤 시간대에 확진자가 증가하면서 감염 규모가 더 커질 수도 있다.
                </div>
            </div>
            <div class="card-hashtags">
                <span class="hashtags-item">#영곤</span>
                <span class="hashtags-item">#진민</span>
                <span class="hashtags-item">#스터디로그</span>
            </div>
            <div class="card-footer">
                <div class="date border">
                    <div class="type">date</div>
                    <div class="value">2021-02-22</div>
                </div>
                <div class="views">
                    <div class="type">views</div>
                    <div class="value">123</div>
                </div>
            </div>
        </div>
        <div class="card left">
            <div class="card-image">
                <img class="card-image-thumbnail" src="img/user-default/1.png" alt="card-image-thumbnail"/>
            </div>
            <div class="card-text">
                <h3 class="card-title">나는 자연인이다</h3>
                <div class="card-sub">(전국종합=연합뉴스) 임화섭 이재림 정빛나 기자 = 국내 신종 코로나바이러스 감염증(코로나19) 확진자 증가세가 주말·휴일 영향으로 다소 주춤해진
                    가운데
                    22일에도 전국 곳곳에서 신규 확진자가 잇따랐다.
                    방역당국과 서울시 등 각 지방자치단체에 따르면 이날 0시부터 오후 6시까지 전국에서 코로나19 양성 판정을 받은 신규 확진자는 총 254명으로 집계됐다.
                    전날 같은 시간에 집계된 271명보다 17명 적다.
                    확진자가 나온 지역을 보면 수도권이 209명(82.3%), 비수도권이 45명(17.7%)이다.
                    시도별로는 서울 107명, 경기 91명, 인천 11명, 부산·강원 각 10명, 경북 7명, 충남 4명, 광주·경남·전북 각 3명, 충북 2명, 대구·대전·전남 각 1명이다.
                    전국 17개 시도 가운데 울산·세종·제주에서는 아직 확진자가 나오지 않았다.
                    집계를 마감하는 자정까지 아직 시간이 남은 만큼 23일 0시 기준으로 발표될 신규 확진자 수는 이보다 늘어 300명 안팎을 기록할 것으로 보인다. 다만 평일에는 검사 건수가 다시
                    늘어나는
                    만큼 밤 시간대에 확진자가 증가하면서 감염 규모가 더 커질 수도 있다.
                </div>
            </div>
            <div class="card-hashtags">
                <span class="hashtags-item">#영곤</span>
                <span class="hashtags-item">#진민</span>
                <span class="hashtags-item">#스터디로그</span>
            </div>
            <div class="card-footer">
                <div class="date border">
                    <div class="type">date</div>
                    <div class="value">2021-02-22</div>
                </div>
                <div class="views">
                    <div class="type">views</div>
                    <div class="value">123</div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-image">
                <img class="card-image-thumbnail" src="img/user-default/4.png" alt="card-image-thumbnail"/>
            </div>
            <div class="card-text">
                <h3 class="card-title">나는 자연인이다</h3>
                <div class="card-sub">(전국종합=연합뉴스) 임화섭 이재림 정빛나 기자 = 국내 신종 코로나바이러스 감염증(코로나19) 확진자 증가세가 주말·휴일 영향으로 다소 주춤해진
                    가운데
                    22일에도 전국 곳곳에서 신규 확진자가 잇따랐다.
                    방역당국과 서울시 등 각 지방자치단체에 따르면 이날 0시부터 오후 6시까지 전국에서 코로나19 양성 판정을 받은 신규 확진자는 총 254명으로 집계됐다.
                    전날 같은 시간에 집계된 271명보다 17명 적다.
                    확진자가 나온 지역을 보면 수도권이 209명(82.3%), 비수도권이 45명(17.7%)이다.
                    시도별로는 서울 107명, 경기 91명, 인천 11명, 부산·강원 각 10명, 경북 7명, 충남 4명, 광주·경남·전북 각 3명, 충북 2명, 대구·대전·전남 각 1명이다.
                    전국 17개 시도 가운데 울산·세종·제주에서는 아직 확진자가 나오지 않았다.
                    집계를 마감하는 자정까지 아직 시간이 남은 만큼 23일 0시 기준으로 발표될 신규 확진자 수는 이보다 늘어 300명 안팎을 기록할 것으로 보인다. 다만 평일에는 검사 건수가 다시
                    늘어나는
                    만큼 밤 시간대에 확진자가 증가하면서 감염 규모가 더 커질 수도 있다.
                </div>
            </div>
            <div class="card-hashtags">
                <span class="hashtags-item">#영곤</span>
                <span class="hashtags-item">#진민</span>
                <span class="hashtags-item">#스터디로그</span>
            </div>
            <div class="card-footer">
                <div class="date border">
                    <div class="type">date</div>
                    <div class="value">2021-02-22</div>
                </div>
                <div class="views">
                    <div class="type">views</div>
                    <div class="value">123</div>
                </div>
            </div>
        </div>
    </section>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>