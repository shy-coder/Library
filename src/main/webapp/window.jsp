<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String message = request.getParameter("message");

    if (message != null && !message.equals("")) {
%>
<script src="./layui/layui.all.js"></script>
<script>
    layer.msg("<%=message%>");
</script>
<%
    }
%>
</body>

</html>
