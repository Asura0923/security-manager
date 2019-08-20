<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#include "../macro/head.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@head title="Security Manager | Group"/>
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
                <small>Group</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Group</a></li>
                <li class="active">list</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
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
    function add(pid) {
        location.href = '${request.contextPath}/security/group/add?pid=' + pid;
    }

    function update(id) {
        location.href = '${request.contextPath}/security/group/update?id=' + id;
    }

    function resource(id) {
        location.href = '${request.contextPath}/security/group/resource?id=' + id;
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
                $.post('${request.contextPath}/security/group/delete', {id: id}, function (result) {
                    if (result.status == 10000) {
                        Swal.fire({
                            position: 'top-end',
                            type: 'success',
                            title: result.message,
                            showConfirmButton: false,
                            onClose: function () {
                                location.href = '${request.contextPath}/security/group/list';
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
            url: '${request.contextPath}/security/group/list'
            , type: 'post'
            , dataSrc: 'datas'
        }
        , columns: [
            {data: 'name', title: '组织名称'}
            , {
                data: 'seq', title: '顺序'
            }
            , {
                data: 'id', title: '操作'
                , render: function (data, type, row, meta) {
                    var c = [];

                    <@security.authorize access="hasRole('/security/group/update')">
                    c.push('<i class="fa fa-edit" title="编辑" onclick="update(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/group/add')">
                    c.push('<i class="fa fa-plus-square-o" title="添加子节点" onclick="add(\'{pid}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{pid}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/group/resource')">
                    c.push('<i class="fa fa-key" title="关联资源" onclick="resource(\'{id}\');"></i>&nbsp;&nbsp;&nbsp;&nbsp;'.replace('{id}', row.id));
                    </@security.authorize>

                    <@security.authorize access="hasRole('/security/group/delete')">
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
        , paging: false
        , info: false
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
        </#if>
    });
</script>
</body>
</html>