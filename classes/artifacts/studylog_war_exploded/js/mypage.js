const blogCreateContainer = document.querySelector(".blog-create-container");

function getArticleNode(event) {
    return event.target.closest("article");
}

function isBlogCreateButton(article) {
    return article.classList.contains("blog-create-button");
}

function moveBlogMainPage(article) {
    window.location.pathname = article.querySelector(".blog-name").innerText;
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