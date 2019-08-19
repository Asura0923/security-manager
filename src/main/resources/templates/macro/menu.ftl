<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="https://cdn.bootcss.com/admin-lte/2.4.15/img/user2-160x160.jpg" class="img-circle"
                     alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                    <@security.authentication property="principal.securityUser.name"/>
                </p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul id="menu" class="sidebar-menu" data-widget="tree">
            <#macro menuMacro children>
                <#if children?? && children?size gt 0>
                    <#list children as child>
                        <#if child.uri?? && child.uri != "">
                            <#assign uri = request.contextPath + child.uri/>
                        <#else>
                            <#assign uri="#"/>
                        </#if>
                        <#if child.icon?? && child.icon != "">
                            <#assign icon = "fa " + child.icon/>
                        <#else>
                            <#if child.children?? && child.children?size gt 0>
                                <#assign icon="fa fa-th"/>
                            <#else>
                                <#assign icon="fa fa-circle-o"/>
                            </#if>
                        </#if>
                        <#if child.children?? && child.children?size gt 0>
                            <li class="treeview">
                                <a href="${uri}" <#if uri != "#">onclick="location.href='${uri}'"</#if>>
                                    <i class="${icon}"></i>
                                    <span>${child.name}</span>
                                    <span class="pull-right-container">
                                      <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <@menuMacro children=child.children />
                                </ul>
                            </li>
                        <#else>
                            <li><a href="${uri}"><i class="${icon}"></i>${child.name}</a></li>
                        </#if>
                    </#list>
                </#if>
            </#macro>
            <@security.authentication property="principal.menu" var="principal_menu"/>
            <@menuMacro children=principal_menu/>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>