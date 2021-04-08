const GOOGLE_LOGIN_INFO = {
    API_URL: "login",
    INIT_OPTIONS: {
        client_id: 'client_id',
        cookiepolicy: 'single_host_origin',
        scope: 'profile'
    },
}

function loadGoogleApi() {
    gapi.load("auth2", () => {
        auth2 = gapi.auth2.init(GOOGLE_LOGIN_INFO.INIT_OPTIONS);
        document.querySelector(".google-login-button").addEventListener("click", () => {
            auth2.signIn().then(login).catch(handleGoogleApiFail);
        });
    });
}

function login() {
    if (auth2.currentUser) {
        submit(auth2.currentUser.get().getBasicProfile());
    } else {
        auth2.currentUser.listen(submit(auth2.currentUser.get().getBasicProfile()));
    }

}

function handleGoogleApiFail(error) {
    console.error('Google Sign Up or Login Error: ', error)
}

function submit(userProfile) {
    fetch("login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify({
            "name": userProfile.getName(),
            "email": userProfile.getEmail(),
            "imageUrl": userProfile.getImageUrl()
        })
    }).then(response => {
        if (isLoginSuccess(response)) {
            window.location.pathname = "mypage";
        } else {
            window.location.pathname = "signup";
        }
    }).catch(error => console.log(error));
}

function isLoginSuccess(response) {
    if (response.status !== 200) {
        return false;
    }
    return response.json().id;
}

window.onload = loadGoogleApi;