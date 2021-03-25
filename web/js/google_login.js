const GOOGLE_LOGIN_INFO = {
    API_URL: "login",
    INSTANCE_NAME: "auth2",
    INIT_OPTIONS: {
        client_id: '900965677916-dipqmfduh44cl0q623ccopn0r5ivv7s4.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        scope: 'profile'
    },
}

function loadGoogleApi() {
    gapi.load(GOOGLE_LOGIN_INFO.INSTANCE_NAME, () => {
        auth2 = gapi.auth2.init(GOOGLE_LOGIN_INFO.INIT_OPTIONS);
        document.querySelector(".google-login-button").addEventListener("click", loginByGoogleApi);
    });
};

function loginByGoogleApi() {
    auth2.signIn().then(() => {
        login(auth2.currentUser.get().getBasicProfile());
    }).catch((error) => {
        console.error('Google Sign Up or Login Error: ', error)
    });
}

function login(userInfo) {
    fetch(GOOGLE_LOGIN_INFO.API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify({
                "email": userInfo.getEmail(),
                "name": userInfo.getName(),
                "imgUrl": userInfo.getImageUrl()
            })
        }
    )
        .then(response => {
            if (response.status === 200) {
                window.location = "/mypage";
            } else if (response.status === 400) {
                alert(response.json())
            }
        })
        .catch(error =>
            alert(error)
        );
}

window.onload = loadGoogleApi;