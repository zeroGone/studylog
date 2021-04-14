const reviewingLists = document.querySelectorAll('.reviewing-list-item');
const reviewingListMoreButton = document.querySelector('.reviewing-list-more');
const reviewingListLessButton = document.querySelector('.reviewing-list-less');
const reviewingMaxItemCount = 5;
const hiddenClass = "visually-hidden";

reviewingListLessButton.style.display = 'none';

[].forEach.call(reviewingLists, function (item, index) {
    if (index > reviewingMaxItemCount - 1) {
        item.classList.add(hiddenClass);
    }
});

if (reviewingLists.length - 1 < reviewingMaxItemCount) {
    reviewingListMoreButton.style.display = 'none';
}

let reviewingCurrentPage = 1;

reviewingListMoreButton.addEventListener('click', function () {
    reviewingLists.forEach((item, index) => {
        if (index < reviewingMaxItemCount * reviewingCurrentPage || index >= reviewingMaxItemCount * (reviewingCurrentPage + 1)) {
            item.classList.add(hiddenClass);
        } else {
            item.classList.remove(hiddenClass);
        }

        if (reviewingLists.length <= reviewingMaxItemCount * reviewingCurrentPage + 1) {
            reviewingListMoreButton.style.display = 'none';
        }

    }, 0);

    reviewingCurrentPage++;

    if (reviewingCurrentPage > 1) {
        reviewingListLessButton.style.display = 'block';
    }
});

reviewingListLessButton.addEventListener('click', function () {
    reviewingLists.forEach((item) => {
        item.classList.add(hiddenClass);
    });

    reviewingLists.forEach((item, index) => {
        if (index >= reviewingMaxItemCount * (reviewingCurrentPage - 1)) {
            item.classList.add(hiddenClass);
        } else if (index >= reviewingMaxItemCount * (reviewingCurrentPage - 2)) {
            item.classList.remove(hiddenClass);
        } else {
            item.classList.add(hiddenClass);
        }

        if (reviewingLists.length <= reviewingMaxItemCount * reviewingCurrentPage - 1) {
            reviewingListMoreButton.style.display = 'block';
        }
    }, 0);

    reviewingCurrentPage--;

    if (reviewingCurrentPage === 1) {
        reviewingListLessButton.style.display = 'none';
    }
});