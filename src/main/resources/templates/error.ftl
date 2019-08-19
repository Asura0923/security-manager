<#include "macro/head.ftl"/>
<!DOCTYPE html>
<html>
<head>
    <@head title="Security Manager | Error"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content">
    <div class="error-page">
        <h2 class="headline text-red">${status!}</h2>
        <div class="error-content">
            <h3><i class="fa fa-warning text-red"></i> ${error!}</h3>
            <p>
                ${message!}
            </p>
            <p>
                ${trace!}
            </p>
            <p>
                <a href="${request.contextPath}/">回到索引页面</a>
            </p>
            <p>
                <a href="${request.contextPath}/security/login">回到登录页面</a>
            </p>
        </div>
    </div>
</section>
</body>
</html>