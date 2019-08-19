<#include "../macro/head.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@head title="Security Manager | Group"/>
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
                <small>Group</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Group</a></li>
                <li class="active">${edit}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-solid">
                        <div class="box-header">
                            <i class="fa fa-cog fa-spin"></i>
                            <h3 class="box-title">${edit}</h3>
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
                            <form class="form-horizontal"
                                  action="${request.contextPath}/security/group/${edit}" method="post">
                                <input name="edit" value="${edit}" style="display: none;">
                                <input name="id" value="${securityGroup.id?c}" style="display: none;">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="name" class="col-md-2 col-lg-1 control-label">组织名称</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="name" name="name" placeholder="请填写组织名称"
                                                   required value="${securityGroup.name!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="pid" class="col-md-2 col-lg-1 control-label">父组织</label>
                                        <div class="col-md-10 col-lg-11">
                                            <select id="pid" name="pid" class="form-control" style="width: 100%;">
                                                <option value="">选择父组织</option>
                                                <#if group?? && group?size gt 0>
                                                    <#list group as g>
                                                        <#if edit ="add" || g.id != securityGroup.id>
                                                            <option value="${g.id?c}"
                                                                    <#if securityGroup.pid?? && securityGroup.pid = g.id>selected</#if>>
                                                                ${g.name}
                                                            </option>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="seq" class="col-md-2 col-lg-1 control-label">组织排序</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="seq" name="seq" required min="0"
                                                   value="${securityGroup.seq?default(0)}" type="number">
                                        </div>
                                    </div>
                                </div>
                                <div class="box-footer">
                                    <button id="clearForm" type="button" class="btn btn-default">清空</button>
                                    <button type="submit" class="btn btn-info pull-right">${edit}</button>
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
    $('#pid').select2({});
</script>
</body>
</html>