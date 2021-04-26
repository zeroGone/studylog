const mainSectionListMoreButtons = document.querySelectorAll('.main-section-list-more');
const mainSectionListLessButtons = document.querySelectorAll('.main-section-list-less');

const memberLists = document.querySelectorAll('.member-list-item');
let memberCurrentPage = 1;
let memberMaxItemCount = 8;

const reviewingLists = document.querySelectorAll('.reviewing-list-item');
let reviewingCurrentPage = 1;
const reviewingMaxItemCount = 5;

const recentActivityLists = document.querySelectorAll('.recent-activity-item');
let recentActivityCurrentPage = 1;
const recentActivityMaxItemCount = 5;

const hiddenClass = "visually-hidden";

window.addEventListener('load', function () {
    setLoadWindow();
    setMemberMaxItemCount();
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
        } else {
            item.classList.remove(hiddenClass);
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
    [].forEach.call(list, (item, index) => {
        if (index < max * page || index >= max * (page + 1)) {
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

    if (button.classList.contains('member-list-less')) {
        memberCurrentPage--;
    } else if (button.classList.contains('reviewing-list-less')) {
        reviewingCurrentPage--;
    } else if (button.classList.contains('recent-activity-list-less')) {
        recentActivityCurrentPage--;
    }

    if (page === 1) {
        button.style.visibility = 'hidden';
    }
}


const container = document.querySelector('.main-container');
window.addEventListener('resize', function (event) {
    if (window.innerWidth > 1065) {
        if (!container.classList.contains('pc')) {
            setWindowPcVersion();
            setMemberMaxItemCount();
            setReorganizeList(memberLists, memberMaxItemCount, memberCurrentPage);
        }
    } else if (window.innerWidth < 1065 && window.innerWidth > 801) {
        if (!container.classList.contains('tablet')) {
            setWindowTabletVersion();
            setMemberMaxItemCount();
            setReorganizeList(memberLists, memberMaxItemCount, memberCurrentPage);

        }

    } else if (window.innerWidth < 801) {
        if (!container.classList.contains('mobile')) {
            setWindowMobileVersion();
            setMemberMaxItemCount();
            setReorganizeList(memberLists, memberMaxItemCount, memberCurrentPage);
        }
    }
});

function setReorganizeList(list, max, page) {
    if (page > 1 && list.length <= max * (page - 1)) {
        page--;
        memberCurrentPage--;
    }

    if (list.length - 1 < max || list.length < max * page) {
        document.querySelector('.member-list-more').style.visibility = 'hidden';
    } else {
        document.querySelector('.member-list-more').style.visibility = 'visible';
    }

    [].forEach.call(list, (item) => {
        item.classList.add(hiddenClass);
    });

    [].forEach.call(list, function (item, index) {
        if (index >= max * page) {
            item.classList.add(hiddenClass);
        } else if (index >= max * (page - 1)) {
            item.classList.remove(hiddenClass);
        } else {
            item.classList.add(hiddenClass);
        }
    });
}

function setWindowPcVersion() {
    container.classList.add('pc');
    container.classList.remove('tablet', 'mobile');
}

function setWindowTabletVersion() {
    container.classList.add('tablet');
    container.classList.remove('pc', 'mobile');
}

function setWindowMobileVersion() {
    container.classList.add('mobile');
    container.classList.remove('pc', 'tablet');
}

function setLoadWindow() {
    if (window.innerWidth > 1065) {
        setWindowPcVersion();
    } else if (window.innerWidth < 1065 && window.innerWidth > 801) {
        setWindowTabletVersion();
    } else if (window.innerWidth < 801) {
        setWindowMobileVersion();
    }
}

function setMemberMaxItemCount() {
    if (container.classList.contains('pc')) {
        return memberMaxItemCount = 8;
    } else if (container.classList.contains('tablet')) {
        return memberMaxItemCount = 9;
    } else if (container.classList.contains('mobile')) {
        return memberMaxItemCount = 6;
    }
}