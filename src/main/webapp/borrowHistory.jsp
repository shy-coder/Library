<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Title</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="./layui/css/layui.css" media="all">

	<style>
		.wrap-div {
			display: -webkit-box;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 3;
			overflow: hidden;
			float: left;
			width: 100%;
			word-break: break-all;
			text-overflow: ellipsis;
		}
	</style>
</head>
<body>
<table class="layui-table" style="table-layout:fixed">
	<colgroup>
		<col width="150">
		<col width="150">
		<col width="150">
		<col width="150">
		<col>
		<col width="150">
		<col width="200">
	</colgroup>
	<thead>
	<tr>
		<th>#</th>
		<th>书名</th>
		<th>作者</th>
		<th>分类</th>
		<th>描述</th>
		<th>商店</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="book" items="${sessionScope.bookList}" varStatus="status">
		<tr>
			<td>${book.id}</td>
			<td>${book.name}</td>
			<td>${book.author}</td>
			<td>${book.sort}</td>
			<td class="wrap-td">
				<div class="wrap-div">${book.description}</div>
			</td>
			<td>${book.sort}</td>
			<td>
				<button class="layui-btn layui-btn-primary layui-btn-xs detail" id="info" index="${status.index}">
					查看
				</button>
				<button class="layui-btn layui-btn-xs borrow" id="delete" index="${book.id }">
					还书
				</button>
				<button class="layui-btn layui-btn-xs borrow" id="store" index="${book.id}">
						${book.store?"已收藏":"收藏"}
				</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<script src="./jquery-3.5.1.min.js" charset="utf-8"></script>

<script src="./layui/layui.js" charset="utf-8"></script>

<script type="text/javascript">
	layui.use(['layer'], function () {
		$(document).ready(function () {
			$.ajax({
				type: 'GET',
				url: '/getBorrowBooks',
				async: false,
				contentType: "application/json;charset=utf-8",
				success: function (data) {
				},
				errors: function (data) {
					console.log(data)
				}
			})
		})
		//还书
		$(document).on('click', '#delete', function () {
			const bookId = $(this).attr("index")
			console.log(bookId)
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
