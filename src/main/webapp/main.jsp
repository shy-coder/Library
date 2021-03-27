<%@ page import="com.shy.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>layout 后台大布局 - Layui</title>
	<link rel="stylesheet" href="./layui/css/layui.css" />
</head>
<body class="layui-layout-body">

<%
	User user = (User) request.getSession().getAttribute("user");
%>

<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo" style="font-size: 25px">图书馆</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="">控制台</a></li>
			<li class="layui-nav-item"><a href="">商品管理</a></li>
			<li class="layui-nav-item"><a href="">用户</a></li>
			<li class="layui-nav-item">
				<a href="javascript:;">其它系统</a>
				<dl class="layui-nav-child">
					<dd><a href="">邮件管理</a></dd>
					<dd><a href="">消息管理</a></dd>
					<dd><a href="">授权管理</a></dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					<%=user.getReader()%>
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
					<dd><a href="">安全设置</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退了</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<li class="layui-nav-item layui-nav-itemed">
					<a class="" href="javascript:;">借阅服务</a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;" name="borrow"
							   title="查询图书"
							   content="./searchBooks.jsp" id="1"
						>查询图书
						</a></dd>
						<dd><a href="javascript:;" name="borrow"
							   title="借阅历史"
							   content="./userHistory.jsp" id="2">
							借阅历史</a></dd>
						<dd><a href="javascript:;" name="borrow"
							   title="在借图书"
							   content="./borrowList.jsp" id="3">
							在借图书</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;">读者服务</a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;">个人信息</a></dd>
						<dd><a href="javascript:;">收藏列表</a></dd>
					</dl>
				</li>
				<%--				<li class="layui-nav-item"><a href="">云市场</a></li>--%>
				<%--				<li class="layui-nav-item"><a href="">发布商品</a></li>--%>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<div class="layui-tab layui-tab-brief" lay-filter="tabTemp"
			 lay-allowClose="true"
			 style="display: flex;flex-direction: column;height:
		     100%;margin: 0;">
			<ul class="layui-tab-title">

			</ul>
			<div class="layui-tab-content" style="flex-grow: 1;">

			</div>
		</div>
	</div>

	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© layui.com - 底部固定区域
	</div>
</div>
<script src="./layui/layui.js"></script>
<script>
	//JavaScript代码区域
	layui.use(['element'], function(){
		var element = layui.element;
		var $ = layui.$;
		$("[name=borrow]").click(function (){
			//获取当前项的id和content
			var id = $(this).attr("id");
			var content = $(this).attr("content");
			//判断标签是否存在
			if ($("li[lay-id="+id+"]").length==0){
				//添加新标签
				element.tabAdd("tabTemp", {
					title: $(this).attr("title"),
					content:
							"<iframe src='"+content+"' class='frame' frameborder='0'></iframe>",
					id: id
				});
			}
			//切换标签
			element.tabChange("tabTemp", id);
		});
	});
</script>
</body>
</html>
