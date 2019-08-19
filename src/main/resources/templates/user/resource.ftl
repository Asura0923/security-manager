<#include "../macro/head.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@head title="Security Manager | User"/>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <#include "../macro/navbar.ftl"/>

    <#include "../macro/menu.ftl"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Security
                <small>User</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> User</a></li>
                <li class="active">Resource</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-solid">
                        <div class="box-header">
                            <i class="fa fa-cog fa-spin"></i>
                            <h3 class="box-title">关联资源</h3>
                            <div class="pull-right box-tools">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse">
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                        </div>
                        <div class="box-body no-padding" style="">
                            <div style="width: 100%"></div>
                        </div>
                        <div class="box-footer" style="">
                            <form class="form-horizontal" onsubmit="return preForm();"
                                  action="${request.contextPath}/security/user/resource" method="post">
                                <input name="id" value="${securityUser.id?c}" style="display: none;" readonly>
                                <input id="resourceIds" name="resourceIds" value="${resourceIds!}"
                                       style="display: none;" readonly>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="name" class="col-md-2 col-lg-1 control-label">用户名称</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="name" name="name" placeholder="请填写用户名称"
                                                   required value="${securityUser.name!}" readonly>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="email" class="col-md-2 col-lg-1 control-label">邮箱</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="email" name="email" placeholder="请填写邮箱"
                                                   type="email" required value="${securityUser.email!}" readonly>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-md-12 col-lg-12">
                                            <table id="resultTable"
                                                   class="table table-striped table-bordered table-hover"
                                                   style="width: 100%;"></table>
                                        </div>
                                    </div>
                                </div>
                                <div class="box-footer">
                                    <button id="clearForm" type="button" class="btn btn-default">清空</button>
                                    <button type="submit" class="btn btn-info pull-right">关联</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <#include "../macro/footer.ftl"/>
</div>
<#include "../macro/js.ftl"/>
<script>
    var datatable = $('#resultTable').DataTable({
        processing: true
        , serverSide: true
        , ajax: {
            url: '${request.contextPath}/security/resource/list'
            , type: 'post'
            , dataSrc: 'datas'
        }
        , columns: [
            {
                data: 'id', title: '', render: function () {
                    return ''
                }
            }
            , {data: 'name', title: '资源名称'}
            , {data: 'uri', title: '资源路径'}
            , {
                data: 'intercept', title: '权限拦截'
                , render: function (data, type, row, meta) {
                    if (data == 1) {
                        return '<i class="fa fa-signing"></i> 拦截';
                    } else if (data == 0) {
                        return '';
                    }
                    return data;
                }
            }
            , {
                data: 'menu', title: '菜单显示'
                , render: function (data, type, row, meta) {
                    if (data == 1) {
                        return '<i class="fa fa-folder-open-o"></i> 显示';
                    } else if (data == 0) {
                        return '';
                    }
                    return data;
                }
            }
            , {
                data: 'icon', title: '图标样式'
                , render: function (data, type, row, meta) {
                    if (data) {
                        return '<i class="fa {icon}"></i>'.replace('{icon}', data);
                    }
                    return data;
                }
            }
            , {
                data: 'seq', title: '顺序'
            }
        ]
        , pagingType: 'full_numbers'
        , scrollX: true
        , language: {
            url: '${request.contextPath}/json/datatables-Chinese.json'
        }
        , ordering: false
        , searching: false
        , paging: false
        , info: false
        , scrollY: '75vh'
        , scrollCollapse: true
        , dom: 'Bfrtip'
        , buttons: [
            {
                text: '选择所有',
                action: function () {
                    datatable.rows().select();
                }
            },
            {
                text: '取消所有',
                action: function () {
                    datatable.rows().deselect();
                }
            }
        ]
        , columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }]
        , select: {
            style: 'multi'
            , blurable: false
        }
        , rowId: 'id'
    }).on('draw', function (e, settings) {
        if ($('#resourceIds').val().length > 0) {
            $.each($('#resourceIds').val().split(','), function (index, item) {
                datatable.row('#' + item).select();
            });
        }
    });

    function preForm() {
        var resourceIds = [];
        var selectedDatas = datatable.rows({selected: true}).data();
        if (selectedDatas.length > 0) {
            $.each(selectedDatas, function (index, item) {
                resourceIds.push(item.id);
            });
            $('#resourceIds').val(resourceIds.join(','));
        } else {
            $('#resourceIds').val('');
        }
        return true;
    }
</script>
</body>
</html>