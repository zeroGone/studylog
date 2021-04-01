const imageInput = document.querySelector('#image-input');
const imageEditButton = document.querySelector('.blog-images-edit-button');

imageEditButton.addEventListener('click', function () {
    imageInput.click();
});

imageInput.onchange = function (event) {
    previewImage(event);
};

function previewImage(event) {
    const file = event.target.files;

    if (!/\.(gif|jpg|jpeg|png)$/i.test(file[0].name)) {
        activeAlertContainer('image');
        event.outerHTML = event.outerHTML;
        document.getElementById('preview').innerHTML = '';
    } else {
        let formData = new FormData();
        formData.append('files', file);

        const reader = new FileReader();
        reader.onload = function (progressEvent) {
            document.getElementById('preview').innerHTML = `<img src="${progressEvent.target.result}" class="blog-create-image" alt="${progressEvent.target.result}">`;
        }

        reader.readAsDataURL(file[0]);
    }
}