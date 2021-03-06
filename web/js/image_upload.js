const userImageInput = document.querySelector('#user-image-input');
const userImageEditButton = document.querySelector('.user-image-edit-button');

/*
userImageEditButton.addEventListener('click', function () {
    userImageInput.click();
});
*/

/*
userImageInput.onchange = function (event) {
    if (userImageInput.files.length !== 0) {
        previewImage(event);
    }
}
*/

const blogImageInput = document.querySelector('#blog-image-input');
const blogImageEditButton = document.querySelector('.blog-image-edit-button');

blogImageEditButton.addEventListener('click', function () {
    blogImageInput.click();
});

blogImageInput.onchange = function (event) {
    if (blogImageInput.files.length !== 0) {
        previewImage(event);
    }
};

function previewImage(event) {
    const targetId = event.target.getAttribute('id');
    const file = event.target.files;

    if (!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name)) {
        activeAlertContainer('imageCheckFormat');
    } else {
        let formData = new FormData();
        formData.append('image', file[0]);

        const reader = new FileReader();
        if (targetId === 'user-image-input') {
            reader.onload = function (progressEvent) {
                postUserImage(formData, progressEvent);
            }
        } else if (targetId === 'blog-image-input') {
            reader.onload = function (progressEvent) {
                document.querySelector('#blog-image-preview').innerHTML = `<img src="${progressEvent.target.result}" class="blog-create-image blog-image" alt="${progressEvent.target.result}">`;
            }
        }

        reader.readAsDataURL(file[0]);
    }
}

function postUserImage(formData, progressEvent) {
    const userId = document.querySelector('.profile-info').dataset.userId;

    fetch(`api/user/${userId}`, {
        method: 'POST',
        mode: 'no-cors',
        body: formData
    }).then(response => {
        if (response.status === 200) {
            return previewUserImage(progressEvent);
        } else {
            return activeAlertContainer('notPostImage');
        }
    })
        .catch(error => console.error(error));
}

function previewUserImage(progressEvent) {
    document.querySelector('#user-image-preview').innerHTML = `<img src="${progressEvent.target.result}" class="profile-image" alt="${progressEvent.target.result}">`;
    document.querySelector('.user-info-image').setAttribute('src', `${progressEvent.target.result}`);
}