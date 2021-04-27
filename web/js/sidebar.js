window.onload = function () {
    const header = document.querySelector('.header');
    const menuBtn = header.querySelector('.header-menu')
    const sidebar = document.querySelector('.sidebar');
    const mainContainer = document.querySelector('.main-container');
    const overlay = document.querySelector('.overlay');

    menuBtn.addEventListener('click', function () {
        sidebarHandler();
        menuBtnClick();
        mainContainerHandler();
        mainContainerOverlayHandler();
    });

    function sidebarHandler() {
        if (sidebar.classList.contains('off')) {
            sidebar.classList.add('on');
            sidebar.classList.remove('off');
        } else {
            sidebar.classList.add('off');
            sidebar.classList.remove('on');
        }
    }

    function menuBtnClick() {
        if (menuBtn.classList.contains('sideOff')) {
            menuBtn.classList.add('sideOn');
            menuBtn.classList.remove('sideOff');
        } else {
            menuBtn.classList.add('sideOff');
            menuBtn.classList.remove('sideOn');
        }
    }

    function mainContainerHandler() {
        if (mainContainer.classList.contains('sideOff')) {
            mainContainer.classList.add('sideOn');
            mainContainer.classList.remove('sideOff');
        } else {
            mainContainer.classList.add('sideOff');
            mainContainer.classList.remove('sideOn');
        }
    }

    function mainContainerOverlayHandler() {
        if (overlay.classList.contains('off')) {
            overlay.classList.add('on');
            overlay.classList.remove('off');
        } else {
            overlay.classList.add('off');
            overlay.classList.remove('on');
        }
    }
}