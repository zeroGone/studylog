class User {
    constructor(id, name, email, nickName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }
}

let userInfoData;

const userSearchButton = document.querySelector('.blog-member-search');
userSearchButton.addEventListener('click', getUserInfo);
document.querySelector('#blog-member-input').setAttribute('onkeypress', 'if( event.keyCode == 13 ){getUserInfo();}');

function createElement(tagName, className, text, attributeNames, attributeValues) {
    const element = document.createElement(tagName);

    if (className !== undefined) {
        if (typeof className === 'object') {
            for (let index in className) {
                element.classList.add(className[index]);
            }
        } else {
            element.classList.add(className);
        }
    }

    if (text !== undefined) {
        element.innerText = text;
    }

    if (attributeNames !== undefined && attributeValues !== undefined) {
        if (typeof attributeNames === 'object') {
            for (let index in attributeNames) {
                element.setAttribute(attributeNames[index], attributeValues[index]);
            }
        } else {
            element.setAttribute(attributeNames, attributeValues);
        }

    }

    return element;
}

function findOverlapList(findId) {
    const memberList = document.querySelector('.blog-member-list');
    const memberItems = memberList.children;

    let isOverlap = false;
    [...memberItems].forEach((element) => {
        const id = parseInt(element.querySelector('.blog-member-item-id').innerHTML);
        if (id === findId) return isOverlap = true;
    });
    return isOverlap;
}


function appendUserList(data) {
    const ul = document.querySelector('.blog-member-list');

    const li = createElement('li', 'blog-member-item');
    const img = createElement('img', 'blog-member-item-img', '', 'src', '../img/user-default/1.png');
    const div = createElement('div', 'blog-member-item-info');

    const id = createElement('span', 'blog-member-item-id', `${data.id}`);
    const name = createElement('span', 'blog-member-item-name', `${data.name}`);
    const email = createElement('span', 'blog-member-item-email', `${data.email}`);
    const nickName = createElement('span', 'blog-member-item-nickName', `${data.nickName}`);

    li.appendChild(img);
    li.appendChild(div);

    div.appendChild(id);
    div.appendChild(name);
    div.appendChild(email);
    div.appendChild(nickName);

    ul.appendChild(li);
}

function confirmUserList(data) {
    const isOverlap = findOverlapList(data.id);

    if (isOverlap) {
        activeAlertContainer('memberOverlap');
        document.querySelector('#blog-member-input').value = '';
    } else {
        displayRegisterAlert();

        const alertContainer = document.querySelector('.alert-container');
        const alertMessage = alertContainer.querySelector('.alert-message');
        alertMessage.innerHTML = `${data.name}님을 등록하시겠습니까?`;
        const register = alertContainer.querySelector('.alert-register');
        register.addEventListener('click', clickToRegisterUser);
        const cancel = alertContainer.querySelector('.alert-cancel');
        cancel.addEventListener('click', function () {
            hideAlertContainer('member');
        });
    }
}

function clickToRegisterUser() {
    appendUserList(userInfoData);
    hideAlertContainer('member');
    document.querySelector('#blog-member-input').value = '';
}

function getUserInfo() {
    const email = document.querySelector('#blog-member-input');

    if (!checkCurrentUser(email.value)) return informToNotRegisterOneself(email);
    if (!checkValidEmail(email.value)) return activeAlertContainer('notValidEmail');

    fetch(`http://localhost:8080/api/user?email=${email.value}`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
        }
    }).then(response => response.json())
        .then(data => {
            if (data.statusCode === 404) {
                activeAlertContainer('userNotExist');
            } else if (data.id && data.id !== 0) {
                userInfoData = data;
                confirmUserList(userInfoData);
            }
        }).catch(error => console.error(error))
}

function checkValidEmail(email) {
    const emailExpression = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    return emailExpression.test(email);
}

function checkCurrentUser(email) {
    const currentUserEmail = document.querySelector('.user-info-item-email').innerHTML;
    return currentUserEmail !== email;
}

let focusLocation;

function activeAlertContainer(type) {

    displayConfirmAlert();
    const alertMessage = document.querySelector('.alert-message');

    if (type === 'blogNameNotValue') {
        alertMessage.innerHTML = '블로그 이름을 입력해주세요.';
        focusLocation = 'blogName';
    } else if (type === 'blogNameCheck') {
        alertMessage.innerHTML = '블로그 이름 중복확인을 해주세요.';
        focusLocation = 'blogName';
    } else if (type === 'nameRegExp') {
        alertMessage.innerHTML = '블로그 이름에는 영문과 숫자만 입력할 수 있습니다.';
        focusLocation = 'blogName';
    } else if (type === 'userNotExist') {
        alertMessage.innerHTML = '해당 유저가 존재하지 않습니다.';
        focusLocation = 'member';
    } else if (type === 'memberOverlap') {
        alertMessage.innerHTML = '이미 등록된 유저입니다.';
        focusLocation = 'member';
    } else if (type === 'registerNotOneself') {
        alertMessage.innerHTML = '본인은 등록할 수 없습니다.';
        focusLocation = 'member';
    } else if (type === 'blogNameExist') {
        alertMessage.innerHTML = '이미 존재하는 블로그 이름입니다.';
        focusLocation = 'blogName';
    } else if (type === 'blogNameNotExist') {
        alertMessage.innerHTML = '사용 가능한 블로그 이름입니다.';
        focusLocation = 'introduce';
    } else if (type === 'imageCheckFormat') {
        alertMessage.innerHTML = 'gif, jpg, png 파일만 선택해 주세요.';
        focusLocation = 'image';
    } else if (type === 'notPostImage') {
        alertMessage.innerHTML = '이미지를 저장할 수 없습니다. 잠시후 다시 시도해 주세요.';
        focusLocation = 'image';
    } else if (type === 'notValidEmail') {
        alertMessage.innerHTML = '올바른 이메일 형식을 입력해 주세요.';
        focusLocation = 'member';
    }

    const confirm = document.querySelector('.alert-confirm');
    confirm.focus();
    confirm.addEventListener('click', function () {
        return hideAlertContainer(focusLocation);
    });
    confirm.setAttribute('onkeypress', 'if( event.keyCode == 13 ){ return hideAlertContainer(focusLocation); }');
}

function displayRegisterAlert() {
    document.querySelector('.alert-container').classList.add('alert');
    document.querySelector('.alert-register').classList.add('display');
    document.querySelector('.alert-confirm').classList.remove('display');
    document.querySelector('.alert-cancel').classList.add('display');
}

function displayConfirmAlert() {
    document.querySelector('.alert-container').classList.add('alert');
    document.querySelector('.alert-register').classList.remove('display');
    document.querySelector('.alert-confirm').classList.add('display');
    document.querySelector('.alert-cancel').classList.remove('display');
}

function hideAlertContainer(location) {
    document.querySelector('.alert-container').classList.remove('alert');

    if (location === 'image') {
        document.querySelector('.blog-images-edit-button').focus();
    } else if (location === 'blogName') {
        document.querySelector('#blog-name-input').focus();
    } else if (location === 'introduce') {
        document.querySelector('#blog-introduce-input').focus();
    } else if (location === 'member') {
        document.querySelector('#blog-member-input').focus();
    }
}

function informToNotRegisterOneself(email) {
    email.value = '';
    return activeAlertContainer('registerNotOneself');
}