class Editor {
    #instance;

    constructor() {
        this.#instance = new toastui.Editor({
            el: document.getElementById("editor"),
            height: "700px",
            previewStyle: "vertical",
            previewHighlight: true,
            placeholder: "내용을 입력하세요.."
        });
    }

    getContent() {
        return this.#instance.getMarkdown();
    }
}

class Viewer {
    #instance;
    #container = document.querySelector(".viewer-container");
    #title = document.querySelector(".viewer-title");
    #categories = document.querySelector(".viewer-category");

    constructor() {
        this.#instance = toastui.Editor.factory({
            el: document.querySelector('#viewer'),
            viewer: true
        });
    }

    setContent(content) {
        this.#instance.setMarkdown(content);
    }

    setTitle(title) {
        this.#title.innerText = title;
    }

    addCategory(category) {
        this.#categories.innerText += Category.HASH + category + " ";
    }

    show() {
        this.#container.classList.remove("hide");
    }

    hide() {
        this.#container.classList.add("hide");
    }
}

class CategoryFactory {
    #container = document.querySelector(".editor-category");
    #input = document.querySelector(".editor-category-input");
    #items = [];

    constructor() {
        this.#input.addEventListener("keypress", (event) => this.#addCategory(event));
    }

    getCategories() {
        return this.#items;
    }

    #addCategory(event) {
        if (event.key !== "Enter") {
            return;
        }
        viewer.addCategory(this.#input.value);

        const category = new Category(this.#input.value);
        this.#items.push(category);
        this.#container.insertBefore(category.getHtmlNode(), this.#input);
        this.#input.value = "";
    }
}

class Category {
    static HASH = "#";
    static CLASS = "editor-category-item";
    #content;
    #htmlNode;

    constructor(content) {
        this.#content = content;
        this.#htmlNode = this.#createHtmlNode(content);
    }

    getContent() {
        return this.#content;
    }

    getHtmlNode() {
        return this.#htmlNode;
    }

    #createHtmlNode(content) {
        const newSpan = document.createElement("span");
        newSpan.classList.add(Category.CLASS);
        newSpan.appendChild(document.createTextNode(Category.HASH + content));
        return newSpan;
    }
}

function submitPost() {
    if (document.querySelector(".editor-title").value === "") {
        alert("제목을 입력해주세요!");
        return;
    }

    viewer.setTitle(document.querySelector(".editor-title").value);
    viewer.setContent(editor.getContent());
    viewer.show();
}

const categories = new CategoryFactory();
const editor = new Editor();
const viewer = new Viewer();

document.querySelector(".save-button").addEventListener("click", submitPost);
document.querySelector(".cancel-button").addEventListener("click", () => viewer.hide());