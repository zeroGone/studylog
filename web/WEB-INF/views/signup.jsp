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
<form id="signup-form" class="main-container" method="post" action="/signup">
    <section class="welcome-container" data-email="${visitor.email}">
        <div class="welcome-text">안녕하세요 <span class="welcome-user-name">${visitor.name}</span> 님!</div>
        <img class="welcome-user-image" src="${visitor.imageUrl}" alt="user image"/>
    </section>
    <section class="signup-input-container">
        <input class="transparent" type="text" name="name" value="${visitor.name}"/>
        <input class="transparent" type="email" name="email" value="${visitor.email}"/>
        <input class="transparent" type="text" name="imageUrl" value="${visitor.imageUrl}"/>
        <label class="input-label" for="input-nick">닉네임을 설정해주세요!</label>
        <input class="input-nick" id="input-nick" type="text" name="nickName" maxlength="15" value="${visitor.name}"/>
        <div class="input-warning">
            <c:choose>
                <c:when test="${error eq null}">
                    닉네임은 15글자 이하로 제한됩니다
                </c:when>
                <c:otherwise>
                    ${error}
                </c:otherwise>
            </c:choose>
        </div>
        <button class="signup-button">회원가입</button>
    </section>
</form>

<jsp:include page="include/footer.jsp"/>
</body>
</html>
