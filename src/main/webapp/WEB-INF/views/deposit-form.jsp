<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Deposit</title>
</head>
<body>
<%@ include file="/WEB-INF/views/navbar.jsp" %>
<h1>Deposit</h1>
<form:form modelAttribute="depositRequest" action="${pageContext.request.contextPath}/transactions/deposit" method="post">
    <table>
        <tr>
            <td>Account ID:</td>
            <td><form:input path="accountId"/>
                <form:errors path="accountId" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Amount:</td>
            <td><form:input path="amount"/>
                <form:errors path="amount" cssClass="error"/></td>
        </tr>
    </table>
    <input type="submit" value="Deposit"/>
</form:form>

<style>
    .error {
        color: red;
        font-size: 0.9em;
    }
</style>
</body>
</html>
