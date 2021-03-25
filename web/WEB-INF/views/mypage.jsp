<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/mypage.css">
    <script src="/js/mypage.js" defer></script>
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="/js/member_invite.js" defer></script>
    <script src="/js/create_blog.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>

<div class="main-container">
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
    <div class="mypage">
        <section class="profile">
            <img class="profile-img" src="/img/user-default/1.png" alt="user profile img">
            <div class="profile-info">
                <article class="profile-name">
                    <div class="profile-header1">name</div>
                    <div class="profile-text">
                        <div>
                            <i class="far fa-user"></i>
                            <span class="profile-content">${userInfo.name}</span>
                        </div>
                    </div>
                </article>
                <article class="profile-email">
                    <div class="profile-header1">email</div>
                    <div class="profile-text">
                        <div>
                            <i class="fab fa-google"></i>
                            <span class="profile-content">${userInfo.email}</span>
                        </div>
                    </div>
                </article>
                <article class="profile-nick">
                    <div class="profile-header2">nickname</div>
                    <div class="profile-text">
                        <div>
                            <i class="fas fa-user-secret"></i>
                            <span class="profile-content">${userInfo.nickName}</span>
                        </div>
                    </div>
                </article>
            </div>
        </section>
        <section class="blog">
            <c:forEach items="${blogs}" var="blog">
                <article class="blog-item">
                    <c:choose>
                        <c:when test="${blog.imgUrl ne null}">
                            <img class="blog-icon" src="${blog.imgUrl}" alt="blog image"/>
                        </c:when>
                        <c:otherwise>
                            <div class="blog-icon"></div>
                        </c:otherwise>
                    </c:choose>
                    <div class="blog-name">${blog.name}</div>
                </article>
            </c:forEach>
            <article class="blog-create-button">
                <i class="fas fa-plus"></i>
            </article>
        </section>
    </div>
</div>
<section class="blog-create-container hide">
    <article class="blog-create-box">
        <form class="blog-create-form">
            <div class="blog-create-image-container">
                <input type="file" id="image-input" class="image-input" name="files1" accept="image/*"/>
                <div id="preview" class="preview">
                    <img src="/img/user-default/5.png" class="blog-create-image" alt="blog-default-image">
                </div>
                <button type="button" class="blog-create-images-edit-container">
                    <i class="fas fa-plus blog-create-images-edit-button"></i>
                </button>
            </div>

            <div class="blog-create-info">
                <div class="blog-create-texts-container">
                    <div class="blog-create-name">
                        <label for="blog-create-name" class="blog-create-label">name</label>
                        <div class="blog-create-member-invite-inputs">
                            <input id="blog-create-name" type="text" class="blog-create-input" maxlength="40"
                                   placeholder="블로그 이름을 설정해주세요." value="" data_result="fail" />
                            <button type="button" class="blog-create-name-check">중복확인</button>
                        </div>
                        <span class="blog-create-input-description">문자 사이에 띄어쓰기는 "_ (Underscore)" 처리됩니다.</span>
                    </div>
                    <div class="blog-create-sub">
                        <label for="blog-create-introduce" class="blog-create-label">introduce</label>
                        <textarea id="blog-create-introduce" class="blog-create-input blog-create-input-introduce"
                                  maxlength="120" placeholder="블로그를 소개해보세요."></textarea>
                    </div>
                </div>

                <div class="divider"></div>

                <div class="blog-create-member-invite">
                    <label for="blog-create-member" class="blog-create-label">member</label>
                    <div class="blog-create-member-invite-inputs">
                        <input id="blog-create-member" type="text" class="blog-create-input" maxlength="40"
                               placeholder="초대받는 분의 이메일을 입력하세요">
                        <button type="button" class="blog-create-member-invite-search">검색</button>
                    </div>
                </div>
                <ul class="blog-create-member-list">
                </ul>
                <button type="button" class="blog-create-save-button">저장</button>
            </div>
        </form>
    </article>
    <i class="fas fa-times blog-close-button"></i>
</section>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
