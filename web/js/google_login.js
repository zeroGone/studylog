const GOOGLE_INIT_OPTIONS = {
    client_id: 'client_id',
    cookiepolicy: 'single_host_origin',
    scope: 'profile'
}

function loadGoogleApi() {
    gapi.load("auth2", () => {
        auth2 = gapi.auth2.init(GOOGLE_INIT_OPTIONS);
        document.querySelector(".google-login-button").addEventListener("click", () => {
            auth2.signIn().then(login);
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

function submit(userProfile) {
    const form = document.getElementById("login-form");
    form.querySelector("#login-form-user-name").value = userProfile.getName();
    form.querySelector("#login-form-user-email").value = userProfile.getEmail();
    form.querySelector("#login-form-user-image-url").value = userProfile.getImageUrl();
    form.submit();
}

window.onload = loadGoogleApi;