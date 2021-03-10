<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/review_list.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/review_list.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container">
    <section class="title">
        <img src="${pageContext.request.contextPath}/img/review_icon.png" alt="review_icon" class="title-icon">
        <h2 class="title-header">Review</h2>
        <div class="title-footer">
            <span class="title-footer-texts">Let's talk about the your team member's idea</span>
        </div>
    </section>
    <section class="review-notices">

    </section>
    <section class="review-list">
        <div class="active-buttons">
            <div class="open-button">open</div>
            <div class="close-button checked">close</div>
        </div>
        <ul class="review-items">
            <article class="search-box">
                <div class="search-icon"><i class="fas fa-search"></i></div>
                <input class="search-input" type="text" placeholder="검색어를 입력해주세요."/>
            </article>
            <div class="divider"></div>
            <li class="review-list-item">
                <div class="review-list-item-header">
                    <div class="review-list-item-header-title">우리의 프로젝트에 관하여</div>
                    <ul class="review-list-item-header-hashtags">
                        <li class="review-list-item-hashtags-list">#영곤</li>
                        <li class="review-list-item-hashtags-list">#진민</li>
                        <li class="review-list-item-hashtags-list">#스터디로그</li>
                        <li class="review-list-item-hashtags-list">#제로곤월드</li>
                    </ul>
                </div>
                <img class="review-list-item-writer-img" src="/img/user-default/5.png" alt="user img"/>
                <div class="review-list-item-date">2021-02-19</div>

            </li>
            <li class="review-list-item">
                <div class="review-list-item-header">
                    <div class="review-list-item-header-title">우리의 프로젝트에 관하여</div>
                    <ul class="review-list-item-header-hashtags">
                        <li class="review-list-item-hashtags-list">#영곤</li>
                        <li class="review-list-item-hashtags-list">#진민</li>
                        <li class="review-list-item-hashtags-list">#스터디로그</li>
                        <li class="review-list-item-hashtags-list">#제로곤월드</li>
                    </ul>
                </div>
                <img class="review-list-item-writer-img" src="/img/user-default/6.png" alt="user img"/>
                <div class="review-list-item-date">2021-02-19</div>
            </li>
            <li class="review-list-item">
                <div class="review-list-item-header">
                    <div class="review-list-item-header-title">우리의 프로젝트에 관하여</div>
                    <ul class="review-list-item-header-hashtags">
                        <li class="review-list-item-hashtags-list">#영곤</li>
                        <li class="review-list-item-hashtags-list">#진민</li>
                        <li class="review-list-item-hashtags-list">#스터디로그</li>
                        <li class="review-list-item-hashtags-list">#제로곤월드</li>
                    </ul>
                </div>
                <img class="review-list-item-writer-img" src="/img/user-default/7.png" alt="user img"/>
                <div class="review-list-item-date">2021-02-19</div>
            </li>
            <li class="review-list-item">
                <div class="review-list-item-header">
                    <div class="review-list-item-header-title">우리의 프로젝트에 관하여</div>
                    <ul class="review-list-item-header-hashtags">
                        <li class="review-list-item-hashtags-list">#영곤</li>
                        <li class="review-list-item-hashtags-list">#진민</li>
                        <li class="review-list-item-hashtags-list">#스터디로그</li>
                        <li class="review-list-item-hashtags-list">#제로곤월드</li>
                    </ul>
                </div>
                <img class="review-list-item-writer-img" src="/img/user-default/8.png" alt="user img"/>
                <div class="review-list-item-date">2021-02-19</div>
            </li>
        </ul>
    </section>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>