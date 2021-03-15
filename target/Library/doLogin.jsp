<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>Title</title>
</head>
<body>
<%
	// 获取传递参数用户名和密码
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	//1. 加载驱动
	Class.forName("com.mysql.cj.jdbc.Driver");
	//2. 获得连接实例
	try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?serverTimezone=UTC&characterEncoding=utf-8", "root", "123456")) {
		//3. 预编译SQL语句
		String sql = "select * from borrow_card where username=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, username);
			//4. 执行查询
			try (ResultSet resultSet = statement.executeQuery()) {
				//5. 遍历ResultSet
				while (resultSet.next()) {
					if (password.equals(resultSet.getString("password"))) {
						//执行跳转
						response.sendRedirect("./main.jsp");
					} else {
						//返回首页，密码错误
						response.sendRedirect("./index.jsp");
					}
				}
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
%>
</body>
</html>
