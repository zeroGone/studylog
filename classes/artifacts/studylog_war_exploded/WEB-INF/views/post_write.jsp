<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudyLog</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"/>
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css"/>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/post_write.css">
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js" defer></script>
    <script src="/js/post_write.js" defer></script>
    <script src="/js/header.js" defer></script>
    <script src="/js/sidebar.js" defer></script>
    <script src="https://kit.fontawesome.com/b63e743ce0.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<jsp:include page="include/sidebar.jsp"/>
<article class="viewer-container hide">
    <div class="viewer">
        <h1 class="viewer-title"></h1>
        <h3 class="viewer-category"></h3>
        <div id="viewer"></div>
    </div>
    <div class="viewer-buttons">
        <button class="submit-button">제출하기</button>
        <button class="cancel-button">취소</button>
    </div>
</article>
<div class="main-container">
    <div class="editor-container">
        <input type="text" class="editor-title" placeholder="제목을 입력하세요"/>
        <div class="editor-category">
            <input class="editor-category-input" type="text" placeholder="카테고리 입력" maxlength="10"/>
        </div>
        <div id="editorInput" class="editor-input">
        </div>
        <div id="editor" class="editor"></div>
        <button class="save-button">저장</button>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>