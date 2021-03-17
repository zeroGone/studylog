<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/mypage.css">
    <script src="/js/mypage.js" defer></script>
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="/js/member_invite.js?ver=1.1" defer></script>
    <script src="/js/img_upload.js?ver=1.0" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container">
    <div class="mypage">
        <section class="profile">
            <img class="profile-img" src="/img/user-default/1.png" alt="user profile img">
            <div class="profile-info">
                <article class="profile-name">
                    <div class="profile-header1">name</div>
                    <div class="profile-text">
                        <div>
                            <i class="far fa-user"></i>
                            <span class="profile-content">김영곤</span>
                        </div>
                    </div>
                </article>
                <article class="profile-email">
                    <div class="profile-header1">email</div>
                    <div class="profile-text">
                        <div>
                            <i class="fab fa-google"></i>
                            <span class="profile-content">dudrhs571@gmail.com</span>
                        </div>
                    </div>
                </article>
                <article class="profile-nick">
                    <div class="profile-nick-body">
                        <div class="profile-header2">nickname</div>
                        <div class="profile-text">
                            <div>
                                <i class="fas fa-user-secret"></i>
                                <input class="profile-input profile-content" value="zeroGone"></input>
                            </div>
                        </div>
                    </div>
                    <div class="profile-edit-button">
                        <i class="fas fa-edit"></i>
                    </div>
                </article>
            </div>
        </section>
        <section class="blog">
            <article class="blog-item">
                <div class="blog-icon"><i class="fab fa-teamspeak"></i></div>
                <div class="blog-name">블로그1</div>
            </article>
            <article class="blog-item">
                <div class="blog-icon"><i class="fas fa-user-friends"></i></div>
                <div class="blog-name">몇글자까지 허용하냐</div>
            </article>
            <article class="blog-item">
                <div class="blog-icon"><i class="fas fa-award"></i></div>
                <div class="blog-name">zzzzzzzzzz</div>
            </article>
            <article class="blog-item">
                <div class="blog-icon"><i class="fas fa-bomb"></i></div>
                <div class="blog-name">zzzz</div>
            </article>
            <article class="blog-item">
                <div class="blog-icon"><i class="far fa-dizzy"></i></div>
                <div class="blog-name">블로그2</div>
            </article>
            <article class="blog-item">
                <div class="blog-icon"><i class="far fa-eye"></i></div>
                <div class="blog-name">블로그3</div>
            </article>
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
                        <input id="blog-create-name" type="text" class="blog-create-input" maxlength="40"
                               placeholder="블로그 이름을 설정해주세요.">
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
                <div class="blog-create-save-button">저장</div>
            </div>
        </form>
    </article>
    <i class="fas fa-times blog-close-button"></i>
</section>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
