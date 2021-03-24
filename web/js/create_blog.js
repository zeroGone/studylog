const blogNameInput = document.querySelector('#blog-create-name');
blogNameInput.addEventListener('keyup', checkRegularExpression);
blogNameInput.setAttribute('onkeypress', 'if( event.keyCode == 13 ){checkForDuplicateName();}');

const blogNameCheckButton = document.querySelector('.blog-create-name-check');
blogNameCheckButton.addEventListener('click', checkForDuplicateName);

const blogCreateButton = document.querySelector('.blog-create-save-button');
blogCreateButton.addEventListener('click', postBlogInfo);

function checkRegularExpression() {
    blogNameInput.setAttribute('data_result', 'fail');
    const expression = RegExp(/^[a-zA-Z0-9\s]+$/);
    const blogNameDescription = document.querySelector('.blog-create-input-description');
    if (!expression.test(blogNameInput.value)) {
        blogNameDescription.innerHTML = "블로그 이름에는 영문과 숫자만 입력할 수 있습니다.";
        return false;
    } else {
        blogNameDescription.innerHTML = '문자 사이에 띄어쓰기는 "_ (Underscore)" 처리됩니다.';
        return true;
    }
}

function checkForDuplicateName() {
    if (!informBlogNameUndefined(blogNameInput)) return false;
    if (!checkRegularExpression()) return activeAlertContainer('nameRegExp');

    fetch(`api/blog?name=${blogNameInput.value}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            if (response.status === 200 || response.status === 404) {
                return response.status;
            }
            return response.json();
        })
        .then((data) => {
            if (data === 200) {
                return existSameBlogName(blogNameInput);
            } else if (data === 404) {
                return doseNotExistBlogName(blogNameInput);
            }
        }).catch((error) => console.error(error));
}

function existSameBlogName(blogNameInput) {
    activeAlertContainer('blogNameExist');
    blogNameInput.setAttribute('data_result', 'fail');
    return false;
}

function doseNotExistBlogName(blogNameInput) {
    activeAlertContainer('blogNameNotExist');
    blogNameInput.setAttribute('data_result', 'success');
    return true;
}

function postBlogInfo() {
    if (!informBlogNameUndefined(blogNameInput)) return false;
    if (blogNameInput.getAttribute('data_result') === 'fail') return activeAlertContainer('blogNameCheck');
    const blogInfo = getValues();

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
                window.location = `/${blogInfo.name}`;
            } else {
                activeAlertContainer();
                document.querySelector('.alert-message').innerHTML = `서버 오류 (status: ${data.status})`;
            }
        }).catch((error) => console.error(error));
}

function getValues() {
    const blogName = document.querySelector('#blog-create-name').value;
    const replacedBlogName = blogName.replace(/ /gi, '_');
    const blogIntroduce = document.querySelector('#blog-create-introduce').value;
    const blogImage = document.querySelector('.blog-create-image').getAttribute('data');
    const blogMembers = getMembers();

    return new Blog(replacedBlogName, blogIntroduce, blogImage, blogMembers);
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

function informBlogNameUndefined(blogNameInput) {
    if (blogNameInput.value === '') {
        activeAlertContainer('blogNameNotValue');
        return false;
    }

    return true;
}
