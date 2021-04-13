const recentActivityLists = document.querySelectorAll('.recent-activity-item');
const recentActivityListMoreButton = document.querySelector('.recent-activity-list-more');
const recentActivityListLessButton = document.querySelector('.recent-activity-list-less');
const recentActivityMaxItemCount = 5;

recentActivityListLessButton.style.display = 'none';

[].forEach.call(recentActivityLists, function (item, index) {
    if (index > recentActivityMaxItemCount - 1) {
        item.classList.add(hiddenClass);
    }
});

if (recentActivityLists.length - 1 < recentActivityMaxItemCount) {
    recentActivityListMoreButton.style.display = 'none';
}

let recentActivityCurrentPage = 1;

recentActivityListMoreButton.addEventListener('click', function () {
    recentActivityLists.forEach((item, index) => {
        if (index < recentActivityMaxItemCount * recentActivityCurrentPage || index >= recentActivityMaxItemCount * (recentActivityCurrentPage + 1)) {
            item.classList.add(hiddenClass);
        } else {
            item.classList.remove(hiddenClass);
        }

        if (recentActivityLists.length <= recentActivityMaxItemCount * recentActivityCurrentPage + 1) {
            recentActivityListMoreButton.style.display = 'none';
        }

    }, 0);

    recentActivityCurrentPage++;

    if (recentActivityCurrentPage > 1) {
        recentActivityListLessButton.style.display = 'block';
    }
});

recentActivityListLessButton.addEventListener('click', function () {
    recentActivityLists.forEach((item) => {
        item.classList.add(hiddenClass);
    });

    recentActivityLists.forEach((item, index) => {
        if (index >= recentActivityMaxItemCount * (recentActivityCurrentPage - 1)) {
            item.classList.add(hiddenClass);
        } else if (index >= recentActivityMaxItemCount * (recentActivityCurrentPage - 2)) {
            item.classList.remove(hiddenClass);
        } else {
            item.classList.add(hiddenClass);
        }

        if (recentActivityLists.length <= recentActivityMaxItemCount * recentActivityCurrentPage - 1) {
            recentActivityListMoreButton.style.display = 'block';
        }
    }, 0);

    recentActivityCurrentPage--;

    if (recentActivityCurrentPage === 1) {
        recentActivityListLessButton.style.display = 'none';
    }
});