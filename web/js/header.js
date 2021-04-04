const noticeIcon = document.querySelector('.header-notice');
const noticeList = document.querySelector('.header-notice-list');

noticeIcon.addEventListener('click', function () {
    if (!noticeList.classList.contains('active')) {
        noticeList.classList.add('active');
        headerResize();
    } else {
        noticeList.classList.remove('active');
    }
});

const userInfo = document.querySelector('.user-info');
const userInfoList = document.querySelector('.user-info-list');

userInfo.addEventListener('click', function () {
    if (!userInfoList.classList.contains('active')) {
        userInfoList.classList.add('active');
    } else {
        userInfoList.classList.remove('active');
    }
});

function headerResize() {
    const userInfoFrame = userInfo.getBoundingClientRect();
    userInfoList.style.width = userInfoFrame.width;

    const noticeIconFrame = noticeIcon.getBoundingClientRect();
    const noticeListFrame = noticeList.getBoundingClientRect();
    noticeList.style.left = (noticeIconFrame.left - noticeListFrame.width) + noticeIconFrame.width;
}

window.addEventListener("load", function () {
    headerResize();
});

let resizeTimer = null;
window.addEventListener("resize", function () {
    clearTimeout(null);
    resizeTimer = setTimeout(function () {
        headerResize();
    }, 300);
});

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