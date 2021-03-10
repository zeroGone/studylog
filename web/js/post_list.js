const card = document.querySelectorAll('.card');

card.forEach((element) => {
    const isHaveThumbnail = element.querySelector('.card-image-thumbnail');
    if (!isHaveThumbnail) {
        element.classList.add('not-image');
    }
});