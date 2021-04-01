const noticeIcon = document.querySelector('.header-notice');
const noticeList = document.querySelector('.header-notice-list');

noticeIcon.addEventListener('click', function (event) {
    if (!noticeList.classList.contains('active')) {
        noticeList.classList.add('active');
        headerResize();
    } else {
        noticeList.classList.remove('active');
    }
});

const userInfo = document.querySelector('.user-info');
const userInfoList = document.querySelector('.user-info-list');
userInfoList.style.width = userInfo.offsetWidth;

userInfo.addEventListener('click', function (event) {
    if (!userInfoList.classList.contains('active')) {
        userInfoList.classList.add('active');
    } else {
        userInfoList.classList.remove('active');
    }
});

const userInfoFrame = userInfo.getBoundingClientRect();
noticeIcon.style.left = (userInfoFrame.left - 70) + 'px';

const noticeIconFrame = noticeIcon.getBoundingClientRect();
noticeList.style.left = (noticeIconFrame.right - noticeList.offsetWidth) + 'px';

window.addEventListener('resize', headerResize);

function headerResize() {
    const userInfoFrame = userInfo.getBoundingClientRect();
    noticeIcon.style.left = (userInfoFrame.left - 70) + 'px';

    const noticeList = document.querySelector('.header-notice-list');
    const noticeIconFrame = noticeIcon.getBoundingClientRect();
    noticeList.style.left = (noticeIconFrame.right - noticeList.offsetWidth) + 'px';
}

document.querySelector(".user-info-item-logout").addEventListener("click", logout);

function logout() {
    fetch("api/logout", {
        method: "POST"
    }).then(response => {
        if (response.status === 200) {
            window.location.pathname = "/";
        } else {
            alert("로그아웃 실패! " + response.body);
        }
    }).catch(error => alert("로그아웃 실패! " + error));
}