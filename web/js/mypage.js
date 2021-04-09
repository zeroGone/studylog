const blogCreateContainer = document.querySelector(".blog-create-container");

function getArticleNode(event) {
    if (!event.target.classList.contains('blog')) {
        return event.target.closest("article");
    }
    return false;
}

function isBlogCreateButton(article) {
    if (!article) {
        return false;
    } else {
        return article.classList.contains("blog-create-button");
    }
}

function moveBlogMainPage(article) {
    if (!article) {
        return false;
    } else {
        return window.location.pathname = article.querySelector(".blog-name").innerText;
    }
}

function showBlogCreateContainer() {
    blogCreateContainer.classList.remove("hide");
}

document.querySelector(".blog").addEventListener("click", (event) => {
    const article = getArticleNode(event);

    if (isBlogCreateButton(article)) {
        showBlogCreateContainer();
    } else {
        moveBlogMainPage(article);
    }
})

document.querySelector(".blog-close-button").addEventListener("click", () => {
    blogCreateContainer.classList.add("hide");
})