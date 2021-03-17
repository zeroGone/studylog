class user {
    constructor(id, name, email, nickName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }
}

class blog {
    constructor(name, introduce, imgUrl, members) {
        this.name = name;
        this.introduce = introduce;
        this.imgUrl = imgUrl;
        this.members = members;
    }
}

let blogInfo;

const userSearchButton = document.querySelector('.blog-create-member-invite-search').addEventListener('click', getUserInfo);
const blogCreateSaveButton = document.querySelector('.blog-create-save-button').addEventListener('click', postBlogInfo);

document.querySelector('#blog-create-member').setAttribute('onkeypress', 'if( event.keyCode == 13 ){getUserInfo();}');;

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

function appendUserList(data, emailInput) {
    const isOverlap = findOverlapList(data.id);

    if (isOverlap) {
        alert('이미 등록된 유저입니다.');
    } else {
        const isRegister = confirm(`${data.name}님을 등록하시겠습니까?`);
        if (isRegister) {
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

            emailInput.value = '';
        }
    }
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
        if (response.ok) {
            console.log('success')
        } else {
            console.log('failure')
        }
        return response.json();
    }).then(data => {
        console.log(data);
        if (data.errorMessage === "검색 결과 없음") {
            alert(data.errorMessage);
        } else {
            appendUserList(data, emailInput);
        }
    }).catch(error => {
        console.log('error')
    })
}

function postBlogInfo() {
    getValues();
    console.log(blogInfo);
    console.log(JSON.stringify(blogInfo));
    fetch("api/blog", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(blogInfo),
    }).then((response) => console.log(response))
}

function informInputUndefined(key, value) {
    if (value === '') {
        if (key === 'name') {
            alert('블로그 이름을 입력해주세요.');
            return false;
        }
        return false;
    }

    return true;
}

function getValues() {
    let blogInfoObj = {
        name: document.querySelector('#blog-create-name').value,
        introduce: document.querySelector('#blog-create-introduce').value,
        imgUrl: document.querySelector('.blog-create-image').getAttribute('src'),
        members: getBlogUsers()
    };

    let isInput = true;
    for (let key in blogInfoObj) {
        isInput = informInputUndefined(key, blogInfoObj[key]);
        if (!isInput) {
            break;
        }
    }

    blogInfo = new blog(blogInfoObj.name, blogInfoObj.introduce, blogInfoObj.imgUrl, blogInfoObj.members);

    return blogInfo;
}

function getBlogUsers() {
    const blogUserArray = [];
    const blogUsers = document.querySelectorAll('.blog-create-member-item');

    blogUsers.forEach((element) => {
        const userId = element.children.item(1).children.item(0).innerHTML;
        const userName = element.children.item(1).children.item(1).innerHTML;
        const userEmail = element.children.item(1).children.item(2).innerHTML;
        const userNickname = element.children.item(1).children.item(3).innerHTML;

        const memberInfo = new user(userId, userName, userEmail, userNickname);

        blogUserArray.push(memberInfo);
    }, 0);

    return blogUserArray;
}