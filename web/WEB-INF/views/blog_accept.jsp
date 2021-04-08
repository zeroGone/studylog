<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/signup.css">
</head>
<body>
<header class="header">
    <h1 class="header-title">WELCOME</h1>
</header>
<div class="main-container">
    <section class="welcome-container">
        <img class="welcome-user-image" src="${blog.imageUrl}" alt="blog image"/>
        <div class="welcome-text welcome-blog-text">${blog.name} <span class="welcome-user-name"></span> 에 일원이 되셨습니다!
        </div>
    </section>
    <section class="signup-input-container">
        <div class="input-label">적극적인 활동 기대할게요~!</div>
        <a href="/">
            <button class="signup-button">홈으로</button>
        </a>
    </section>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
