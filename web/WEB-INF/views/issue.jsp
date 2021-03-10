<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/issue.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container">
    <section class="issue-header">
        <h1 class="issue-title">제목 들어갈 자리</h1>
        <div class="issue-tag">
            <div class="issue-category notice">notice</div>
            <div class="issue-active open">open</div>
        </div>
    </section>
    <ul class="list">
        <li class="issue-content">
            <img class="writer-img" src="/img/user-default/2.png" alt="user img"/>
            <div class="small-triangle"></div>
            <div class="content">
                <div class="content-header">
                    <span class="writer-name">zeroGone</span>
                    <span class="date">2021-02-19</span>
                </div>
                <div class="text">본문</div>
            </div>
        </li>
        <li class="comment-divider">
            comment
        </li>
        <li class="comment">
            <img class="writer-img" src="/img/user-default/5.png" alt="user img"/>
            <span class="small-triangle"></span>
            <div class="content">
                <div class="content-header">
                    <span class="writer-name">Jinmin Yang</span>
                    <span class="date">2021-02-19</span>
                </div>
                <div class="text">is gooooooooooooooooooooood!</div>
            </div>
        </li>

        <li class="comment">
            <img class="writer-img" src="/img/user-default/5.png" alt="user img"/>
            <div class="small-triangle"></div>
            <div class="content">
                <div class="content-header">
                    <span class="writer-name">Jinmin Yang</span>
                    <span class="date">2021-02-19</span>
                </div>
                <div class="text">어떠한 글을 쓸까요</div>
            </div>
        </li>
        <li class="comment">
            <img class="writer-img" src="/img/user-default/6.png" alt="user img"/>
            <div class="small-triangle"></div>
            <div class="content">
                <div class="content-header">
                    <span class="writer-name">unknown</span>
                    <span class="date">2021-02-19</span>
                </div>
                <div class="text">어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요
                    >어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요
                    >어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요>어떠한 글을 쓸까요
                </div>
            </div>
        </li>
        <li>
            <div class="comment-input-box">
                <div class="comment-input" contenteditable=true data-placeholder="input your think :)"></div>
            </div>
            <div class="comment-save-button">
                <i class="fas fa-pen"></i>
            </div>
        </li>
    </ul>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>