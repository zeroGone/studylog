const GOOGLE_LOGIN_INFO = {
    API_URL: "login",
    INIT_OPTIONS: {
        client_id: '900965677916-dipqmfduh44cl0q623ccopn0r5ivv7s4.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        scope: 'profile'
    },
}

function loadGoogleApi() {
    gapi.load("auth2", () => {
        auth2 = gapi.auth2.init(GOOGLE_LOGIN_INFO.INIT_OPTIONS);

        document.querySelector(".google-login-button").addEventListener("click", () => {
            auth2.signIn().then(login(getGoogleProfile())).catch(handleGoogleApiFail);
        });
    });
}

function getGoogleProfile() {
    const profile = auth2.currentUser.get().getBasicProfile();
    return {
        "name": profile.getName(),
        "email": profile.getEmail(),
        "imgUrl": profile.getImageUrl()
    }
}

function handleGoogleApiFail(error) {
    console.error('Google Sign Up or Login Error: ', error)
}

function login(userInfo) {
    fetch("login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify(userInfo)
    }).then(response => {
        if (response.status === 200) {
            window.location.pathname = "mypage";
        } else if (response.status === 400) {
            window.location.pathname = "signup";
        }
    }).catch(error => console.log(error));
}

window.onload = loadGoogleApi;