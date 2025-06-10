<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Role List</title></head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>

<h1>Role List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Role Name</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="r" items="${roles}">
        <tr>
            <td>${r.roleId}</td>
            <td>${r.roleName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/roles/${r.roleId}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/roles/${r.roleId}/delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/roles/new">Create New Role</a>
</body>
</html>
