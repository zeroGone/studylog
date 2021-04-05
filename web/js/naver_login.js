const NAVER_LOGIN_INFO = {
    API_URL: "login",
    INIT_OPTIONS: {
        client_id: 'Client ID',
        client_secret: 'Client Secret',
        callback_url: "http://localhost:8080",
        service_url: "http://localhost:8080"
    },
}

var naver_id_login = new naver_id_login(NAVER_LOGIN_INFO.INIT_OPTIONS.client_id, NAVER_LOGIN_INFO.INIT_OPTIONS.service_url);
var state = naver_id_login.getUniqState();

naver_id_login.setButton("white", 2,40);
naver_id_login.setDomain("http://localhost:8080");
naver_id_login.setState(state);
naver_id_login.setPopup();
naver_id_login.init_naver_id_login();

// 네이버 사용자 프로필 조회
naver_id_login.get_naver_userprofile("submit()");
function submit() {
    fetch("login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify({
            "name": naver_id_login.getProfileData('name'),
            "email": naver_id_login.getProfileData('email'),
            "imgUrl": naver_id_login.getProfileData('profile_image')
        })
    }).then(response => {
        if (isLoginSuccess(response)) {
            window.location.pathname = "mypage";
        } else {
            window.location.pathname = "signup";
        }
    }).catch(error => console.log(error));
}