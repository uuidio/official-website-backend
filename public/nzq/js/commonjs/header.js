$(function () {
  if ($(window).scrollTop() > 0) {
    $('.header').css({'background': '#fff'});
  } else {
    $('.header').css({'background': 'transparent'});
  }
  if ($(window).width() > 767) {
    $(document.body).append('<script>var _hmt = _hmt || []; (function() { var hm = document.createElement("script"); hm.src = "https://hm.baidu.com/hm.js?7ae99b02b5de0ff18b4def7cce22e02f"; var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(hm, s); })();</script>');
  }
  $('.nav-bar-list').on('click', '.nav-bar-item.service', function () {
      $('.nav-bar-item-list ul').toggle();
  });
  $(window).on('scroll', function () {
    if ($(this).scrollTop() > 0) {
      $('.header').css({'background': '#fff'});
    } else {
      $('.header').css({'background': 'transparent'});
    }
  });
});