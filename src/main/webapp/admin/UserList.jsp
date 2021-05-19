<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">

</head>
<body>

<div class="layui-form" id="content">
    <button class="layui-btn layui-btn-normal" id="addBooksBtn">新增用户</button>
    <table class="layui-table" style="table-layout:fixed">
        <colgroup>
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col >
            <col width="100">
            <col width="100">
            <col width="100">
        </colgroup>
        <thead>
        <tr>
            <th>序号</th>
            <th>用户名</th>
            <th>密码</th>
            <th>别名</th>
            <th>头像</th>
            <th>描述</th>
            <th>手机号</th>
            <th>邮箱</th>
            <th>性别</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${sessionScope.users}" varStatus="status">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.reader}</td>
                <td>${user.header}</td>
                <td>${user.describe}</td>
                <td>${user.cellphone}</td>
                <td>${user.email}</td>
                <td>${user.sex}</td>
                <td>
                    <button class="layui-btn layui-btn-primary layui-btn-xs detail" id="info" index="${status.index}">
                        查看
                    </button>
                    <button class="layui-btn layui-btn-xs borrow" id="update" index="${book.id}">
                        修改
                    </button>
                    <button class="layui-btn layui-btn-xs borrow" id="delete" index="${book.id}">
                        删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="../layui/layui.js" charset="utf-8"></script>

<script>
    layui.use(['laypage', 'layer', 'element', 'form'], function () {
        var laypage = layui.laypage, layer = layui.layer, element = layui.element;
        var $ = layui.$;
        var form = layui.form;
        form.render();

        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: '/user/getAllUser',
                async: false,
                contentType: "application/json;charset=utf-8",
                success: function (data) {
                    console.log(data)
                },
                errors: function (data) {
                    console.log(data)
                }
            })
        });
    });
</script>

</body>
</html>
