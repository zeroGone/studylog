<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/main.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="/js/main_scroll.js" defer></script>
    <script src="/js/main_list_control.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body class="fullscreen">
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container sideOff">
    <section data-title="members" class="main-section members">
        <h2 class="main-section-title">Members</h2>
        <div class="member-container">
            <i class="fas fa-chevron-circle-left main-section-list-less member-list-less"></i>
            <ul class="member-list">
                <c:forEach items="${blog.members}" var="member">
                    <c:if test="${member.role ne 'INVITING'}">
                        <li class="member-list-item">
                            <img src="${member.imageUrl}" alt="member img"
                                 class="member-list-item-user"/>
                            <span class="member-list-item-nickname">${member.nickName}</span>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
            <i class="fas fa-chevron-circle-right main-section-list-more member-list-more"></i>
        </div>
    </section>

    <section data-title="reviewing" class="main-section reviewing">
        <h2 class="main-section-title reviewing-title">Reviewing</h2>
        <i class="fas fa-caret-up main-section-list-less reviewing-list-less"></i>
        <ul class="main-section-list reviewing-list">
            <li class="main-section-list-item reviewing-list-item">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit
                    주의사항1</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit
                    주의사항2</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit
                    주의사항3</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit
                    주의사항4</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit
                    주의사항5</h2>
                <span class="main-section-list-item-post reviewing-list-item-post">studyhub</span>
                <span class="main-section-list-item-date reviewing-list-item-date">2021-02-01</span>
                <div class="main-section-list-item-footer reviewing-list-item-footer">
                    <i class="far fa-comment-alt main-section-list-item-footer-comment reviewing-list-item-footer-comment"></i>
                    <span class="main-section-list-item-footer-comment-count reviewing-list-item-footer-comment-count">2</span>
                    <img src="/img/user-default/1.png" alt="writer-01"
                         class="main-section-list-item-footer-image reviewing-list-item-footer-image">
                </div>
            </li>
            <li class="main-section-list-item reviewing-list-item">
                <h2 class="main-section-list-item-title reviewing-list-item-title">StudyHub PR & Commit
                    주의사항6</h2>
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

    <section data-title="recent-activity" class="main-section recent-activity">
        <article class="recent-activity-container">
            <h2 class="recent-activity-title">Recent activity</h2>
            <i class="fas fa-caret-up main-section-list-less recent-activity-list-less"></i>
            <ul class="recent-activity-list">
                <li class="recent-activity-item">
                    <div class="recent-activity-icon newface">
                        <i class="fas fa-grin-wink"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <h3 class="recent-activity-item-title">팀에 뉴페이스가 두두등장~!!</h3>
                        <div class="recent-activity-footer">
                            <span class="recent-activity-date">2021-02-15</span>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon open-issue">
                        <i class="fas fa-exclamation-triangle"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <h3 class="recent-activity-item-title">새로운 이슈가 생겼어요!</h3>
                        <div class="recent-activity-footer">
                            <span class="recent-activity-date">2021-02-15</span>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon close-issue">
                        <i class="fas fa-exclamation-triangle"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <h3 class="recent-activity-item-title">이슈 해결!</h3>
                        <div class="recent-activity-footer">
                            <span class="recent-activity-date">2021-02-15</span>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon comment">
                        <i class="fas fa-comment-dots"></i>

                    </div>
                    <div class="recent-activity-contents">
                        <h3 class="recent-activity-item-title">새로운 댓글이 달렸네요.</h3>
                        <div class="recent-activity-footer">
                            <span class="recent-activity-date">2021-02-15</span>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon open-review">
                        <i class="fas fa-comments"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <h3 class="recent-activity-item-title">리뷰 부탁드립니다~!</h3>
                        <div class="recent-activity-footer">
                            <span class="recent-activity-date">2021-02-15</span>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
                <li class="recent-activity-item">
                    <div class="recent-activity-icon open-post">
                        <i class="fas fa-book-open"></i>
                    </div>
                    <div class="recent-activity-contents">
                        <h3 class="recent-activity-item-title">글 게시!</h3>
                        <div class="recent-activity-footer">
                            <span class="recent-activity-date">2021-02-15</span>
                            <img class="recent-activity-user" src="/img/user-default/1.png" alt="writer-01">
                        </div>
                    </div>
                </li>
            </ul>
            <i class="fas fa-caret-down main-section-list-more recent-activity-list-more"></i>
        </article>
    </section>
</div>
<div class="section-navigation">
    <div class="section-button active">
        <span class="section-navigation-text">members</span>
    </div>
    <div class="section-button">
        <span class="section-navigation-text">reviewing</span>
    </div>
    <div class="section-button">
        <span class="section-navigation-text">recent-activity</span>
    </div>
    <div class="section-button">
        <span class="section-navigation-text">footer</span>
    </div>
</div>
<div class="scroll-information">
    <i class="fas fa-angle-double-down scroll-icon"></i>
    <span class="scroll-information-text">scroll</span>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
