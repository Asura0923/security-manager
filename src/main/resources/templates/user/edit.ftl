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
                                  action="${request.contextPath}/security/user/${edit}" method="post">
                                <input name="edit" value="${edit}" style="display: none;">
                                <input name="id" value="${securityUser.id?c}" style="display: none;">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="name" class="col-md-2 col-lg-1 control-label">用户名称</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="name" name="name" placeholder="请填写用户名称"
                                                   required value="${securityUser.name!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="email" class="col-md-2 col-lg-1 control-label">邮箱</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="email" name="email" placeholder="请填写邮箱"
                                                   type="email" required value="${securityUser.email!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="password" class="col-md-2 col-lg-1 control-label">密码</label>
                                        <div class="col-md-10 col-lg-11">
                                            <input class="form-control" id="password" name="password"
                                                   placeholder="<#if edit='add'>请填写密码<#else>密码为空则不修改密码</#if>"
                                                   type="password"
                                                   <#if edit='add'>required</#if>
                                                   value="${securityUser.password!}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="enabled" class="col-md-2 col-lg-1 control-label">状态</label>
                                        <div class="col-md-10 col-lg-11">
                                            <select id="enabled" name="enabled" class="form-control" required>
                                                <option value="1" <#if securityUser.enabled = 1>selected</#if>>
                                                    启用
                                                </option>
                                                <option value="0" <#if securityUser.enabled = 0>selected</#if>>
                                                    禁用
                                                </option>
                                            </select>
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
</script>
</body>
</html>