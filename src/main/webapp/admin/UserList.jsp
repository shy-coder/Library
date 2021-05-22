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
    <button class="layui-btn layui-btn-normal" id="addUserBtn">新增用户</button>
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
<%--添加用户--%>
<form class="layui-form" name="addUserPanel" id="addUserPanel" style="display: none;">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="text" name="passWord" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">别名</label>
        <div class="layui-input-block">
            <input type="text" name="reader" required lay-verify="required" placeholder="请输入别名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <input type="radio" name="sex" value="0" title="男">
        <input type="radio" name="sex" value="1" title="女">
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-block">
            <input type="text" name="phone" required lay-verify="required" placeholder="请输入联系方式" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" required lay-verify="required" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" required lay-verify="required" placeholder="请输入自我描述" autocomplete="off" class="layui-input">
        </div>
    </div>
</form>

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

        //新增
        $(document).on('click','#addUserBtn',function () {
            console.log('~~~~~~我被点了');
            layer.open({
                title:'添加用户',
                content:$('#addUserPanel'),
                area:'777px',
                btn:['确认添加','取消'],
                yes:function (index,layer) {
                    const username = document.getElementById('userName')[0].value
                    const password = document.getElementById('passWord')[0].value
                    const name = document.getElementById('reader')[0].value
                    const phone = document.getElementById('phone')[0].value
                    const email = document.getElementById('email')[0].value
                    const description = document.getElementById('description')[0].value
                    const sex = document.getElementsByName('sex').value
                    $.ajax({
                        type:'post',
                        url:'/user/addUser',
                        data:JSON.stringify({
                          username:username,
                            password:password,
                            reader:name,
                            cellphone:phone,
                            email:email,
                            describe:description,
                            sex:sex
                        }),
                        contentType: "application/json;charset=utf-8",
                        success: function (res) {
                            console.log('添加成功')
                            location.reload() //刷新页面
                        },
                        error: function (e) {
                            console.log('添加失败', e)
                        }
                    });
                }
            });
        });

        // 删除
        $(document).on('click','#delete',function () {
            let userid = $(this).parents("tr").find("td").eq(0).text();
            console.log(userid);
            $.ajax({
                type:'POST',
                url:'/user/deleteUser',
                async:false,
                data:JSON.stringify({
                    userid:userid
                }),
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    console.log('删除成功')
                    location.reload() //刷新页面
                },
                error: function (e) {
                    console.log('删除失败', e)
                    location.reload() //刷新页面
                }
            })
        });

    });
</script>

</body>
</html>
