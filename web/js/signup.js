const imageInputComponent = document.querySelector(".input-image");
const imagePreviewComponent = document.querySelector(".welcome-user-image");
const warningText = document.querySelector(".input-warning");

function isImageFile(file) {
    return /\.(gif|jpg|jpeg|png)$/i.test(file.name);
}

function showImagePreview(image) {
    imagePreviewComponent.src = URL.createObjectURL(image);
    imagePreviewComponent.onload = () => URL.revokeObjectURL(imagePreviewComponent.src);
}

function submitSignupData() {
    const userFormData = new FormData();
    userFormData.append("email", document.querySelector(".welcome-container").dataset.email);
    userFormData.append("name", document.querySelector(".welcome-user-name").innerText);
    userFormData.append("nickName", document.querySelector(".input-nick").value);
    if (imageInputComponent.files[0]) {
        userFormData.append("image", imageInputComponent.files[0]);
    } else {
        userFormData.append("imgUrl", imagePreviewComponent.src);
    }

    fetch("signup", {
        method: "POST",
        body: userFormData
    }).then(response => {
        if (response.status === 201) {
            window.location.pathname = "mypage";
        } else if (response.status === 200) {
            warningText.innerText = "닉네임이 중복됩니다";
        }
    }).catch(error => alert("회원가입 실패! " + error));
}

imageInputComponent.addEventListener("change", event => {
    if (isImageFile(event.target.files[0])) {
        showImagePreview(event.target.files[0]);
    }
})

imagePreviewComponent.addEventListener("click", () => imageInputComponent.click());

document.querySelector(".signup-button").addEventListener("click", submitSignupData);