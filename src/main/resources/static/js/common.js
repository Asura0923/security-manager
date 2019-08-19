$('#clearForm').on('click', function () {
    $.each($(this).parents('form').find('input'), function (index, item) {
        if (!$(item).attr('readonly')) {
            $(item).val('');
        }
    });
    $.each($(this).parents('form').find('select'), function (index, item) {
        if (!$(item).attr('readonly')) {
            $(item).val(null).trigger('change');
        }
    });
});