class User {
    constructor(id, name, email, nickName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }
}

class Blog {
    constructor(name, introduce, imgUrl, members) {
        this.name = name;
        this.introduce = introduce;
        this.imgUrl = imgUrl;
        this.members = members;
    }
}

let currentData;
let focusLocation;

const userSearchButton = document.querySelector('.blog-create-member-invite-search').addEventListener('click', getUserInfo);
document.querySelector('#blog-create-member').setAttribute('onkeypress', 'if( event.keyCode == 13 ){getUserInfo();}');

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
    const memberList = document.querySelector('.blog-create-member-list');
    const memberItems = memberList.children;

    if (memberItems.length === 0) {
        return false;
    } else {
        for (let i = 0; i < memberItems.length; i++) {
            const id = memberItems[i].querySelector('.blog-create-member-id').innerHTML;
            if (parseInt(id) === findId) {
                return true;
            }
        }
    }

    return false;
}


function appendUserList(data) {
    const ul = document.querySelector('.blog-create-member-list');

    const li = createElement('li', 'blog-create-member-item');
    const img = createElement('img', 'blog-create-member-img', '', 'src', '../img/user-default/1.png');
    const div = createElement('div', 'blog-create-member-texts');

    const id = createElement('span', 'blog-create-member-id', `${data.id}`);
    const name = createElement('span', 'blog-create-member-name', `${data.name}`);
    const email = createElement('span', 'blog-create-member-email', `${data.email}`);
    const nickName = createElement('span', 'blog-create-member-nickName', `${data.nickName}`);

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
        document.querySelector('#blog-create-member').value = '';
    } else {
        displayRegisterAlert();

        const alertContainer = document.querySelector('.alert-container');
        const alertMessage = alertContainer.querySelector('.alert-message');
        alertMessage.innerHTML = `${data.name}님을 등록하시겠습니까?`;
        const register = alertContainer.querySelector('.alert-register');
        register.addEventListener('click', clickToRegisterUser);
        const cancel = alertContainer.querySelector('.alert-cancel');
        cancel.addEventListener('click', hideAlertContainer);
    }
}

function clickToRegisterUser() {
    appendUserList(currentData);
    hideAlertContainer();
    hideAlertRegister();
    document.querySelector('#blog-create-member').value = '';
}

function getUserInfo() {
    const emailInput = document.querySelector('#blog-create-member');

    fetch(`http://localhost:8080/api/user?email=${emailInput.value}`, {
        method: 'GET',
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json",
        },
        mode: 'no-cors'
    }).then(response => {
        if (response.status === 400) {
            return response.status;
        }
        return response.json();
    }).then(data => {
        if (data.errorMessage === "검색 결과 없음") {
            activeAlertContainer(data.errorMessage);
        } else if (data === 400) {
            informToNotRegisterOneself();
        } else {
            currentData = data;
            confirmUserList(currentData);
        }
    }).catch(error => {
        console.error(error);
    })
}

function activeAlertContainer(type) {
    displayConfirmAlert();

    const alertMessage = document.querySelector('.alert-message');
    if (type === 'blogNameNotValue') {
        alertMessage.innerHTML = '블로그 이름을 입력해주세요.';
        focusLocation = 'blogName';
    } else if (type === '검색 결과 없음') {
        alertMessage.innerHTML = '해당 유저가 존재하지 않습니다.';
        focusLocation = 'member';
    } else if (type === 'memberOverlap') {
        alertMessage.innerHTML = '이미 등록된 유저입니다.';
        focusLocation = 'member';
    } else if (type === 'memberOneself') {
        alertMessage.innerHTML = '본인은 등록할 수 없습니다.';
        focusLocation = 'member';
    } else if (type === 'nameRegExp') {
        alertMessage.innerHTML = '블로그 이름 입력 주의사항을 확인해주세요.';
        focusLocation = 'blogName';
    } else if (type === 'blogNameExist') {
        alertMessage.innerHTML = '이미 존재하는 블로그 이름입니다.';
        focusLocation = 'blogName';
    } else if (type === 'blogNameNotExist') {
        alertMessage.innerHTML = '사용 가능한 블로그 이름입니다.';
        focusLocation = 'introduce';
    } else if (type === 'blogNameCheck') {
        alertMessage.innerHTML = '블로그 이름 중복확인을 해주세요.';
        focusLocation = 'blogName';
    } else if (type === 'image') {
        alertMessage.innerHTML = 'gif, jpg, png 파일만 선택해 주세요.';
        focusLocation = 'image';
    }

    const confirm = document.querySelector('.alert-confirm');
    confirm.setAttribute('onkeypress', 'if( event.keyCode == 13 ){hideAlertContainer();}');
    confirm.addEventListener('click', hideAlertContainer);
    confirm.focus();
}

function displayRegisterAlert() {
    displayAlertContainer();
    displayAlertRegister();
    hideAlertConfirm();
    displayAlertCancel();
}

function displayConfirmAlert() {
    displayAlertContainer();
    hideAlertRegister();
    displayAlertConfirm();
    hideAlertCancel();
}

function displayAlertContainer() {
    const alertContainer = document.querySelector('.alert-container');
    alertContainer.classList.add('alert');
}

function hideAlertContainer() {
    const alertContainer = document.querySelector('.alert-container');
    alertContainer.classList.remove('alert');

    if (focusLocation === 'blogName') {
        document.querySelector('#blog-create-name').focus();
    } else if (focusLocation === 'member') {
        document.querySelector('#blog-create-member').focus();
    } else if (focusLocation === 'introduce') {
        document.querySelector('#blog-create-introduce').focus();
    } else if (focusLocation === 'image') {
        document.querySelector('.blog-create-images-edit-container').focus();
    }
}

function displayAlertConfirm() {
    const alertConfirm = document.querySelector('.alert-confirm');
    alertConfirm.classList.add('display');
}

function hideAlertConfirm() {
    const alertConfirm = document.querySelector('.alert-confirm');
    alertConfirm.classList.remove('display');
}

function displayAlertRegister() {
    const alertRegister = document.querySelector('.alert-register');
    alertRegister.classList.add('display');
}

function hideAlertRegister() {
    const alertRegister = document.querySelector('.alert-register');
    alertRegister.classList.remove('display');
}

function displayAlertCancel() {
    const alertCancel = document.querySelector('.alert-cancel');
    alertCancel.classList.add('display');
}

function hideAlertCancel() {
    const alertCancel = document.querySelector('.alert-cancel');
    alertCancel.classList.remove('display');
}

function informToNotRegisterOneself() {
    return activeAlertContainer('memberOneself');
}