<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>

<h1>User List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Roles</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.userId}</td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>
                <c:forEach var="r" items="${u.roles}">
                    ${r.roleName}<br/>
                </c:forEach>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${u.userId}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/users/${u.userId}/delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/users/new">Create New User</a>
</body>
</html>
