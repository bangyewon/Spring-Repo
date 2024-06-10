<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    코드가 번잡함 --%>
<%--    <li>id = <%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    프로퍼티 접근법으로 가능 --%>
    <li>id =${member.id}</li>
    <li>username =${member.username}</li>
    <li>age = ${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
