<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../layui/css/layui.css"/>
</head>
<body>
<table class="layui-table" style="table-layout:fixed">
    <colgroup>
        <col width="120">
        <col >
        <col width="120">
        <col width="120">
        <col width="120">
        <col width="200">
    </colgroup>
    <thead>
    <tr>
        <th>序号</th>
        <th>书名</th>
        <th>作者</th>
        <th>用户账号</th>
        <th>用户别名</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${sessionScope.ADMIN_BORROW_BOOK}" varStatus="status">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.username}</td>
            <td >${book.reader}</td>
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


<script src="../layui/layui.js" charset="utf-8"></script>
<script>
    layui.use([ 'layer', 'element'], function () {
        var layer = layui.layer, element = layui.element;
        var $ = layui.$;
        $(document).on('click','#delete',function () {
            const bookId = $(this).attr("index")
            $.ajax({
                type: 'POST',
                url: '/delBorrowBook',
                async: false,
                data: JSON.stringify({
                    bookId: bookId,
                }),
                contentType: "application/json;charset=utf-8",
                success: function (data) {
                    layer.msg("操作成功!")
                    window.location.reload();
                    $(document).reload();
                },
                errors: function (data) {
                    layer.msg("操作失败！")
                }
            })
        })
    })
</script>


</body>
</html>
