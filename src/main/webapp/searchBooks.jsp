<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Layui</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
	      content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="./layui/css/layui.css"
	      media="all">
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<div class="layui-nav-item demoTable" style="display: flex;justify-content: flex-end;">
	<input type="text" class="layui-input"
	       style="padding: 0;display: inline;width: 300px;" placeholder="请输入搜索信息..."/>
	<button class="layui-btn" data-type="getCheckLength"
	        style="margin-left: 20px;">搜索
	</button>
</div>

<table class="layui-table" style="width: inherit"
       lay-data="{height:700, url: '/FromAjaxservlet', page:true, id:'idTest'}"
       lay-filter="demo">
	<thead>
	<tr>
		<th
				lay-data="{type:'checkbox', width:5%, fixed: 'left'}"></th>
		<th lay-data="{field: 'id', width:'5%', sort: false}">ID</th>
		<th
				lay-data="{field:'username', width:'15%', align: 'center'}">用户名</th>
		<th lay-data="{field:'sex', width:'5%', sort: true}">性别</th>
		<th lay-data="{field:'city', width:'5%'}">城市</th>
		<th lay-data="{field:'sign', width:'5%'}">签名</th>
		<th lay-data="{field:'experience', width:'5%', sort: true}">积分
		</th>
		<th lay-data="{field:'classify', width:'5%'}">职业</th>
		<th lay-data="{field:'wealth', width:'10%', sort: true}">财富</th>
		<th
				lay-data="{field:'score', width:'5%', sort: true, fixed: 'right'}">评分</th>
		<th
				lay-data="{fixed: 'right', align:'center',toolbar: '#barDemo'}"></th>
	</tr>
	</thead>
</table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs"
	   lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs"
	   lay-event="del">删除</a>
</script>


<script src="./layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
    layui.use('table', function () {
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.alert('编辑行：<br>' + JSON.stringify(data))
            }
        });

        var $ = layui.$, active = {
            getCheckData: function () { //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            , getCheckLength: function () { //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    , data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
            }
            , isAll: function () { //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选' : '未全选')
            }
        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

</body>
</html>
