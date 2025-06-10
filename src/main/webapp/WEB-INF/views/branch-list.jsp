<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Branch List</title>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h1>Branch List</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address Line1</th>
        <th>City</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="b" items="${branches}">
        <tr>
            <td>${b.branchId}</td>
            <td>${b.branchName}</td>
            <td>${b.branchAddress.addressLine1}</td>
            <td>${b.branchAddress.city}</td>
            <td>
                <a href="${pageContext.request.contextPath}/branches/${b.branchId}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/branches/${b.branchId}/delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/branches/new">Create New Branch</a>

</body>
</html>
