const blogNameInput = document.querySelector('#blog-name-input');
blogNameInput.setAttribute('onkeypress', 'if( event.keyCode == 13 ){checkForDuplicateName();}');
blogNameInput.addEventListener('keyup', setAttributeOverlapToFalse);

const blogNameCheckButton = document.querySelector('.blog-name-overlap-check');
blogNameCheckButton.addEventListener('click', checkForDuplicateName);

const blogCreateButton = document.querySelector('.blog-save');
blogCreateButton.addEventListener('click', postBlogInfo);

function setAttributeOverlapToFalse() {
    return blogNameInput.setAttribute('isCheckOverlap', 'false');
}

function checkForDuplicateName() {
    if (!informBlogNameUndefined(blogNameInput)) return activeAlertContainer('blogNameNotValue');
    const replacedName = blogNameInput.value.replace(/ /gi, '_');
    fetch(`api/blog?name=${replacedName}`, {
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
                return existSameBlogName();
            } else if (data === 404) {
                return doseNotExistBlogName();
            }
        }).catch((error) => console.error(error));
}

function existSameBlogName() {
    activeAlertContainer('blogNameExist');
    blogNameInput.setAttribute('isCheckOverlap', 'false');
    return false;
}

function doseNotExistBlogName() {
    activeAlertContainer('blogNameNotExist');
    blogNameInput.setAttribute('isCheckOverlap', 'true');
    return true;
}

function postBlogInfo() {
    if (!informBlogNameUndefined(blogNameInput)) return activeAlertContainer('blogNameNotValue');
    if (blogNameInput.getAttribute('isCheckOverlap') === 'false') return activeAlertContainer('blogNameCheck');

    const formData = new FormData();
    const name = document.querySelector('#blog-name-input').value;
    const replacedName = name.replace(/ /gi, '_');
    const introduce = document.querySelector('#blog-introduce-input').value;
    const image = document.querySelector('#blog-image-input').files[0];

    formData.append('name', replacedName);
    formData.append('introduce', introduce);
    if (image) {
        formData.append('image', image);
    }

    const members = getMembers();
    for (let index = 0; index < members.length; index += 1) {
        formData.append('members[' + index + '].id', members[index].id);
        formData.append('members[' + index + '].name', members[index].name);
        formData.append('members[' + index + '].email', members[index].email);
        formData.append('members[' + index + '].nickname', members[index].nickName);
    }

    fetch('api/blog', {
        method: 'POST',
        body: formData,
    })
        .then(response => response)
        .then(data => {
            if (data.status === 201) {
                window.location = `/${replacedName}`;
            } else {
                activeAlertContainer();
                document.querySelector('.alert-message').innerHTML = `서버 오류 (status: ${data.status})`;
            }
        }).catch((error) => console.error(error));
}

function getMembers() {
    const userArray = [];
    const blogUsers = document.querySelectorAll('.blog-member-item');
    [...blogUsers].forEach(element => {
        const userId = element.children.item(1).children.item(0).innerHTML;
        const userName = element.children.item(1).children.item(1).innerHTML;
        const userEmail = element.children.item(1).children.item(2).innerHTML;
        const userNickname = element.children.item(1).children.item(3).innerHTML;

        const memberInfo = new User(userId, userName, userEmail, userNickname);

        userArray.push(memberInfo);
    }, 0);

    return userArray;
}

function informBlogNameUndefined(blogNameInput) {
    return blogNameInput.value !== '';
}
