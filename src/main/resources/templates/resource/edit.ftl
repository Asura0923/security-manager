<#include "../macro/head.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@head title="Security Manager | Resource"/>
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
                <small>Resource</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Resource</a></li>
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
                                  action="${request.contextPath}/security/resource/${edit}" method="post">
                                <input name="edit" value="${edit}" style="display: none;">
                                <input name="id" value="${securityResource.id?c}" style="display: none;">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="name" class="col-md-2 col-lg-1 control-label">资源名称</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="name" name="name" placeholder="请填写资源名称"
                                                   required value="${securityResource.name!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="uri" class="col-md-2 col-lg-1 control-label">资源路径</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="uri" name="uri" placeholder="请填写资源路径"
                                                   value="${securityResource.uri!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="pid" class="col-md-2 col-lg-1 control-label">父资源</label>
                                        <div class="col-md-10 col-lg-11">
                                            <select id="pid" name="pid" class="form-control" style="width: 100%;">
                                                <option value="">选择父资源</option>
                                                <#if menu?? && menu?size gt 0>
                                                    <#list menu as m>
                                                        <#if edit ="add" || m.id != securityResource.id>
                                                            <option value="${m.id?c}"
                                                                    <#if securityResource.pid?? && securityResource.pid = m.id>selected</#if>>
                                                                ${m.name}
                                                            </option>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="seq" class="col-md-2 col-lg-1 control-label">资源排序</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="seq" name="seq" required min="0"
                                                   value="${securityResource.seq?default(0)}" type="number">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="intercept" class="col-md-2 col-lg-1 control-label">权限拦截</label>
                                        <div class="col-md-10 col-lg-11">
                                            <select id="intercept" name="intercept" class="form-control" required>
                                                <option value="0" <#if securityResource.intercept = 0>selected</#if>>
                                                    不拦截
                                                </option>
                                                <option value="1" <#if securityResource.intercept = 1>selected</#if>>
                                                    拦截
                                                </option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="menu" class="col-md-2 col-lg-1 control-label">资源类型</label>
                                        <div class="col-md-10 col-lg-11">
                                            <select id="menu" name="menu" class="form-control" required>
                                                <option value="1" <#if securityResource.menu = 1>selected</#if>>
                                                    菜单
                                                </option>
                                                <option value="0" <#if securityResource.menu = 0>selected</#if>>
                                                    功能
                                                </option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="icon" class="col-md-2 col-lg-1 control-label">图表样式</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="icon" name="icon"
                                                   placeholder="填写图标样式名称" value="${securityResource.icon!}">
                                            <a href="http://fontawesome.dashgame.com/#new" target="_blank">图标样式表</a>
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