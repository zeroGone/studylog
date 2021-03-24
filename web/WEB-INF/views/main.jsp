<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container sideOff">
    <section class="main-section members">
        <h2 class="main-section-title">Members</h2>
        <div class="member-container">
            <ul class="member-list">
                <c:forEach items="${members}" var="member">
                    <li class="member-list-item">
                        <img src="${member.imgUrl}" alt="member img"
                             class="member-list-item-user"/>
                        <span class="member-list-item-nickname">${member.nickName}</span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </section>

    <section class="main-section reviewing">
        <h2 class="main-section-title reviewing-title">Reviewing</h2>
        <ul class="main-section-list reviewing-list">
            <li class="main-section-list-item reviewing-list-item reviewing-list-item-01">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit 주의사항</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image eviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item reviewing-list-item-02">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit 주의사항</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item reviewing-list-item-03">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit 주의사항</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item reviewing-list-item-04">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit 주의사항</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item reviewing-list-item-05">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit 주의사항</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
        </ul>
        <i class="fas fa-caret-down main-section-list-more reviewing-list-more"></i>
    </section>

    <section class="recent-activity">
        <article class="recent-activity-container">
            <h2 class="recent-activity-title">Recent activity</h2>
            <ul class="recent-activity-list">
                <li class="recent-activity-item">
                    <div class="recent-activity-icon newface">
                        <i class="fas fa-grin-wink"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <div class="recent-activity-text">팀에 뉴페이스가 두두등장~!!</div>
                        <div class="recent-activity-footer">
                            <div class="recent-activity-date">2021-02-15</div>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon open-issue">
                        <i class="fas fa-exclamation-triangle"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <div class="recent-activity-text">새로운 이슈가 생겼어요!</div>
                        <div class="recent-activity-footer">
                            <div class="recent-activity-date">2021-02-15</div>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon close-issue">
                        <i class="fas fa-exclamation-triangle"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <div class="recent-activity-text">이슈 해결!</div>
                        <div class="recent-activity-footer">
                            <div class="recent-activity-date">2021-02-15</div>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon comment">
                        <i class="fas fa-comment-dots"></i>

                    </div>
                    <div class="recent-activity-contents">
                        <div class="recent-activity-text">새로운 댓글이 달렸네요.</div>
                        <div class="recent-activity-footer">
                            <div class="recent-activity-date">2021-02-15</div>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon open-review">
                        <i class="fas fa-comments"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <div class="recent-activity-text">리뷰 부탁드립니다~!</div>
                        <div class="recent-activity-footer">
                            <div class="recent-activity-date">2021-02-15</div>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon open-post">
                        <i class="fas fa-book-open"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <div class="recent-activity-text">글 게시!</div>
                        <div class="recent-activity-footer">
                            <div class="recent-activity-date">2021-02-15</div>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
            </ul>
            <i class="fas fa-caret-down main-section-list-more recent-activity-list-more"></i>
        </article>
    </section>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
