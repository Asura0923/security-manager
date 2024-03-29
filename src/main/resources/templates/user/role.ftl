<#include "../macro/head.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@head title="Security Manager | User"/>
</head>
<body class="sidebar-mini skin-black">
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
                <li class="active">Role</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-solid">
                        <div class="box-header">
                            <i class="fa fa-cog fa-spin"></i>
                            <h3 class="box-title">关联角色</h3>
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
                                  action="${request.contextPath}/security/user/role" method="post">
                                <input name="id" value="${securityUser.id?c}" style="display: none;" readonly>
                                <input id="roleIds" name="roleIds" value="${roleIds!}"
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
            url: '${request.contextPath}/security/role/list'
            , type: 'post'
            , dataSrc: 'datas'
        }
        , columns: [
            {
                data: 'id', title: '', render: function () {
                    return ''
                }
            }
            , {data: 'name', title: '角色名称'}
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
        if ($('#roleIds').val().length > 0) {
            $.each($('#roleIds').val().split(','), function (index, item) {
                datatable.row('#' + item).select();
            });
        }
    });

    function preForm() {
        var roleIds = [];
        var selectedDatas = datatable.rows({selected: true}).data();
        if (selectedDatas.length > 0) {
            $.each(selectedDatas, function (index, item) {
                roleIds.push(item.id);
            });
            $('#roleIds').val(roleIds.join(','));
        } else {
            $('#roleIds').val('');
        }
        return true;
    }
</script>
</body>
</html>