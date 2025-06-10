<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Account List</title>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>


<h1>Account List</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Type</th>
        <th>Holder</th>
        <th>Balance</th>
        <th>Date Opened</th>
        <th>Customer</th>
        <th>Branch</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="acc" items="${accounts}">
        <tr>
            <td>${acc.accountId}</td>
            <td>${acc.accountType}</td>
            <td>${acc.accountHolder}</td>
            <td>${acc.accountBalance}</td>
            <td>${acc.accountDateOpened}</td>
            <td>
                <c:out value="${acc.accountCustomer.customerName}"/>
            </td>
            <td>
                <c:out value="${acc.accountBranch.branchName}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/accounts/${acc.accountId}/edit">Edit</a>
                <a href="${pageContext.request.contextPath}/accounts/${acc.accountId}/delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/accounts/new">Create New Account</a>
</body>
</html>

