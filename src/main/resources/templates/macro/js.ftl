<!-- jQuery -->
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>

<!-- pace -->
<script src="https://cdn.bootcss.com/pace/1.0.2/pace.min.js"></script>

<!-- jquery ui -->
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>$.widget.bridge('uibutton', $.ui.button);</script>

<!-- Bootstrap -->
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- FastClick -->
<script src="https://cdn.bootcss.com/fastclick/1.0.6/fastclick.min.js"></script>

<!-- select2 -->
<script src="https://cdn.bootcss.com/select2/4.0.8/js/select2.full.min.js"></script>
<script src="https://cdn.bootcss.com/select2/4.0.8/js/i18n/zh-CN.js"></script>
<script>$.fn.select2.defaults.set('language', 'zh-CN');</script>

<!-- moment -->
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.min.js"></script>

<!-- datetimepicker -->
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script>
    $.extend($.fn.datetimepicker.defaults, {
        locale: 'zh-cn'
        , format: 'YYYY-MM-DD HH:mm:ss'
        , showTodayButton: true
        , showClear: true
        , showClose: true
    });
</script>

<!-- iCheck -->
<script src="https://cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>

<!-- sweetalert2 -->
<script src="https://cdn.bootcss.com/limonte-sweetalert2/8.11.8/sweetalert2.all.min.js"></script>

<!-- echarts -->
<script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>

<!-- datatables -->
<script src="https://cdn.bootcss.com/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/select/1.3.0/js/dataTables.select.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>

<!-- AdminLTE App -->
<script src="https://cdn.bootcss.com/admin-lte/2.4.15/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="https://cdn.bootcss.com/admin-lte/2.4.15/js/demo.js"></script>

<!-- app js -->
<script src="${request.contextPath}/js/status.js?v=1.0.0"></script>
<script src="${request.contextPath}/js/toolkit.js?v=1.0.0"></script>
<script src="${request.contextPath}/js/common.js?v=1.0.0"></script>
