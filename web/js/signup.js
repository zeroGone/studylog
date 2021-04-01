const imageInputComponent = document.querySelector(".input-image");
const imagePreviewComponent = document.querySelector(".welcome-user-image");
const warningText = document.querySelector(".input-warning");

function isImageFile(file) {
    return /\.(jpg|jpeg|png)$/i.test(file.name);
}

function showImagePreview(image) {
    imagePreviewComponent.src = URL.createObjectURL(image);
    imagePreviewComponent.onload = () => URL.revokeObjectURL(imagePreviewComponent.src);
}

function makeFormData() {
    const formData = new FormData();
    formData.append("email", document.querySelector(".welcome-container").dataset.email);
    formData.append("name", document.querySelector(".welcome-user-name").innerText);
    formData.append("nickName", document.querySelector(".input-nick").value);
    if (imageInputComponent.files[0]) {
        formData.append("image", imageInputComponent.files[0]);
    } else {
        formData.append("imgUrl", imagePreviewComponent.src);
    }
    return formData;
}

function submitSignupData() {
    fetch("signup", {
        method: "POST",
        body: makeFormData()
    }).then(response => {
        if (response.status === 201) {
            window.location.pathname = "mypage";
        } else if (response.status === 400) {
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