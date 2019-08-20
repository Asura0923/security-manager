<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<@security.authentication property="principal.username" var="principal_username"/>
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
                                  action="${request.contextPath}/security/user/list"
                                  method="get">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="name" class="col-md-2 col-lg-1 control-label">用户名称</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="name" name="name" placeholder="请填写用户名称"
                                                   value="${securityUser.name!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="email" class="col-md-2 col-lg-1 control-label">邮箱</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="email" name="email" placeholder="请填写邮箱"
                                                   value="${securityUser.email!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="enabled" class="col-md-2 col-lg-1 control-label">状态</label>
                                        <div class="col-md-10 col-lg-11">
                                            <select id="enabled" name="enabled" class="form-control">
                                                <option value="">请选择账号状态</option>
                                                <option value="1"
                                                        <#if securityUser.enabled?? && securityUser.enabled = 1>selected</#if>>
                                                    启用
                                                </option>
                                                <option value="0"
                                                        <#if securityUser.enabled?? && securityUser.enabled = 0>selected</#if>>
                                                    禁用
                                                </option>
                                            </select>
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
        location.href = '${request.contextPath}/security/user/update?id=' + id;
    }

    function resource(id) {
        location.href = '${request.contextPath}/security/user/resource?id=' + id;
    }

    function group(id) {
        location.href = '${request.contextPath}/security/user/group?id=' + id;
    }

    function role(id) {
        location.href = '${request.contextPath}/security/user/role?id=' + id;
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
                $.post('${request.contextPath}/security/user/delete', {id: id}, function (result) {
                    if (result.status == 10000) {
                        Swal.fire({
                            position: 'top-end',
                            type: 'success',
                            title: result.message,
                            showConfirmButton: false,
                            onClose: function () {
                                location.href = '${request.contextPath}/security/user/list';
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
            url: '${request.contextPath}/security/user/list'
            , type: 'post'
            , dataSrc: 'datas'
        }
        , columns: [
            {data: 'name', title: '用户名称'}
            , {data: 'email', title: '邮箱'}
            , {data: 'lastLogin', title: '最后登入时间'}
            , {
                data: 'enabled', title: '状态'
                , render: function (data, type, row, meta) {
                    if (data == 1) {
                        return '<i class="fa fa-check"></i> 启用';
                    }
                    return '<i class="fa fa-ban"></i> 禁用';
                }
            }
            , {
                data: 'id', title: '操作'
                , render: function (data, type, row, meta) {
                    var c = [];

                    <@security.authorize access="hasRole('/security/user/update')">
                    c.push('<i class="fa fa-edit" title="编辑" onclick="update(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/user/resource')">
                    c.push('<i class="fa fa-key" title="关联资源" onclick="resource(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/user/group')">
                    c.push('<i class="fa fa-group" title="关联组织" onclick="group(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/user/role')">
                    c.push('<i class="fa fa-user-circle" title="关联角色" onclick="role(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/user/delete')">
                    c.push('<i class="fa fa-remove" title="删除" onclick="del(\'{id}\',\'{name}\');"></i>'.replace('{id}', data).replace('{name}', row.name));
                    </@security.authorize>

                    if ('${principal_username}' == row.email) {
                        return '';
                    }

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