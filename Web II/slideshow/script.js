var slideshow = {
    index: 0,
    slides: null,
    dots: null,

    container: document.getElementsByClassName('container')[0],
    dotContainer: document.getElementsByClassName('dotContainer')[0],

    directory: 'images/',

    images: [
        'Bolero.jpg',
        'Bossa Nova.jpg',
        'Breath of the Wild.jpg',
        'Elegy.jpg',
        'Ganons Castle.jpg',
        'Healing.jpg',
        'Hero of the Winds.jpg',
        'Jump Attack.jpg',
        'Lullaby.jpg',
        'Majora.png',
        'Minuet.jpg',
        'Nocturne.jpg',
        'Ocarina of Time.jpg',
        'Prelude.jpg',
        'Requiem.jpg',
        'Serenade.jpg',
        'Shattered Shield.jpg',
        'Skyrule.jpg',
        'Skyward Dive.png',
        'Skyward Sword.jpg',
        'Sonata.jpg',
        'Terrible Fate.jpg',
        'Twilight Hero.jpg',
        'Wind Waker.jpg'
    ],

    init: function() {
        for (var i = 0; i < slideshow.images.length; i++) {
            var slide = document.createElement('div');
            slide.setAttribute('class', 'slide fade');

            var number = document.createElement('div');
            number.innerHTML = (i + 1) + ' / ' + slideshow.images.length;
            number.setAttribute('class', 'number');
            slide.appendChild(number);

            var image = document.createElement('img');
            image.setAttribute('src', slideshow.directory + slideshow.images[i]);
            image.style.width = '100%';
            slide.appendChild(image);

            var caption = document.createElement('div');
            caption.innerHTML = slideshow.images[i].split('.')[0];
            caption.setAttribute('class', 'text');
            slide.appendChild(caption);

            slideshow.container.appendChild(slide);

            var dot = document.createElement('span');
            dot.setAttribute('class', 'dot');
            dot.setAttribute('onclick', 'slideshow.show(' + i + ')');

            slideshow.dotContainer.appendChild(dot);
        }

        slideshow.slides = document.getElementsByClassName('slide');
        slideshow.dots = document.getElementsByClassName('dot');
        
        var prev = document.createElement('a');
        prev.setAttribute('class', 'prev');
        prev.setAttribute('onclick', 'slideshow.previous()');
        prev.innerHTML = '&#10094;'
        prev.style.display = 'none';

        var next = document.createElement('a');
        next.setAttribute('class', 'next');
        next.setAttribute('onclick', 'slideshow.next()');
        next.innerHTML = '&#10095;'
        next.style.display = 'block';

        slideshow.container.appendChild(prev);
        slideshow.container.appendChild(next);

        slideshow.show(0);
    },

    show: function(number) {

        if (slideshow.slides[number + 1] != undefined) {
            document.getElementsByClassName('next')[0].style.display = 'block';
        }

        else {
            document.getElementsByClassName('next')[0].style.display = 'none';
        }

        if (slideshow.slides[number - 1] != undefined) {
            document.getElementsByClassName('prev')[0].style.display = 'block';
        }

        else {
            document.getElementsByClassName('prev')[0].style.display = 'none';
        }

        slideshow.index = number;

        for (i = 0; i < slideshow.slides.length; i++) {
            slideshow.slides[i].style.display = 'none';
        }

        for (i = 0; i < slideshow.dots.length; i++) {
            slideshow.dots[i].className = slideshow.dots[i].className.replace(' active', '');
        }

        //console.log(slides);
        slideshow.slides[slideshow.index].style.display = 'block';
        slideshow.dots[slideshow.index].className += ' active';
    },

    next: function() {
        if (slideshow.slides[slideshow.index + 1] != undefined) {
            slideshow.show(slideshow.index + 1);
        }
    },

    previous: function() {
        if (slideshow.slides[slideshow.index - 1] != undefined) {
            slideshow.show(slideshow.index - 1);
        }
    }
}

slideshow.init();