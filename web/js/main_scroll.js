window.addEventListener('load', function () {
    if (document.body.classList.contains('fullscreen') && window.innerWidth > 1024) {
        window.scrollTo({top: 0, behavior: 'smooth'});

        const sections = document.querySelectorAll('.main-section');
        let scrollIndex = 0;

        const sectionButtons = document.querySelectorAll('.section-button');
        [].forEach.call(sectionButtons, function (item, index) {
            item.addEventListener('click', function () {
                document.querySelector('.section-button.active').classList.remove('active');
                this.classList.add('active');
                scrollIndex = index;
                scroll_content(scrollIndex);
            });
        });

        let can_scroll = true;
        window.addEventListener('mousewheel', function (event) {
            if (can_scroll) {
                can_scroll = false;

                if (event.deltaY > 0) {
                    if (scrollIndex < sections.length) scrollIndex += 1;
                } else {
                    if (scrollIndex > 0) scrollIndex -= 1;
                }

                scroll_content(scrollIndex);
            }

            setTimeout(function () {
                can_scroll = true;
            }, 560);

        });

        function scroll_content(count) {
            const content = document.querySelector('.main-container');
            const footer = document.querySelector('.footer');

            if (count === sections.length) {
                content.setAttribute('style', `transform: translateY(-${((count - 1) * 100) + 20}vh)`);
                footer.classList.add('active');
            } else {
                content.setAttribute('style', `transform: translateY(-${count * 100}vh)`);
                footer.classList.remove('active');
            }
            document.querySelector('.section-button.active').classList.remove('active');
            sectionButtons[count].classList.add('active');
        }
    }
});