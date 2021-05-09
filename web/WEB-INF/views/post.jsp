<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Studylog</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css"/>
    <link rel="stylesheet" href="/css/post.css">
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
    <script src="/js/post.js" defer></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<div class="main-container">
    <div class="main-container-header">
        <h2 class="title">${post.title}</h2>
        <ul class="hashtags">
            <c:forEach items="${post.categories}" var="category">
                <li class="hashtags-item">#${category}</li>
            </c:forEach>
        </ul>
        <span class="view-number">${post.hits} views</span>
    </div>
    <div class="editor-container" data-contents='${post.contents}'></div>
    <div class="writer">
        <img class="writer-image" src='${post.writer.imageUrl}' alt="writer"/>
        <h3 class="writer-name">zeroGone</h3>
    </div>
    <div class="comment-divider">comment</div>
    <ul class="comment-list">
        <li class="comment">
            <img class="writer-img" src="/img/user-default/5.png" alt="user img"/>
            <span class="small-triangle"></span>
            <div class="comment-content">
                <div class="comment-header">
                    <span class="comment-writer">Jinmin Yang</span>
                    <span class="comment-date">2021-02-19</span>
                </div>
                <div class="comment-text">is gooooooooooooooooooooood!</div>
            </div>
        </li>
        <li class="comment">
            <img class="writer-img" src="/img/user-default/6.png" alt="user img"/>
            <div class="small-triangle"></div>
            <div class="comment-content">
                <div class="comment-header">
                    <span class="comment-writer">zeroGone</span>
                    <span class="comment-date">2021-02-19</span>
                </div>
                <div class="comment-text">Amazing</div>
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
    <div class="page-control">
        <div class="prev-page">
            <i class="fas fa-chevron-circle-left prev-page-button"></i>
            <h3 class="prev-title">이전 페이지 제목</h3>
            <span class="prev-description">이전 페이지에 대한 설명글이전 페이지에 대한 설명글</span>
        </div>
        <div class="next-page">
            <i class="fas fa-chevron-circle-right next-page-button"></i>
            <h3 class="next-title">이전 페이지 제목</h3>
            <span class="next-description">이전 페이지에 대한 설명글이전 페이지에 대한 설명글이전 페이지에 대한 설명글</span>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>