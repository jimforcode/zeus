//Sliding Effect Control
head.js("js/libs/apricot/skin-select/jquery.cookie.js");
//head.js("js/libs/apricot/skin-select/skin-select.js");

//Showing Date
//head.js("js/libs/apricot/clock/date.js");

//Bootstrap
//head.js("js/libs/apricot/bootstrap.js");

//NEWS STICKER
head.js("js/libs/apricot/newsticker/jquery.newsTicker.js");


head.js("js/libs/highcharts/highcharts.js");
head.js("js/libs/highcharts/highcharts-3d.src.js");

head.js("js/libs/highcharts/highcharts-more.js");
head.js("js/libs/highcharts/modules/solid-gauge.js");
//------------------------------------------------------------- 

head.js("js/libs/echarts/echarts-all-3.js");
head.js("js/libs/echarts/china.js");

head.js("js/libs/apricot/tip/jquery.tooltipster.js");

////Acordion and Sliding menu

head.js("js/libs/apricot/custom/scriptbreaker-multiple-accordion-1.js");

////Right Sliding menu

head.js("js/libs/apricot/slidebars/slidebars.min.js", function() {
    $(document).ready(function() {
        var mySlidebars = new $.slidebars();
        $('.toggle-left').on('click', function() {
            mySlidebars.toggle('right');
        });
    });
});

//-------------------------------------------------------------

//SEARCH MENU
head.js("js/libs/apricot/search/jquery.quicksearch.js", function() {
    $('input.id_search').quicksearch('#menu-showhide li, .menu-left-nest li');
});

//DIGITAL CLOCK
head.js("js/libs/apricot/clock/jquery.clock.js", function() {
    $('#digital-clock').clock({
        offset: '+8',
        type: 'digital'
    });


});


//------------------------------------------------------------- 