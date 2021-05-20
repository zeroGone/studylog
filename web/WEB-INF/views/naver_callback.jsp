<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>NaverLoginSDK</title>
</head>

<body>

<!-- (1) LoginWithNaverId Javscript SDK -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"
        charset="utf-8"></script>


<!-- (2) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
<script>
    var naverLogin = new naver.LoginWithNaverId(
        {
            clientId: "T0eCoHKw1YK3phu9brZX",
            callbackUrl: "http://localhost:8080/naver_callback.jsp",
            isPopup: false,
            callbackHandle: true
        }
    );

    /* (3) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
    naverLogin.init();

    /* (4) Callback의 처리. 정상적으로 Callback 처리가 완료될 경우 main page로 redirect(또는 Popup close) */
    window.addEventListener('load', function () {
        naverLogin.getLoginStatus(function (status) {
            if (status) {
                /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
                let email = naverLogin.user.getEmail();

                if (email === undefined || email === null) {
                    alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
                    /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
                    naverLogin.reprompt();
                    return;
                }

                // submit(naverLogin.user);
                // window.location.replace("http://" + window.location.hostname +
                //     ((location.port == "" || location.port == undefined) ? "" : ":" + location.port) + "/sample/main.html");
                /* 인증이 완료된후 /sample/main.html 페이지로 이동하라는것이다. 본인 페이로 수정해야한다. */
            } else {
                console.log("callback 처리에 실패하였습니다.");
            }
        });
    });

    function submit(userProfile) {
        // console.log(userProfile);
        alert(userProfile);
        fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify({
                "name": userProfile.getName(),
                "email": userProfile.getEmail(),
                "imageUrl": userProfile.getProfileImage()
            })
        }).then(response => {
            if (isLoginSuccess(response)) {
                window.location.pathname = "mypage";
            } else {
                window.location.pathname = "signup";
            }
        }).catch(error => console.log(error));
    }
</script>
</body>

</html>