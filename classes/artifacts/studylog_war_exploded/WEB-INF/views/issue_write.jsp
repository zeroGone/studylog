<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/issue_write.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container">
    <form>
        <section class="issue-header">
            <label><input class="issue-title" type="text" placeholder="제목을 입력하세요"></label>
            <div class="issue-tag">
                <div class="issue-category notice">notice</div>
                <div class="issue-category request">request</div>
                <div class="issue-category question">question</div>
                <div class="issue-category chat">chat</div>
            </div>
        </section>
        <section class="list">
            <div class="comment-input-box">
                <div class="comment-input" contenteditable=true data-placeholder="input your think :)"></div>
            </div>
            <div class="save-button">
                <div class="comment-save-button">저장 <i class="fas fa-pen"></i></div>
            </div>
        </section>
    </form>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>