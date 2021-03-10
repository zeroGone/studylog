<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/issue_list.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container">
    <section class="title">
        <img src="img/issue_icon.png" alt="issue_icon" class="title-icon">
        <h1 class="title-header">Issue</h1>
        <div class="title-footer">Notice, Request, Question, Chat</div>
    </section>
    <section class="issue-notice">
        <div class="issue-notice-header">notice</div>
        <ul class="issue-notice-list">
            <li class="issue-notice-item">
                <img class="issue-notice-writer-img" src="img/user-default/5.png" alt="user img"/>
                <div class="issue-notice-content">
                    <div class="issue-notice-title">어떠한 글을 쓸까요어떠한 글을 쓸까요어떠한 글을 쓸까</div>
                    <div class="issue-notice-date">2021-02-21</div>
                </div>
            </li>
            <li class="issue-notice-item">
                <img class="issue-notice-writer-img" src="img/user-default/5.png" alt="user img"/>
                <div class="issue-notice-content">
                    <div class="issue-notice-title">어떠한 글을 쓸까요어떠한 글을 쓸까요어떠한 글을 쓸까요</div>
                    <div class="issue-notice-date">2021-02-21</div>
                </div>
            </li>
            <li class="issue-notice-item">
                <img class="issue-notice-writer-img" src="img/user-default/5.png" alt="user img"/>
                <div class="issue-notice-content">
                    <div class="issue-notice-title">어떠한 글을 쓸까요어떠한 글을 쓸까요어떠한 글을 쓸까요</div>
                    <div class="issue-notice-date">2021-02-21</div>
                </div>
            </li>
        </ul>
    </section>
    <section class="issue-list">
        <div class="active-buttons">
            <div class="open-button">open</div>
            <div class="close-button">close</div>
        </div>
        <ul class="items">
            <article class="search-box">
                <div class="search-icon"><i class="fas fa-search"></i></div>
                <input class="search-input" type="text" value="title"/>
            </article>
            <div class="create-button">
                <div class="create-button-icon"><i class="fas fa-pen"></i></div>
                New issue
            </div>
            <div class="divider"></div>
            <li class="item">
                <div class="item-category item-notice">notice</div>
                <div class="item-title">어떠한 글을 쓸까요</div>
                <img class="item-writer-img" src="/img/user-default/5.png" alt="user img"/>
                <div class="item-date">2021-02-19</div>
            </li>
            <li class="item">
                <div class="item-category item-question">question</div>
                <div class="item-title">어떠한 글을 쓸까요</div>
                <img class="item-writer-img" src="/img/user-default/6.png" alt="user img"/>
                <div class="item-date">2021-02-19</div>
            </li>
            <li class="item">
                <div class="item-category item-chat">chat</div>
                <div class="item-title">어떠한 글을 쓸까요</div>
                <img class="item-writer-img" src="/img/user-default/7.png" alt="user img"/>
                <div class="item-date">2021-02-19</div>
            </li>
            <li class="item">
                <div class="item-category item-request">request</div>
                <div class="item-title">어떠한 글을 쓸까요</div>
                <img class="item-writer-img" src="/img/user-default/8.png" alt="user img"/>
                <div class="item-date">2021-02-19</div>
            </li>
        </ul>
    </section>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>