<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Customer List</title></head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>

<h1>Customer List</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>DOB</th>
        <th>Gender</th>
        <th>City</th>
        <th>SSN</th>
        <th>UserName</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="c" items="${customers}">
        <tr>
            <td>${c.customerId}</td>
            <td>${c.customerName}</td>
            <td>${c.customerDOB}</td>
            <td>${c.customerGender}</td>
            <td>${c.customerAddress.city}</td>
            <td>${c.customerSSN}</td>
            <td>${c.user.username}</td>

            <td>
                <a href="${pageContext.request.contextPath}/customers/${c.customerId}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/customers/${c.customerId}/delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/customers/new">Create New Customer</a>
</body>
</html>
