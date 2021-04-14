const mainSectionListMoreButtons = document.querySelectorAll('.main-section-list-more');
const mainSectionListLessButtons = document.querySelectorAll('.main-section-list-less');

const memberLists = document.querySelectorAll('.member-list-item');
let memberCurrentPage = 1;
const memberMaxItemCount = 8;

const reviewingLists = document.querySelectorAll('.reviewing-list-item');
let reviewingCurrentPage = 1;
const reviewingMaxItemCount = 5;

const recentActivityLists = document.querySelectorAll('.recent-activity-item');
let recentActivityCurrentPage = 1;
const recentActivityMaxItemCount = 5;

const hiddenClass = "visually-hidden";

window.addEventListener('load', function () {
    setList(memberLists, memberMaxItemCount);
    setList(reviewingLists, reviewingMaxItemCount);
    setList(recentActivityLists, recentActivityMaxItemCount);

    [].forEach.call(mainSectionListLessButtons, function (item) {
        item.style.visibility = 'hidden';
    });
});

function setList(list, max) {
    [].forEach.call(list, function (item, index) {
        if (index > max - 1) {
            item.classList.add(hiddenClass);
        }
    });

    if (list.length - 1 < max) {
        list[0].parentElement.nextElementSibling.style.visibility = 'hidden';
    }
}

[].forEach.call(mainSectionListMoreButtons, function (button) {
    button.addEventListener('click', function () {
        if (button.classList.contains('member-list-more')) {
            controlListMore(memberLists, this, memberCurrentPage, memberMaxItemCount);
        } else if (button.classList.contains('reviewing-list-more')) {
            controlListMore(reviewingLists, this, reviewingCurrentPage, reviewingMaxItemCount);
        } else if (button.classList.contains('recent-activity-list-more')) {
            controlListMore(recentActivityLists, this, recentActivityCurrentPage, recentActivityMaxItemCount);
        }
    });
});

function controlListMore(list, button, page, max) {
    [].forEach.call(list, (item, idx) => {
        if (idx < max * page || idx >= max * (page + 1)) {
            item.classList.add(hiddenClass);
        } else {
            item.classList.remove(hiddenClass);
        }

        if (list.length <= max * (page + 1)) {
            button.style.visibility = 'hidden';
        }
    }, 0);

    page++;

    if (button.classList.contains('member-list-more')) {
        memberCurrentPage++;
    } else if (button.classList.contains('reviewing-list-more')) {
        reviewingCurrentPage++;
    } else if (button.classList.contains('recent-activity-list-more')) {
        recentActivityCurrentPage++;
    }

    if (page > 1) {
        const lessButton = button.previousElementSibling.previousElementSibling;
        lessButton.style.visibility = 'visible';
    }
}

[].forEach.call(mainSectionListLessButtons, function (button) {
    button.addEventListener('click', function () {
        if (button.classList.contains('member-list-less')) {
            controlListLess(memberLists, this, memberCurrentPage, memberMaxItemCount)
        } else if (button.classList.contains('reviewing-list-less')) {
            controlListLess(reviewingLists, this, reviewingCurrentPage, reviewingMaxItemCount)
        } else if (button.classList.contains('recent-activity-list-less')) {
            controlListLess(recentActivityLists, this, recentActivityCurrentPage, recentActivityMaxItemCount)
        }
    });
});

function controlListLess(list, button, page, max) {
    [].forEach.call(list, (item) => {
        item.classList.add(hiddenClass);
    });

    [].forEach.call(list, (item, index) => {
        if (index >= max * (page - 1)) {
            item.classList.add(hiddenClass);
        } else if (index >= max * (page - 2)) {
            item.classList.remove(hiddenClass);
        } else {
            item.classList.add(hiddenClass);
        }

        if (list.length >= max * (page - 1)) {
            const moreButton = button.nextElementSibling.nextElementSibling;
            moreButton.style.visibility = 'visible';
        }
    }, 0);

    page--;

    if (button.classList.contains('member-list-more')) {
        memberCurrentPage--;
    } else if (button.classList.contains('reviewing-list-more')) {
        reviewingCurrentPage--;
    } else if (button.classList.contains('recent-activity-list-more')) {
        recentActivityCurrentPage--;
    }

    if (page === 1) {
        button.style.visibility = 'hidden';
    }
}