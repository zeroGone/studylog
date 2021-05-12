document.querySelector('.member-invite-link-copy').addEventListener('click', function () {
    const copyText = document.querySelector('#member-invite-link');
    copyText.focus();
    copyText.select(); // 모든 텍스트 선택
    copyText.setSelectionRange(0, copyText.value.length); // select 오류날 수 있으니 이거 사용
    document.execCommand("Copy"); // 편집가능한 섹션의 부분에 지정된명령 실행 (copy)니 복사기능
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

    // url 수정함 일단 테스트용
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
            inviteMemberAlert(inviteMemberData, email)
        })
        .catch(error => console.error(error));

}

function inviteMemberAlert(userInfoData, emailInput) {
    confirmUserList(userInfoData, emailInput);
}

function confirmUserList(userInfoData, emailInput) {
    const isOverLap = findOverlapList(userInfoData.email);

    if (isOverLap) {
        activeAlertContainer('memberOverlap');
        emailInput.value = '';
    } else {
        displayRegisterAlert();
        const alertContainer = document.querySelector('.alert-container');
        const alertMessage = alertContainer.querySelector('.alert-message');
        alertMessage.innerHTML = `${userInfoData.name}님을 등록하시겠습니까?`;
        const register = alertContainer.querySelector('.alert-register');
        register.addEventListener('click', clickToRegisterUser);
        const cancel = alertContainer.querySelector('.alert-cancel');
        cancel.addEventListener('click', function () {
            hideAlertContainer('member');
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
    let html = `<div class="member-list-item">
                    <img src="${data.imageUrl}" alt="${data.name}-image" class="member-list-item-image">
                    <span class="member-list-item-name">${data.name}</span>
                    <span class="member-list-item-email">${data.email}</span>
                    <span class="member-list-item-access-level">
                        <i class="fas fa-caret-down member-list-item-access-level-control"></i>
                     초대중
                    </span>
                </div>`;

    const memberList = document.querySelector('.member-list');
    memberList.innerHTML += html;
}


