<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/blog_setting.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container blog-setting">
    <h2 class="blog-setting-title">블로그 설정</h2>
    <div class="blog-info">
        <div class="blog-info-images">
            <img src="${blog.imageUrl}" alt="blog-image" class="blog-info-images-image">
            <div class="blog-info-images-edit-container">
                <i class="fas fa-plus blog-info-images-edit"></i>
            </div>
        </div>
        <div class="blog-info-texts">
            <input type="text" class="blog-info-texts-name" value="${blog.name}" readonly/>
            <input type="text" class="blog-info-texts-introduction" placeholder="블로그를 소개해보세요."
                   value="${blog.introduce}"/>
        </div>
    </div>
    <div class="member">
        <h2 class="member-title">멤버</h2>
        <div class="member-invite">
            <label for="member-invite-link" class="member-invite-link-label">블로그 초대 링크</label>
            <input type="text" value="https://studylog.com/invite/a1sd4d78sd8g1ddf45dw789rq456"
                   id="member-invite-link" class="member-invite-link" readonly/>
            <span class="member-invite-link-copy">링크 복사</span>

            <label for="member-invite-email" class="member-invite-email-label">이메일 입력</label>
            <input type="text" id="member-invite-email" class="member-invite-email" placeholder="초대받는 분의 이메일을 입력하세요."
                   value=""/>
            <span class="member-invite-email-button">보내기</span>
        </div>
        <div class="member-user">
            <div class="member-user-header">
                <span class="member-user-title">사용자</span>
                <span class="member-user-access-level">접근 수준</span>
            </div>
            <ul class="member-list">
                <c:forEach items="${blog.members}" var="member">
                    <li class="member-list-item member-list-item-01">
                        <img src="${member.imageUrl}" alt="user-01-image" class="member-list-item-image"/>
                        <span class="member-list-item-name">${member.name}</span>
                        <span class="member-list-item-email">${member.email}</span>
                        <span class="member-list-item-access-level">
                            <i class="fas fa-caret-down member-list-item-access-level-control"></i>
                            <c:choose>
                                <c:when test="${member.role eq 'ADMIN'}">관리자</c:when>
                                <c:when test="${member.role eq 'MEMBER'}">멤버</c:when>
                                <c:when test="${member.role eq 'INVITING'}">초대중</c:when>
                            </c:choose>
                        </span>
                        <div class="member-list-item-access-level-setting">
                            <span class="member-list-item-access-level-setting-administrator">관리자</span>
                            <span class="member-list-item-access-level-setting-member">멤버</span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="buttons">
        <input type="button" class="buttons-save" id="buttons-save" value="저장"/>
        <input type="reset" class="buttons-reset" id="buttons-reset" value="취소"/>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>