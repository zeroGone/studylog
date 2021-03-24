const blogCreateButton = document.querySelector('.blog-create-save-button');
blogCreateButton.addEventListener('click', postBlogInfo);

function postBlogInfo() {
    const isEnterName = informBlogNameUndefined();
    if (!isEnterName) {
        return false;
    }

    let blogInfo = getValues();

    fetch("api/blog", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(blogInfo),
    })
        .then((response) => response)
        .then((data) => {
            if (data.status === 201) {
                window.location = `/${blogInfo.name}`
            } else {
                activeAlertContainer();
                document.querySelector('.alert-message').innerHTML = `서버 오류 (status: ${data.status})`;
            }
        }).catch((error) => console.error(error));
}

function getValues() {
    const blogName = document.querySelector('#blog-create-name').value;
    const blogIntroduce = document.querySelector('#blog-create-introduce').value;
    const blogImage = document.querySelector('.blog-create-image').getAttribute('data');
    const blogMembers = getMembers();

    return new Blog(blogName, blogIntroduce, blogImage, blogMembers);
}

function getMembers() {
    const blogUserArray = [];
    const blogUsers = document.querySelectorAll('.blog-create-member-item');

    blogUsers.forEach((element) => {
        const userId = element.children.item(1).children.item(0).innerHTML;
        const userName = element.children.item(1).children.item(1).innerHTML;
        const userEmail = element.children.item(1).children.item(2).innerHTML;
        const userNickname = element.children.item(1).children.item(3).innerHTML;

        const memberInfo = new User(userId, userName, userEmail, userNickname);

        blogUserArray.push(memberInfo);
    }, 0);

    return blogUserArray;
}

function informBlogNameUndefined() {
    const blogName = document.querySelector('#blog-create-name');
    if (blogName.value === '') {
        activeAlertContainer('name');
        return false;
    }

    return true;
}