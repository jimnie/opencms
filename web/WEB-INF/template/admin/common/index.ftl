<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>${message("admin.index.title")} - Powered By SHOP++</title>
    <meta name="author" content="SHOP++ Team"/>
    <meta name="copyright" content="SHOP++"/>
    <link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .input .powered {
            font-size: 11px;
            text-align: right;
            color: #cccccc;
        }
    </style>
</head>
<body>
<div class="path">
${message("admin.index.title")}
</div>
<table class="input">
    <tr>
        <th>
        ${message("admin.index.systemName")}:
        </th>
        <td>
        ${systemName}
        </td>
        <th>
        ${message("admin.index.systemVersion")}:
        </th>
        <td>
        ${systemVersion}
        </td>
    </tr>
    <tr>
        <th>
        ${message("admin.index.official")}:
        </th>
        <td>
            <a href="http://www.shopxx.net" target="_blank">http://www.shopxx.net</a>
        </td>
        <th>
        ${message("admin.index.bbs")}:
        </th>
        <td>
            <a href="http://bbs.shopxx.net" target="_blank">http://bbs.shopxx.net</a>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            &nbsp;
        </td>
    </tr>
    <tr>
        <th>
        ${message("admin.index.javaVersion")}:
        </th>
        <td>
        ${javaVersion}
        </td>
        <th>
        ${message("admin.index.javaHome")}:
        </th>
        <td>
        ${javaHome}
        </td>
    </tr>
    <tr>
        <th>
        ${message("admin.index.osName")}:
        </th>
        <td>
        ${osName}
        </td>
        <th>
        ${message("admin.index.osArch")}:
        </th>
        <td>
        ${osArch}
        </td>
    </tr>
    <tr>
        <th>
        ${message("admin.index.serverInfo")}:
        </th>
        <td>
        ${serverInfo}
        </td>
        <th>
        ${message("admin.index.servletVersion")}:
        </th>
        <td>
        ${servletVersion}
        </td>
    </tr>
    <tr>
        <td colspan="4">
            &nbsp;
        </td>
    </tr>
</table>
</body>
</html>