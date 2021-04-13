window.addEventListener('load', function () {
    if (document.body.classList.contains('fullscreen') && window.innerWidth > 1024) {
        window.scrollTo({top: 0, behavior: 'smooth'});

        const sections = document.querySelectorAll('.main-section');
        const footer = document.querySelector('.footer');
        const content = document.querySelector('.main-container');
        const scrollLength = sections.length;
        let spin_value = 0;
        let can_scroll = true;
        let section_nav = '';


        document.body.insertAdjacentHTML('beforeend', '<div class="section-navigation"></div>');

        for (let i = 0; i < sections.length; i++) {
            section_nav += `<div class="section-button"><span>${sections[i].dataset.title}</span></div>`;
        }
        section_nav += `<div class="section-button"><span>footer</span></div>`;

        document.querySelector('.section-navigation').innerHTML = section_nav;

        const sectionButtons = document.querySelectorAll('.section-button');
        sectionButtons[0].classList.add('active');

        for (let i = 0; i < sectionButtons.length; i++) {
            sectionButtons[i].addEventListener('click', function () {
                document.querySelector('.section-button.active').classList.remove('active');
                this.classList.add('active');
                spin_value = i;
                scroll_content(spin_value);
            });
        }

        window.addEventListener('mousewheel', function (e) {
            if (can_scroll) {
                can_scroll = false;

                if (e.deltaY > 0) {
                    // scroll down
                    if (spin_value < sections.length) spin_value += 1;
                } else {
                    // scroll up
                    if (spin_value > 0) spin_value -= 1;
                }

                scroll_content(spin_value);

            }

            setTimeout(function () {
                can_scroll = true;
            }, 560);

        });

        function scroll_content(count) {
            if (count === scrollLength) {
                footer.classList.add('active');
                content.setAttribute('style', `transform: translateY(-${((count - 1) * 100) + 20}vh)`);
            } else {
                footer.classList.remove('active');
                content.setAttribute('style', `transform: translateY(-${count * 100}vh)`);
            }
            document.querySelector('.section-button.active').classList.remove('active');
            sectionButtons[count].classList.add('active');
        }
    }
});