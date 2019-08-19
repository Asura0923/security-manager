/**
 * 通过url选中菜单
 */
var parentsli = $('#menu a[href="' + window.location.pathname + '"]').parents('li');
if (parentsli.length > 0) {
    parentsli.addClass('active');
} else {
    //可能是跳转的add或者update的链接
    var newUrl = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
    console.log(newUrl);
    newUrl += '/list';
    console.log(newUrl);
    $('#menu a[href="' + newUrl + '"]').parents('li').addClass('active');
}
