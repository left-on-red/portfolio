$(function() {
    let $toast = $('#toast');
    let $toastBody = $toast.find('.toast-body');
    let $product = $('#product');

    let wave = new Audio('toast.wav');

    $('.code').on('click', function(e) {
        e.preventDefault();

        $parent = $(e.target.parentElement);
        $product.text($parent.data('product'));
        $toastBody.html(`Discount Code: <b>${$parent.data('code')}</b>`);

        $toast.css('top', e.originalEvent.clientY - 100);
        $toast.css('left', e.originalEvent.clientX);
        wave.pause();
        wave.currentTime = 0;
        wave.play();
        $toast.toast({ autohide: false }).toast('show');
    });

    // closes the toast when the user enters the esc key
    $(document).keydown(function(e) {
        // esc key
        if (e.keyCode == 27) { $toast.toast('hide') }
    })
});