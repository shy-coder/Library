<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>图书馆-用户注册</title>
    <link rel="stylesheet" href="./layui/css/layui.css"/>
    <style>
        html, body {
            width: 100%;
            height: 100%;
        }

        .register {
            position: relative;
            width: 100%;
            height: 100%;
        }

        .register-layout {
            position: absolute;
            width: 500px;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background: rgba(0, 150, 136, 0.1);
            padding: 100px 80px 80px 50px;
        }

        .logo {
            font-size: 30px;
            width: 100%;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="register">
    <div class="register-layout">
        <form class="layui-form" action="/Library/register">
            <div class="layui-form-item">
                <label class="layui-form-label logo">
                    用户注册
                </label>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"
                       style="text-align: center">用户名
                </label>
                <div class="layui-input-block">
                    <input type="text" name="username"
                           placeholder="请输入"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="text-align:
                center">密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password"
                           placeholder="请输入"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="text-align:
                center">别名</label>
                <div class="layui-input-block">
                    <input type="text" name="reader"
                           placeholder="请输入"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    layui.use('form', function () {
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>

</body>
</html>
