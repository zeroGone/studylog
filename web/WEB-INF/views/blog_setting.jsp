<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/blog_setting.css">
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="/js/member_invite.js" defer></script>
    <script src="/js/blog-setting.js" defer></script>
    <script src="/js/image_upload.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container blog-setting">
    <div class="alert-container">
        <div class="alert-contents">
            <i class="fas fa-exclamation-circle alert-icon"></i>
            <div class="alert-message">
                This is test.
            </div>
            <div class="alert-buttons">
                <input type="button" class="alert-register" value="등록"/>
                <input type="button" class="alert-confirm" value="확인"/>
                <input type="button" class="alert-cancel" value="취소"/>
            </div>
        </div>
    </div>
    <h2 class="blog-setting-title">블로그 설정</h2>
    <form class="blog-create-form" enctype="multipart/form-data">
        <div class="blog-info">
            <div class="blog-info-images">
                <input type="file" id="blog-image-input" class="blog-image-input" name="blog-image" accept="image/*"/>
                <div id="blog-image-preview" class="blog-image-preview">
                    <img class="blog-image" src="${blog.imageUrl}" alt="blog-profile-img">
                </div>
                <button type="button" class="blog-image-edit-button">
                    <i class="fas fa-plus blog-image-edit-icon"></i>
                </button>
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
                <button type="button" class="member-invite-link-copy">링크 복사</button>

                <label for="blog-member-input" class="member-invite-email-label">이메일 입력</label>
                <input type="text" id="blog-member-input" class="member-invite-email" placeholder="초대받는 분의 이메일을 입력하세요."
                       value=""/>
                <button type="button" class="member-invite-email-button">보내기</button>
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
    </form>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>