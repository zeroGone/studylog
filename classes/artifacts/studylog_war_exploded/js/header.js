const notice = document.querySelector('.header-notice');
const noticeList = document.querySelector('.header-notice-list');

notice.addEventListener('click', function (event) {
    if (!noticeList.classList.contains('active')) {
        noticeList.classList.add('active');
    } else {
        noticeList.classList.remove('active');
    }
});

const userInfo = document.querySelector('.user-info');
const userInfoList = document.querySelector('.user-info-list');

userInfo.addEventListener('click', function (event) {
    if (!userInfoList.classList.contains('active')) {
        userInfoList.classList.add('active');
    } else {
        userInfoList.classList.remove('active');
    }
});