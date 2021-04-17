<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>设置我的资料</title>
	<link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>

<body>
<jsp:useBean id="user" class="com.shy.pojo.User" scope="session"/>

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">设置我的资料</div>
				<div class="layui-card-body" pad15>
					<div class="layui-row">
						<div class="layui-col-md2" style="display: flex;justify-content: flex-end;">
							<img class="layui-header"
								 src="<jsp:getProperty name="user" property="header"/>"/>
						</div>
						<div class="layui-col-md10">
							<div class="layui-form" lay-filter="">
								<div class="layui-form-item">
									<label class="layui-form-label">用户名</label>
									<div class="layui-input-inline">
										<input type="text"
											   name="username"
											   value="<jsp:getProperty name="user" property="username"/>"
											   readonly
											   class="layui-input">
									</div>
									<div class="layui-form-mid layui-word-aux">
										不可修改,用于后台登录名
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">昵称</label>
									<div class="layui-input-inline">
										<input type="text"
											   name="nickname"
											   value="<jsp:getProperty name="user" property="reader"/>"
											   lay-verify="nickname"
											   autocomplete="off"
											   placeholder="请输入昵称"
											   class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">性别</label>
									<div class="layui-input-block">
										<input type="radio" name="sex"
											   value="男"
											   title="男"
												<%=user.isSex() ? "checked" : ""%>/>
										<input type="radio" name="sex"
											   value="女"
											   title="女" <%=user.isSex() ? "" : "checked"%>/>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">头像</label>
									<div class="layui-input-inline">
										<input name="avatar"
											   lay-verify="required"
											   id="LAY_avatarSrc"
											   placeholder="图片地址"
											   value="<jsp:getProperty name="user" property="header"/>"
											   class="layui-input">
									</div>
									<div class="layui-input-inline layui-btn-container"
										 style="width: auto;">
										<button type="button"
												class="layui-btn layui-btn-primary"
												id="LAY_avatarUpload">
											<i class="layui-icon">&#xe67c;</i>上传图片
										</button>
										<button class="layui-btn layui-btn-primary"
												layadmin-event="avartatPreview">
											查看图片
										</button>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">手机</label>
									<div class="layui-input-inline">
										<input type="text"
											   name="cellphone"
											   value="<jsp:getProperty name="user" property="cellphone"/>"
											   lay-verify="phone"
											   autocomplete="off"
											   class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">邮箱</label>
									<div class="layui-input-inline">
										<input type="text"
											   name="email"
											   value="<jsp:getProperty name="user" property="email"/>"
											   lay-verify="email"
											   autocomplete="off"
											   class="layui-input">
									</div>
								</div>
								<div class="layui-form-item layui-form-text">
									<label class="layui-form-label">备注</label>
									<div class="layui-input-block">
										<textarea name="remarks"
												  placeholder="请输入内容"
												  class="layui-textarea"><jsp:getProperty
												name="user"
												property="describe"/>
										</textarea>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="setmyinfo">确认修改</button>
										<button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/layui/layui.js"></script>
<script>
	layui.use(['form', 'upload'], function () {
		var form = layui.form;
		var upload = layui.upload;

		//执行实例
		var uploadInst = upload.render({
			elem: '#LAY_avatarUpload' //绑定元素
			, url: '/upload/' //上传接口
			, done: function (res) {
				//上传完毕回调
			}
			, error: function () {
				//请求异常回调
			}
		});


		//各种基于事件的操作，下面会有进一步介绍
	});
</script>
</body>

</html>
