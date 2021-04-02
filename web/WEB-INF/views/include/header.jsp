<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
    <div class="header-menu sideOff">
        <div class="header-menu-bars">
            <span class="header-menu-bar header-menu-bartop"></span>
            <span class="header-menu-bar header-menu-barmid"></span>
            <span class="header-menu-bar header-menu-barbottom"></span>
        </div>
        <span class="header-menu-text">menu</span>
    </div>
    <h1 class="header-title">${blog.name}</h1>
    <div class="header-information">
        <div class="user-info">
            <span class="user-info-nickname">${userInfo.nickName}</span>
            <img src=${userInfo.imgUrl} alt="current-user" class="user-info-image">
        </div>
        <div class="header-notice">
            <img src="/img/header/notice-active.png" alt="notice-icon" class="header-notice-icon"/>
        </div>
    </div>
</header>
<ul class="header-notice-list">
    <li class="header-notice-list-item">
        <div class="header-notice-list-item-header">
            <span class="header-notice-list-item-category issue">Issue</span>
            <span class="header-notice-list-item-date">2021-02-25</span>
        </div>
        <span class="header-notice-list-item-title">어제 생각해봤는데</span>
        <span class="header-notice-list-item-sub">새로운 글이 있습니다.</span>
    </li>
    <li class="header-notice-list-item">
        <div class="header-notice-list-item-header">
            <span class="header-notice-list-item-category review">Review</span>
            <span class="header-notice-list-item-date">2021-02-25</span>
        </div>
        <span class="header-notice-list-item-title">게임은 질병이야</span>
        <span class="header-notice-list-item-sub">글이 Post로 이동되었습니다.</span>
    </li>
    <li class="header-notice-list-item">
        <div class="header-notice-list-item-header">
            <span class="header-notice-list-item-category post">Post</span>
            <span class="header-notice-list-item-date">2021-02-25</span>
        </div>
        <span class="header-notice-list-item-title">오마에와 모 신데이루</span>
        <span class="header-notice-list-item-sub">글에 댓글이 등록되었습니다.</span>
    </li>

</ul>
<ul class="user-info-list">
    <li class="user-info-item">
        <span class="user-info-item-email">${userInfo.email}</span>
    </li>
    <li class="user-info-item">
        <span class="user-info-item-name">내 정보 관리</span>
    </li>
    <li class="user-info-item">
        <span class="user-info-item-logout">로그아웃</span>
    </li>
</ul>