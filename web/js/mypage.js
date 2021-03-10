const blogCreateContainer = document.querySelector(".blog-create-container");

function showBlogCreateContainer() {
    blogCreateContainer.classList.remove("hide");
}

function hideBlogCraeteContainer() {
    blogCreateContainer.classList.add("hide");
}

document.querySelector(".blog-create-button").addEventListener("click", () => {
    showBlogCreateContainer();
})

document.querySelector(".blog-close-button").addEventListener("click", () => {
    hideBlogCraeteContainer();
})