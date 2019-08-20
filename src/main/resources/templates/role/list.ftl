<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#include "../macro/head.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@head title="Security Manager | Role"/>
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
                <small>Role</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Role</a></li>
                <li class="active">list</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-solid">
                        <div class="box-header">
                            <i class="fa fa-search"></i>
                            <h3 class="box-title">查询条件</h3>
                            <div class="pull-right box-tools">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse">
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                        </div>
                        <div class="box-body no-padding">
                            <div style="width: 100%"></div>
                        </div>
                        <div class="box-footer">
                            <form id="searchForm" class="form-horizontal"
                                  action="${request.contextPath}/security/role/list"
                                  method="get">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="name" class="col-md-2 col-lg-1 control-label">角色名称</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="name" name="name" placeholder="请填写角色名称"
                                                   value="${securityRole.name!}">
                                        </div>
                                    </div>
                                </div>
                                <div class="box-footer">
                                    <button id="clearForm" type="button" class="btn btn-default">清空条件</button>
                                    <button type="submit" class="btn btn-info pull-right">查询</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body">
                            <table id="resultTable" class="table table-striped table-bordered table-hover"
                                   style="width: 100%;"></table>
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
    function update(id) {
        location.href = '${request.contextPath}/security/role/update?id=' + id;
    }

    function resource(id) {
        location.href = '${request.contextPath}/security/role/resource?id=' + id;
    }

    function del(id, name) {
        Swal.fire({
            title: '您确定要删除' + name + '？',
            text: '删除后不可恢复！',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            focusConfirm: false,
            focusCancel: true
        }).then(function (result) {
            if (result.value) {
                $.post('${request.contextPath}/security/role/delete', {id: id}, function (result) {
                    if (result.status == 10000) {
                        Swal.fire({
                            position: 'top-end',
                            type: 'success',
                            title: result.message,
                            showConfirmButton: false,
                            onClose: function () {
                                location.href = '${request.contextPath}/security/role/list';
                            }
                        });
                    }
                }, 'json');
            }
        });
    }

    var datatable = $('#resultTable').DataTable({
        processing: true
        , serverSide: true
        , ajax: {
            url: '${request.contextPath}/security/role/list'
            , type: 'post'
            , dataSrc: 'datas'
        }
        , columns: [
            {data: 'name', title: '角色名称'}
            , {
                data: 'id', title: '操作'
                , render: function (data, type, row, meta) {
                    var c = [];

                    <@security.authorize access="hasRole('/security/role/update')">
                    c.push('<i class="fa fa-edit" title="编辑" onclick="update(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/role/resource')">
                    c.push('<i class="fa fa-key" title="关联资源" onclick="resource(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/role/delete')">
                    c.push('<i class="fa fa-remove" title="删除" onclick="del(\'{id}\',\'{name}\');"></i>'.replace('{id}', data).replace('{name}', row.name));
                    </@security.authorize>

                    return c.join('');
                }
            }
        ]
        , pagingType: 'full_numbers'
        , scrollX: true
        , language: {
            url: '${request.contextPath}/json/datatables-Chinese.json'
        }
        , ordering: false
        , searching: false
        , paging: true
        , info: true
        , scrollY: '75vh'
        , scrollCollapse: true
    }).on('preXhr.dt', function (e, settings, data) {
        $.extend(data, $('#searchForm').serializeObject());
    }).on('draw', function (e, settings) {
        <#if RequestParameters['message']??>
        Swal.fire({
            position: 'top-end',
            type: 'success',
            title: '${RequestParameters['message']}',
            showConfirmButton: false
        });
        <#elseif RequestParameters['error']??>
        Swal.fire({
            position: 'top-end',
            type: 'error',
            title: '${RequestParameters['error']}',
            showConfirmButton: false
        });
        </#if>
    });
</script>
</body>
</html>