<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/signup.css">
    <script src="/js/signup.js" defer></script>
</head>
<body>
<header class="header">
    <h1 class="header-title">WELCOME</h1>
</header>
<div class="main-container">
    <section class="welcome-container">
        <div class="welcome-text">안녕하세요 <span class="welcome-user-name">${visitor.name}</span> 님!</div>
        <img class="welcome-user-image" src="${visitor.imgUrl}" alt="user image"/>
    </section>
    <section class="signup-input-container">
        <label class="input-label" for="input-nick">닉네임을 설정해주세요!</label>
        <input class="input-nick" id="input-nick" type="text" maxlength="15" value="${visitor.name}"/>
        <div class="input-warning">닉네임은 15글자 이하로 제한됩니다</div>
        <button class="signup-button">회원가입</button>
    </section>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
