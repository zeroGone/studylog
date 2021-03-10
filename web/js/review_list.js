const openButton = document.querySelector('.open-button');
const closeButton = document.querySelector('.close-button');
const reviewItems = document.querySelector('.review-items');
const reviewListItems = reviewItems.querySelectorAll('.review-list-item');
const searchBox = document.querySelector('.search-box');
const searchInput = searchBox.querySelector('.search-input');

openButton.addEventListener('click', function () {
    closeButton.classList.remove('checked');
    reviewItems.classList.remove('close-checked');
    searchBox.classList.remove('close-checked');
    searchInput.classList.remove('close-checked');
    reviewListItems.forEach((element) => {
        element.classList.remove('close-checked');
    });
});

closeButton.addEventListener('click', function () {
    closeButton.classList.add('checked');
    reviewItems.classList.add('close-checked');
    searchBox.classList.add('close-checked');
    searchInput.classList.add('close-checked');
    reviewListItems.forEach((element) => {
        element.classList.add('close-checked');
    });

});