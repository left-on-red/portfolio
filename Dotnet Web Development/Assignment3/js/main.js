$(function(){
    let $header = $('#header');
    let $uncheckAll = $('#uncheckAll');

    function uncheckAll(click) {
        if (click) {
            $('.form-check-input').each(function() {
                if ($(this).prop('checked')) { $(this).trigger('click') }
            }); 
        }
        $('.form-check-input').each(function () {
            $(this).prop('checked', false);
        });
    }

    let animations = [
        {
            in: 'bounceInDown',
            out: 'bounceOutUp'
        },

        {
            in: 'fadeInDown',
            out: 'fadeOutUp'
        },

        {
            in: 'rotateInDownLeft',
            out: 'rotateOutUpRight'
        }
    ]

    let animate = animations[Math.floor(Math.random() * animations.length)];

    $('#birthday').pickadate({ format: 'mmmm, d' });

    
    // event listener for check/uncheck
    $('.form-check-input').on('change', function() {
        // make the image visible
        $(`#${this.id}Img`).css('visibility', 'visible')
        // animate balloon in/out based on checkbox
        $(this).is(':checked') ?
         $(`#${this.id}Img`).removeClass().addClass(`animated ${animate.in}`) :
         $(`#${this.id}Img`).addClass(`animated ${animate.out}`);
    });

    $('.form-check').on('mouseenter', function() {
        let color = $(this).find('input')[0].id;
        $header.css('color', color);
    });

    $('.form-check').on('mouseleave', function() {
        $header.css('color', '');
    });

    uncheckAll();

    $uncheckAll.on('click', function() { uncheckAll(true) });
});