document.querySelector('.member-invite-link-copy').addEventListener('click', function () {
    const copyText = document.querySelector('#member-invite-link');
    copyText.focus();
    copyText.select();
    copyText.setSelectionRange(0, copyText.value.length);
    document.execCommand("Copy");
    activeAlertContainer('memberInviteLinkCopy');
});

document.querySelector('.member-invite-email-button').addEventListener('click', function () {
    findUser();
});

let inviteMemberData;

function findUser() {
    const email = document.querySelector('.member-invite-email');

    if (!checkCurrentUser(email.value)) return informToNotRegisterOneself(email);
    if (!checkValidEmail(email.value)) return activeAlertContainer('notValidEmail');

    // url 수정함 테스트용
    fetch(`http://localhost:8080/api/user/?email=${email.value}`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then((response) => {
            if (response.status === 200) {
                return response.json();
            }
        })
        .then((data) => {
            inviteMemberData = data;
            return confirmUserList(inviteMemberData, email);
        })
        .catch(error => console.error(error));

}

function confirmUserList(data, emailInput) {
    const isOverLap = findOverlapList(data.email);

    if (isOverLap) {
        activeAlertContainer('memberOverlap');
        emailInput.value = '';
    } else {
        displayRegisterAlert();

        const alertContainer = document.querySelector('.alert-container');
        const alertMessage = alertContainer.querySelector('.alert-message');
        alertMessage.innerHTML = `${inviteMemberData.name}님을 등록하시겠습니까?`;

        const register = alertContainer.querySelector('.alert-register');
        register.addEventListener('click', function () {
            return clickToRegisterUser();
        });

        const cancel = alertContainer.querySelector('.alert-cancel');
        cancel.addEventListener('click', function () {
            return hideAlertContainer();
        });
    }
}

function findOverlapList(findEmail) {
    const memberList = document.querySelector('.member-list');
    const memberItems = memberList.children;

    let isOverlap = false;
    [...memberItems].forEach((element) => {
        const existEmail = element.querySelector('.member-list-item-email').innerHTML;
        if (existEmail === findEmail) return isOverlap = true;
    });

    return isOverlap;
}

function clickToRegisterUser() {
    appendMemberList(inviteMemberData);
    hideAlertContainer();
    document.querySelector('.member-invite-email').value = '';
}

function appendMemberList(data) {
    let html = `<li class="member-list-item" data-member-id="${data.id}" data-member-nickname="${data.nickName}" data-member-role="INVITING" >
                    <img src="${data.imageUrl}" alt="${data.name}-image" class="member-list-item-image">
                    <span class="member-list-item-name">${data.name}</span>
                    <span class="member-list-item-email">${data.email}</span>
                    <div class="member-list-item-access-level">
                        <i class="fas fa-caret-down member-list-item-access-level-control"></i>
                        <span class="member-list-item-access-level-text">초대중</span>
                    </div>
                    <div class="member-list-item-access-level-setting">
                        <span class="member-list-item-access-level-setting-administrator">관리자</span>
                        <span class="member-list-item-access-level-setting-member">멤버</span>
                    </div>
                </li>`;

    const memberList = document.querySelector('.member-list');
    memberList.innerHTML += html;
}

const memberList = document.querySelector('.member-list');

function clickAccessLevel() {
    const accessLevel = memberList.querySelectorAll('.member-list-item-access-level');
    [].forEach.call(accessLevel, function (element) {
        if (!(element.children.item(1).innerHTML === '초대중')) {
            element.addEventListener('click', function (event) {
                event.preventDefault();
                let accessLevelSetting = element.nextElementSibling;
                let accessLevelSettingActive = accessLevelSetting.classList.toggle('active');
            });
        }
    })
}

clickAccessLevel();

function clickAccessLevelSetting() {
    const accessLevelSetting = memberList.querySelectorAll('.member-list-item-access-level-setting');
    [].forEach.call(accessLevelSetting, function (element) {
        [].forEach.call(element.children, function (item) {
            item.addEventListener('click', function (el) {
                el.preventDefault();
                return alertMemberAccessLevelChange(el.target);
            });
        });
    });
}

clickAccessLevelSetting();

function alertMemberAccessLevelChange(target) {
    const memberListItem = target.closest('.member-list-item');
    const memberName = memberListItem.querySelector('.member-list-item-name').innerHTML;

    displayConfirmAlert();

    const alertContainer = document.querySelector('.alert-container');
    const alertMessage = alertContainer.querySelector('.alert-message');
    alertMessage.innerHTML = `${memberName}님을 ${target.innerHTML}로 변경하시겠습니까?`;

    const confirm = alertContainer.querySelector('.alert-confirm');
    confirm.addEventListener('click', function () {
        return clickToAccessLevelRegister(memberListItem, target);
    });

    document.querySelector('.alert-cancel').classList.add('display');
    const cancel = alertContainer.querySelector('.alert-cancel');
    cancel.addEventListener('click', function () {
        return hideAlertContainer();
    });
}


function clickToAccessLevelRegister(memberListItem, target) {
    memberListItem.querySelector('.member-list-item-access-level-text').innerHTML = target.innerHTML;
    memberListItem.querySelector('.member-list-item-access-level-setting').classList.remove('active');
    hideAlertContainer();
}


// div 변경 감지
let observer = new MutationObserver(function (mutations) {
    clickAccessLevel();
    clickAccessLevelSetting();
});

let config = {attributes: true, childList: true, characterData: true};
observer.observe(memberList, config);
//

document.querySelector('#buttons-save').addEventListener('click', function () {
    postBlogInfoEdit();
});

function postBlogInfoEdit() {
    let formData = new FormData();
    const blogId = document.querySelector('.blog-info').dataset.blogId;
    const blogName = document.querySelector('.blog-info-texts-name').value;
    const blogIntroduce = document.querySelector('.blog-info-texts-introduction').value;
    let blogImage = document.querySelector('#blog-image-input').files[0];

    if (blogImage === undefined) {
        blogImage = document.querySelector('.blog-image').getAttribute('src');
    }

    formData.append('name', blogName);
    formData.append('introduce', blogIntroduce);
    formData.append('image', blogImage);

    const blogMembers = getBlogMembers();

    for (let index = 0; index < blogMembers.length; index += 1) {
        formData.append('members[' + index + '].id', blogMembers[index].id);
        formData.append('members[' + index + '].name', blogMembers[index].name);
        formData.append('members[' + index + '].email', blogMembers[index].email);
        formData.append('members[' + index + '].nickname', blogMembers[index].nickName);
        formData.append('members[' + index + '].role', blogMembers[index].role);

    }

    fetch(`/blogs/${blogId}`, {
        method: 'POST',
        headers: {
            'Access-Control-Allow-Origin': '*',
        },
        mode: 'no-cors',
        body: formData,
    })
        .then((response) => {
            if (response.status === 200) {
                return alertSuccessPostBlogInfoEdit();
            } else {
                return activeAlertContainer('blogNotPost');
            }
        })
        .catch((error) => console.error(error));

}

function getBlogMembers() {
    const memberArray = [];
    const blogMembers = document.querySelectorAll('.member-list-item');
    [...blogMembers].forEach(element => {
        const userId = `${element.dataset.memberId}`;
        const userName = `${element.querySelector('.member-list-item-name').innerHTML}`;
        const userEmail = `${element.querySelector('.member-list-item-email').innerHTML}`;
        const userNickname = `${element.dataset.memberNickname}`;
        const userRole = `${element.dataset.memberRole}`;

        const memberInfo = new User(userId, userName, userEmail, userNickname, userRole);

        memberArray.push(memberInfo);
    }, 0);

    return memberArray;
}

function alertSuccessPostBlogInfoEdit() {
    activeAlertContainer();

    const alertMessage = document.querySelector('.alert-message');
    alertMessage.innerHTML = '저장되었습니다';

    const confirm = document.querySelector('.alert-confirm');
    confirm.addEventListener('click', function () {
        return window.location.reload();
    })
}

document.querySelector('#buttons-reset').addEventListener('click', function () {
    window.location.reload();
});
