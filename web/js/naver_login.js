/*
/!*const NAVER_LOGIN_INFO = {
    API_URL: "login",
    INIT_OPTIONS: {
        client_id: 'gWo2HStBsDicQk1vH8VV',
        client_secret: 'RByMBHe5hh',
        callback_url: 'http://localhost:8080',
        service_url: 'http://localhost:8080'
    },
}*!/

// document.querySelector("#naver_id_login").addEventListener('click', function () {
/!*var naver_id_login = new naver_id_login("T0eCoHKw1YK3phu9brZX", "http://localhost:8080");
var state = naver_id_login.getUniqState();
// naver_id_login.setButton("white", 2,40);
naver_id_login.setDomain("http://localhost:8080");
naver_id_login.setState(state);
naver_id_login.setPopup();
naver_id_login.init_naver_id_login();

console.log(naver_id_login);
console.log(state);*!/
// });


/!*let naverLogin = new naver.LoginWithNaverId(
    {
        clientId: "T0eCoHKw1YK3phu9brZX",
        callbackUrl: "http://localhost:8080",
        isPopup: false,
        loginButton: {color: "green", type: 3, height: 60},
        callbackHandle: true
    }
);*!/

const { naver } = window;

document.querySelector('#naverIdLogin').addEventListener('click', function () {
    Login();
});

const Login = () => {
    const naver = Naver();
    UserProfile(naver);
}


const Naver = () => {
    const naverLogin = new naver.LoginWithNaverId({
        clientId: "T0eCoHKw1YK3phu9brZX",
        callbackUrl: "http://localhost:8080/",
        isPopup: false,
        loginButton: {color: "green", type: 1, height: 30} ,
        callbackHandle: true
    });
    naverLogin.init();

    return naverLogin;
}

const UserProfile = (naverLogin) => {

    console.log(naverLogin);
    console.log(naverLogin.user);

    window.location.href.includes('access_token') && GetUser();
    function GetUser() {
        const location = window.location.href.split('=')[1];
        const token = location.split('&')[0];
        console.log("token: ", token);
        fetch(`/login` , {
            method: "POST",
            headers : {
                "Content-type" : "application/json",
                "Authorization": token
            },
            body: JSON.stringify({
                "name": naverLogin.user.getName(),
                "email": naverLogin.user.getEmail(),
                "imageUrl": naverLogin.user.getProfileImage()
            })
        })
            .then(res => {
                console.log(res);
                res.json()
            })
            .then(res => {
                localStorage.setItem("access_token", res.token);
                setUserData({
                    nickname : res.nickname,
                    image : res.image
                })
            })
            .catch(err => console.log("err : ", err));
    }
}


/!*
function getLoginStatus() {
    naverLogin.getLoginStatus(function (status) {
        if (status) {
            let email = naverLogin.user.getEmail();
            if (email === undefined || email === null) {
                alert("이메일 정보는 필수사항입니다. 정보제공을 동의해주세요.");
                naverLogin.reprompt();
                return;
            }

            // window.location.pathname = "mypage";

            console.log(naverLogin);
            console.log(naverLogin.user);
        } else {
            console.error("callback 실패");
        }
    });
}*!/
/!*

naverLogin.getLoginStatus(function (status) {
    if (status) {
        let email = naverLogin.user.getEmail();
        // console.log(email);
        console.log(naverLogin.user.getProfileImage());
        if (email === undefined || email === null) {
            alert("이메일 정보는 필수사항입니다. 정보제공을 동의해주세요.");
            naverLogin.reprompt();
            return;
        }

        // window.location.pathname = "mypage";

        console.log(naverLogin);
        console.log(naverLogin.user);
    } else {
        console.error("callback 실패");
    }
});*!/
*/


//
let naverLogin = new naver.LoginWithNaverId(
    {
        clientId: "T0eCoHKw1YK3phu9brZX",
        callbackUrl: "http://localhost:8080/naver_callback.jsp",
        isPopup: true,
        loginButton: {color: "green", type: 3, height: 60}
    }
);

naverLogin.init();

/*

naverLogin.getLoginStatus(function (status) {
    if (status) {
        let email = naverLogin.user.getEmail();
        if (email === undefined || email === null) {
            alert("이메일 정보는 필수사항입니다. 정보제공을 동의해주세요.");
            naverLogin.reprompt();
            return;
        }

        // window.location.pathname = "mypage";

        console.log(naverLogin);
        console.log(naverLogin.user);
    } else {
        console.error("callback 실패");
    }
});*/
