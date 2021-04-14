const memberLists = document.querySelectorAll('.member-list-item');
const memberListMoreButton = document.querySelector('.member-list-more');
const memberListLessButton = document.querySelector('.member-list-less');
const memberMaxItemCount = 8;

memberListLessButton.style.display = 'none';

[].forEach.call(memberLists, function (item, index) {
    if (index > memberMaxItemCount - 1) {
        item.classList.add(hiddenClass);
    }
});

if (memberLists.length - 1 < memberMaxItemCount) {
    memberListMoreButton.style.display = 'none';
}

let memberCurrentPage = 1;

memberListMoreButton.addEventListener('click', function () {
    memberLists.forEach((item, index) => {
        if (index < memberMaxItemCount * memberCurrentPage || index >= memberMaxItemCount * (memberCurrentPage + 1)) {
            item.classList.add(hiddenClass);
        } else {
            item.classList.remove(hiddenClass);
        }

        if (memberLists.length <= memberMaxItemCount * (memberCurrentPage + 1)) {
            memberListMoreButton.style.display = 'none';
        }

    }, 0);

    memberCurrentPage++;

    if (memberCurrentPage > 1) {
        memberListLessButton.style.display = 'block';
    }
});

memberListLessButton.addEventListener('click', function () {
    memberLists.forEach((item) => {
        item.classList.add(hiddenClass);
    });

    memberLists.forEach((item, index) => {
        if (index >= memberMaxItemCount * (memberCurrentPage - 1)) {
            item.classList.add(hiddenClass);
        } else if (index >= memberMaxItemCount * (memberCurrentPage - 2)) {
            item.classList.remove(hiddenClass);
        } else {
            item.classList.add(hiddenClass);
        }

        if (memberLists.length <= memberMaxItemCount * memberCurrentPage - 1) {
            memberListMoreButton.style.display = 'block';
        }
    }, 0);

    memberCurrentPage--;

    if (memberCurrentPage === 1) {
        memberListLessButton.style.display = 'none';
    }
});